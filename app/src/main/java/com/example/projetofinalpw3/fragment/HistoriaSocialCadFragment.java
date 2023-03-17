package com.example.projetofinalpw3.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.ui.gallery.GalleryViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

public class HistoriaSocialCadFragment extends Fragment {
    private ImageView foto;
    private Button btnCadastrar;
    private com.example.projetofinalpw3.databinding.FragmentHistoriaSocialCadBinding binding;
    private Uri uri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        Intent intent = getActivity().getIntent();
        binding = com.example.projetofinalpw3.databinding.FragmentHistoriaSocialCadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        foto = root.findViewById(R.id.upload);
        foto.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
              mGetContent.launch("image/*");
          }
        });

        btnCadastrar = root.findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String titulo = ((EditText)root.findViewById(R.id.txtTituloHistoriaSocial)).getText().toString();
                String seq = ((EditText)root.findViewById(R.id.txtSeqHistoriaSocial)).getText().toString();
                String texto = ((EditText)root.findViewById(R.id.txtTextoHistoriaSocial)).getText().toString();
                String textoURL = ((EditText)root.findViewById(R.id.txtSeqHistoriaSocialURL)).getText().toString();
                String url = "";
                if(uri != null){
                    url = uri.toString();
                }else{
                    url = textoURL;
                }

                HistoriaSocial historiaSocial = new HistoriaSocial();
                historiaSocial.setSeq(seq);
                historiaSocial.setUrl(url);
                historiaSocial.setTitulo(titulo);
                historiaSocial.setTexto(texto);

                DatabaseReference historias = FirebaseDatabase.getInstance().getReference().child("HistoriaSocial");

                historias.push().setValue(historiaSocial).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Snackbar.make(v, "Cadastrou história!", Snackbar.LENGTH_LONG)
                                .setTextColor(Color.GREEN).show();
                        Navigation.findNavController(v).navigate(R.id.nav_fragment_historia_social_cad);
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(v, "Erro ao cadastrar história!", Snackbar.LENGTH_LONG)
                                        .setTextColor(Color.RED).show();
                            }
                        });
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
                        uri = result;
                    }
                }
            });


    }
