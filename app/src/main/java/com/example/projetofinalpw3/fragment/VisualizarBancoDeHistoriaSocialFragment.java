package com.example.projetofinalpw3.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.model.Imagem;
import com.example.projetofinalpw3.model.Img;
import com.example.projetofinalpw3.util.Util;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VisualizarBancoDeHistoriaSocialFragment extends Fragment {
    private View root;
    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 123;
    private List<Imagem> listaImagens;
    private GridLayout imageContainer;
    private TextView tituloHistoria;
    private TextView textoHistoria;
    private Map<Integer, Imagem> imagensId = new HashMap<Integer,Imagem>();

    private ImageButton proximo;
    private int atual;
    private ImageView atualView;
    private ImageButton anterior;
    private Util util;
    private FirebaseStorage storage;

    private String uriAuxiliar = "";

    private ImageView imageView;

    private Imagem img = new Imagem();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_historia_social_visualizar, container, false);
        Bundle bundle = getArguments();

        storage = FirebaseStorage.getInstance();
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_READ_EXTERNAL_STORAGE);
        }else {
            listaImagens = bundle.getParcelableArrayList("listaImagens");

            imageContainer = root.findViewById(R.id.imagemPricipal);

            for (int i = 0; i < listaImagens.size(); i++) {
                if (i == 0) {
                    img = listaImagens.get(i);
                    imageView = new ImageView(requireContext());
                    imageView.setId(util.geraId());

                    getImagensFirebase();

                    TextView textoImagem = root.findViewById(R.id.textoImgHistoriaSocial);
                    textoImagem.setText(img.getTexto());

                    imagensId.put(i, img);
                    atual = i;
                    GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                    params.width = 200;
                    params.height = 200;
                    params.setMargins(8, 8, 8, 8); // margem entre as imagens
                    params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                    params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                    atualView = imageView;
                    imageContainer.addView(imageView, params);
                } else {
                    Imagem img = listaImagens.get(i);
                    imagensId.put(i, img);
                }
            }

            tituloHistoria = root.findViewById(R.id.tituloHistoriaSocial);
            textoHistoria = root.findViewById(R.id.textoHistoriaSocial);
            tituloHistoria.setText(bundle.getString("titulo"));
            textoHistoria.setText(bundle.getString("texto"));

            proximo = root.findViewById(R.id.proximo);
            proximo.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(atual < listaImagens.size()-1) {
                        Log.e("tamanho lista ", ""+ listaImagens.size());
                        Log.e("atual ", ""+ atual);
                        atual++;
                        Log.e("atual++ ", ""+ atual);
                        imageContainer.removeView(atualView);
                        Imagem img = listaImagens.get(atual);
                        ImageView imageView = new ImageView(requireContext());
                        imageView.setId(util.geraId());

                        getImagensFirebase();

                        TextView textoImagem = root.findViewById(R.id.textoImgHistoriaSocial);
                        textoImagem.setText(img.getTexto());

                        imagensId.put(atual, img);

                        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                        params.width = 200;
                        params.height = 200;
                        params.setMargins(8, 8, 8, 8); // margem entre as imagens
                        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                        params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                        imageContainer.addView(imageView, params);
                        atualView = imageView;
                    }
                }
            });

            anterior = root.findViewById(R.id.anterior);
            anterior.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(atual > 0) {
                        atual--;
                        imageContainer.removeView(atualView);
                        Imagem img = listaImagens.get(atual);
                        ImageView imageView = new ImageView(requireContext());
                        imageView.setId(util.geraId());

                        getImagensFirebase();

                        TextView textoImagem = root.findViewById(R.id.textoImgHistoriaSocial);
                        textoImagem.setText(img.getTexto());

                        imagensId.put(atual, img);

                        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                        params.width = 200;
                        params.height = 200;
                        params.setMargins(8, 8, 8, 8); // margem entre as imagens
                        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                        params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                        imageContainer.addView(imageView, params);
                        atualView = imageView;
                    }
                }
            });
        }
        return root;
    }

    public void getImagensFirebase(){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child(img.getUrl());

        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.e("URI", uri.toString());
                Glide.with(requireContext())
                        .load(uri)
                        .into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("FALHA", exception.toString());
            }
        });
    }
}