package dev.jdvila.marvelunlimitedmark01.repository;

import android.accounts.NetworkErrorException;

import dev.jdvila.marvelunlimitedmark01.view.ComicDetailUpdateViewListener;

public interface ComicDetailNetworkData {

    void getComicDetailDataFromNetwork(Integer bookId, ComicDetailUpdateViewListener comicDetailUpdateViewListener, ComicDetailRepositoryUpdateListener comicDetailRepositoryUpdateListener);
}
