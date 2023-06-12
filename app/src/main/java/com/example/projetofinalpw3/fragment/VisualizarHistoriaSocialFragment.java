package com.example.projetofinalpw3.fragment;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.model.AtividadeDeVidaDiaria;
import com.example.projetofinalpw3.model.HabilidadeSocial;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.example.projetofinalpw3.model.Imagem;
import com.example.projetofinalpw3.model.Img;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;
import com.example.projetofinalpw3.util.Util;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VisualizarHistoriaSocialFragment extends Fragment {
    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 123;
    private List<Imagem> listaImagens;
    private Map<Integer, Imagem> imagensId = new HashMap<Integer,Imagem>();
    private View root;
    private TextView tituloHistoria;
    private TextView textoHistoria;
    private GridLayout imageContainer;
    private Util util;

    private ImageButton proximo;
    private int atual;

    private ImageView atualView;
    private ImageButton anterior;
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_historia_social_visualizar, container, false);
        Bundle bundle = getArguments();

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_READ_EXTERNAL_STORAGE);
        }else {
            listaImagens = bundle.getParcelableArrayList("listaImagens");

            imageContainer = root.findViewById(R.id.imagemPricipal);

            for (int i = 0; i < listaImagens.size(); i++) {
                if(i==0) {
                    Imagem img = listaImagens.get(i);
                    ImageView imageView = new ImageView(requireContext());
                    imageView.setId(util.geraId());

                    if(img.getUrl().contains("gs://projetofinalpw3.appspot.com")){
                        try {
                            getImagensFirebase(imageView, img);
                        }catch (Exception e){
                            Log.e("Exception", e.toString());
                        }
                    }else {
                        Glide.with(requireContext()).load(img.getUrl()).into(imageView);
                    }

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
                }else{
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

                        if(img.getUrl().contains("gs://projetofinalpw3.appspot.com")){
                            try {
                                getImagensFirebase(imageView, img);
                            }catch (Exception e){
                                Log.e("Exception", e.toString());
                            }
                        }else {
                            Glide.with(requireContext()).load(img.getUrl()).into(imageView);
                        }

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
                    }else{

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

                        if(img.getUrl().contains("gs://projetofinalpw3.appspot.com")){
                            try {
                                getImagensFirebase(imageView, img);
                            }catch (Exception e){
                                Log.e("Exception", e.toString());
                            }
                        }else {
                            Glide.with(requireContext()).load(img.getUrl()).into(imageView);
                        }

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
    public void getImagensFirebase(ImageView imageView, Imagem img){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null){
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference gsReference = storage.getReferenceFromUrl(img.getUrl());
            gsReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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
        }else {
            Log.e("USER", "User is not logged in");
        }

    }
}