package dev.jdvila.marvelunlimitedmark01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import dev.jdvila.marvelunlimitedmark01.model.MarvelResponse;
import dev.jdvila.marvelunlimitedmark01.repository.ComicDetailRepository;
import dev.jdvila.marvelunlimitedmark01.repository.ComicDetailRepositoryImpl;
import dev.jdvila.marvelunlimitedmark01.view.ComicDetailUpdateViewListener;
import dev.jdvila.marvelunlimitedmark01.view.ViewLoader;
import dev.jdvila.marvelunlimitedmark01.view.fragments.ComicDetailViewFragment;

public class MainActivity extends AppCompatActivity implements ComicDetailUpdateViewListener, ViewLoader {

    ComicDetailRepositoryImpl dataRepository;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataRepository = new ComicDetailRepositoryImpl();
        loadLoadingDialog(this, "Loading...", "Loading Comic Details...");
        loadComicDetailView(dataRepository, 291, this);
    }

    @Override
    public void loadComicDetailView(ComicDetailRepository comicDetailRepository, int bookId, Context context) {
        comicDetailRepository.getComicDetailData(bookId, context);
    }

    @Override
    public void updateComicDetailView(MarvelResponse marvelResponse) {
        closeLoadingDialog();
        ComicDetailViewFragment comicDetailViewFragment = ComicDetailViewFragment.newInstance(marvelResponse);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentViewContainerFrameLayout, comicDetailViewFragment);
        fragmentTransaction.commitNow();
    }

    @Override
    public void onDataAquisitionError() {
        closeLoadingDialog();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(getApplicationContext().getResources().getString(R.string.networkErrorDialogTitle));
        alertDialogBuilder.setMessage(getApplicationContext().getResources().getString(R.string.networkErrorDialogMessage));
        alertDialogBuilder.setPositiveButton(getApplicationContext().getResources().getString(R.string.quit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialogBuilder.create().show();
    }

    public void loadLoadingDialog(Context context, String title, String message) {
        progressDialog = ProgressDialog.show(context, title, message, false);
    }

    public void closeLoadingDialog() {
        progressDialog.dismiss();
        progressDialog = null;
    }
}
