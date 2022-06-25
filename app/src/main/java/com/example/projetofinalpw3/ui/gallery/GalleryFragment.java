package com.example.projetofinalpw3.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.databinding.FragmentGalleryBinding;
import com.squareup.picasso.Picasso;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        ImageView imageView = binding.imageView;
        Picasso.with(getActivity().getBaseContext())
                .load("https://i.postimg.cc/HsdCJjjf/HS-Contato-visual-exercicio-1.png") // Equivalent of what ends up in onBitmapLoaded
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_baseline_error_24)
                .centerCrop()
                .fit()
                .into(imageView);*/

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}