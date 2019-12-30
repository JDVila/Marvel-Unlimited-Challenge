package dev.jdvila.marvelunlimitedmark01.network;

import dev.jdvila.marvelunlimitedmark01.model.MarvelResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ComicDetailService {
    @GET("v1/public/comics/{comic_id}")
    Call<MarvelResponse> getComic(@Path("comic_id") String comicId, @Query("ts") String timeStamp, @Query("apikey") String apiKey, @Query("hash") String hash);
}
