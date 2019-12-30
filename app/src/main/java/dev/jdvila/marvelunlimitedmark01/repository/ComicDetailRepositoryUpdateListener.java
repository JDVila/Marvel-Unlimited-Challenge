package dev.jdvila.marvelunlimitedmark01.repository;

import android.content.Context;

import dev.jdvila.marvelunlimitedmark01.model.MarvelResponse;

public interface ComicDetailRepositoryUpdateListener {
    void updateData(MarvelResponse marvelResponse, Context context);
}
