package dev.jdvila.marvelunlimitedmark01.view;

import android.content.Context;

import dev.jdvila.marvelunlimitedmark01.repository.ComicDetailRepository;

public interface ViewLoader {
    void loadComicDetailView(ComicDetailRepository comicDetailRepository, int bookId, Context context);
}
