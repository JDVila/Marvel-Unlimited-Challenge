package dev.jdvila.marvelunlimitedmark01.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dev.jdvila.marvelunlimitedmark01.R;
import dev.jdvila.marvelunlimitedmark01.model.Item;
import dev.jdvila.marvelunlimitedmark01.model.MarvelResponse;

public class ComicDetailViewFragment extends Fragment {
    private static final String MARVEL_RESPONSE = "marvelResponse";

    private MarvelResponse marvelResponse;

    public ComicDetailViewFragment() {
    }

    public static ComicDetailViewFragment newInstance(MarvelResponse marvelResponse) {
        ComicDetailViewFragment fragment = new ComicDetailViewFragment();
        Bundle args = new Bundle();
        args.putSerializable(MARVEL_RESPONSE, marvelResponse);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.marvelResponse = (MarvelResponse) getArguments().getSerializable(MARVEL_RESPONSE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comic_detail_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView coverImage = view.findViewById(R.id.coverImageView);
        TextView titleTextView = view.findViewById(R.id.comicTitleTextView);
        TextView publishedTextView = view.findViewById(R.id.comicPublishedValueTextView);
        TextView writerTextView = view.findViewById(R.id.comicWriterValueTextView);
        TextView pencillerTextView = view.findViewById(R.id.comicPencillerValueTextView);
        TextView coverArtistTextView = view.findViewById(R.id.comicCoverArtistValueTextView);
        TextView descriptionTextView = view.findViewById(R.id.comicDescriptionValueTextView);

        String path = marvelResponse.getData().getResults().get(0).getImages().get(0).getPath().substring(4);
        String extension = marvelResponse.getData().getResults().get(0).getImages().get(0).getExtension();
        String url = "https" + path + "." + extension;
        Picasso.get().load(url).into(coverImage);

        String titleValue = marvelResponse.getData().getResults().get(0).getTitle();
        titleTextView.setText(titleValue);

        String publishedValue = marvelResponse.getData().getResults().get(0).getDates().get(0).getDate();
        String formattedDate = null;
        try {
            String oldDatePattern = "yyyy-MM-dd'T'HH:mm:ss-SSS";
            String newDatePattern = "MMMM dd, yyyy";
            Date date = new SimpleDateFormat(oldDatePattern).parse(publishedValue);
            formattedDate = new SimpleDateFormat(newDatePattern).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        publishedTextView.setText(formattedDate);

        List<Item> creatorRoles = marvelResponse.getData().getResults().get(0).getCreators().getItems();
        for (Item i : creatorRoles) {
            if (i.getRole().contains("writer")) {
                writerTextView.setText(i.getName());
            }
            if (i.getRole().contains("penciller")) {
                pencillerTextView.setText(i.getName());
            }
            if (i.getRole().contains("cover")) {
                coverArtistTextView.setText(i.getName());
            }
        }

        String descriptionValue = marvelResponse.getData().getResults().get(0).getDescription();

        descriptionTextView.setText(descriptionValue);
    }
}
