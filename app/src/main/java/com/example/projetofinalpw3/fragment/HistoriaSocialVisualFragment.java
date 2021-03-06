package com.example.projetofinalpw3.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.ParcelFileDescriptor;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.databinding.FragmentHistoriaSocialVisualBinding;
import com.example.projetofinalpw3.ui.gallery.GalleryViewModel;
import com.squareup.picasso.Picasso;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;


public class HistoriaSocialVisualFragment extends Fragment {
    private ImageView foto;
    private Uri uri;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MANAGE_DOCUMENTS};

        View root = inflater.inflate(R.layout.fragment_historia_social_visual, container, false);
        Bundle bundle = getArguments();

        TextView txtNomeHistorialSocial = root.findViewById(R.id.txtTextoHistoriaSocialVis);
        txtNomeHistorialSocial.setText(bundle.getString("TEXTO"));
        String url = bundle.getString("URL");

        foto = root.findViewById(R.id.imageHistoriaSocialVis);
        if(url.contains("content")){
            try {
                Bitmap thumbnail = getActivity().getApplicationContext().getContentResolver().loadThumbnail(Uri.parse(url), new Size(400, 400), null);
                foto.setImageBitmap(thumbnail);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            carregaImagemURL(url);
        }
        return root;
    }

    public void carregaImagemURL(String url){
        Picasso.with(getActivity().getBaseContext())
                .load(url) // Equivalent of what ends up in onBitmapLoaded
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_baseline_error_24)
                .centerCrop()
                .fit()
                .into(foto);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}