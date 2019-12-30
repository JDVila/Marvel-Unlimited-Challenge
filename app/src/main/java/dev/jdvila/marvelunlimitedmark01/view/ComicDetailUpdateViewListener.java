package dev.jdvila.marvelunlimitedmark01.view;

import dev.jdvila.marvelunlimitedmark01.model.MarvelResponse;

public interface ComicDetailUpdateViewListener {
    void updateComicDetailView(MarvelResponse marvelResponse);
    void onDataAquisitionError();
}
