package com.example.projetofinalpw3.fragment;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class EditarHistoriaSocialFragment extends Fragment {
    private TextInputEditText txtSeq;
    private TextInputEditText txtTitulo;
    private TextInputEditText txtTexto;
    private TextInputEditText txtTextoURL;
    private ImageView imagemUpload;
    private Button btnEditar;
    private String key;
    private Uri uri;
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_historia_social_editar, container, false);
        Bundle bundle = getArguments();

        return root;
    }

}