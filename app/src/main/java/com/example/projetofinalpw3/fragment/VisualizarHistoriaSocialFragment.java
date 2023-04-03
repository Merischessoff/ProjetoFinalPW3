package com.example.projetofinalpw3.fragment;

import android.Manifest;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.databinding.FragmentHistoriaSocialVisualizarBinding;
import com.example.projetofinalpw3.ui.gallery.GalleryViewModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;


public class VisualizarHistoriaSocialFragment extends Fragment {
    private ImageView foto;
    private Uri uri;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MANAGE_DOCUMENTS};

        View root = inflater.inflate(R.layout.fragment_historia_social_visualizar, container, false);
        Bundle bundle = getArguments();

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}