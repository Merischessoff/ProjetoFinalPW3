package com.example.projetofinalpw3.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.Util;
import com.example.projetofinalpw3.databinding.FragmentGalleryBinding;
import com.example.projetofinalpw3.databinding.FragmentHistoriaSocialBinding;
import com.example.projetofinalpw3.ui.gallery.GalleryViewModel;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import java.io.IOException;

public class HistoriaSocialFragment extends Fragment {
    private Button btnBuscaFoto;
    private ImageView foto;
    private Button btnCadastrar;
    private FragmentHistoriaSocialBinding binding;

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        *//*util = new Util(requireActivity().getActivityResultRegistry());
        getLifecycle().addObserver(util);*//*
    }*/

    /*@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        View root = getLayoutInflater().inflate(R.layout.fragment_historia_social, container, false);
        foto = root.findViewById(R.id.upload);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                util.selectImage();
                foto.setImageURI(util.getUri());
            }
        });
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        Intent intent = getActivity().getIntent();
        binding = FragmentHistoriaSocialBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        foto = root.findViewById(R.id.upload);
        foto.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
              mGetContent.launch("image/*");
          }
        });
        return root;


    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if(result != null){
                        foto.setImageURI(result);
                    }
                }
            });


    }
