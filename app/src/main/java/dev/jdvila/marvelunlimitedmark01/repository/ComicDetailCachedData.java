package dev.jdvila.marvelunlimitedmark01.repository;

import android.content.Context;

import dev.jdvila.marvelunlimitedmark01.model.MarvelResponse;

public interface ComicDetailCachedData {

    MarvelResponse getComicDetailDataFromCache(Integer bookId, Context context);

    void updateComicDetailDataToCache(Integer bookId, MarvelResponse marvelResponse, Context context);
}
