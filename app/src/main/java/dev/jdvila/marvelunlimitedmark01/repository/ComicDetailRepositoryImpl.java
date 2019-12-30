package dev.jdvila.marvelunlimitedmark01.repository;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.Date;
import java.util.Objects;

import dev.jdvila.marvelunlimitedmark01.R;
import dev.jdvila.marvelunlimitedmark01.encryption.Md5HashCreatorImp;
import dev.jdvila.marvelunlimitedmark01.model.MarvelResponse;
import dev.jdvila.marvelunlimitedmark01.network.ComicDetailService;
import dev.jdvila.marvelunlimitedmark01.network.RetrofitSingleton;
import dev.jdvila.marvelunlimitedmark01.view.ComicDetailUpdateViewListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComicDetailRepositoryImpl implements ComicDetailRepository, ComicDetailNetworkData, ComicDetailCachedData, ComicDetailRepositoryUpdateListener {
    private static final String SHARED_PREFERENCES_MARVEL_KEY = "sharedPreferencesMarvelKey";

    @Override
    public MarvelResponse getComicDetailDataFromCache(Integer bookId, Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(SHARED_PREFERENCES_MARVEL_KEY, Context.MODE_PRIVATE);
        String data = sharedPreferences.getString(bookId.toString(), null);
        MarvelResponse marvelResponse = null;
        if (null != data) {
            marvelResponse = new Gson().fromJson(data, MarvelResponse.class);
        }
        return marvelResponse;
    }

    @Override
    public void updateComicDetailDataToCache(final Integer bookId, MarvelResponse marvelResponse, final Context context) {
        final String data = new Gson().toJson(marvelResponse);
        final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(SHARED_PREFERENCES_MARVEL_KEY, Context.MODE_PRIVATE);
        // This method is called on a background thread:
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(context.getApplicationContext().getResources().getString(R.string.comicBookId) + bookId.toString(), data);
                // The following write to disk is performed asynchronously:
                editor.apply();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void getComicDetailData(Integer bookId, Context context) {
        MarvelResponse marvelResponse = getComicDetailDataFromCache(bookId, context);
        if (context instanceof ComicDetailUpdateViewListener) {
            ComicDetailUpdateViewListener comicDetailUpdateViewListener = (ComicDetailUpdateViewListener) context;
            if (null != marvelResponse) {
                comicDetailUpdateViewListener.updateComicDetailView(marvelResponse);
            } else {
                getComicDetailDataFromNetwork(bookId, comicDetailUpdateViewListener, this);
            }
        } else {
            throw new ClassCastException("Error - " + context.getClass().getSimpleName() + " not instance of ComicDetailUpdateViewListener");
        }
    }

    @Override
    public void getComicDetailDataFromNetwork(Integer bookId, final ComicDetailUpdateViewListener comicDetailUpdateViewListener, final ComicDetailRepositoryUpdateListener comicDetailRepositoryUpdateListener) {
        Retrofit retrofitInstance = null;
        try {
            RetrofitSingleton.init("https://gateway.marvel.com", GsonConverterFactory.create());
            retrofitInstance = RetrofitSingleton.getRetrofitInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if (null != retrofitInstance) {
            Md5HashCreatorImp md5HashCreatorImp = new Md5HashCreatorImp();
            ComicDetailService comicDetailService = retrofitInstance.create(ComicDetailService.class);
            long timeStamp = new Date().getTime();
            Call<MarvelResponse> comicDetailsCall = comicDetailService.getComic(
                    Integer.toString(bookId),
                    Long.toString(timeStamp), ((Context) comicDetailUpdateViewListener).getApplicationContext().getResources().getString(R.string.publicKey),
                    md5HashCreatorImp.createHash(Long.toString(timeStamp),
                            ((Context) comicDetailUpdateViewListener).getApplicationContext().getResources().getString(R.string.publicKey),
                            ((Context) comicDetailUpdateViewListener).getApplicationContext().getResources().getString(R.string.privateKey)
                            ));
            comicDetailsCall.enqueue(new Callback<MarvelResponse>() {
                @Override
                public void onResponse(@NonNull Call<MarvelResponse> call, @NonNull Response<MarvelResponse> response) {
                    if (null != response.body()) {
                        comicDetailRepositoryUpdateListener.updateData(response.body(), (Context) comicDetailUpdateViewListener);
                        comicDetailUpdateViewListener.updateComicDetailView(response.body());
                    } else {
                        comicDetailUpdateViewListener.onDataAquisitionError();
                    }
                }

                @Override
                public void onFailure(Call<MarvelResponse> call, Throwable t) {
                    comicDetailUpdateViewListener.onDataAquisitionError();
                    t.printStackTrace();
                }
            });
        }
    }

    @Override
    public void updateData(MarvelResponse marvelResponse, Context context) {
        Integer id = Objects.requireNonNull(Objects.requireNonNull(marvelResponse.getData()).getResults()).get(0).getId();
        updateComicDetailDataToCache(id, marvelResponse, context);
    }
}
