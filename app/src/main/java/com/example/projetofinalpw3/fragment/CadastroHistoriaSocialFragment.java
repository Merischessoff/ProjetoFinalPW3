package com.example.projetofinalpw3.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.dto.UsuarioEditarDTO;
import com.example.projetofinalpw3.model.AtividadeDeVidaDiaria;
import com.example.projetofinalpw3.model.HabilidadeSocial;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.example.projetofinalpw3.model.Imagem;
import com.example.projetofinalpw3.model.Usuario;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;
import com.example.projetofinalpw3.ui.gallery.GalleryViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroHistoriaSocialFragment extends Fragment {
    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 1;
    private ImageView foto;
    private Button btnCadastrar;
    AppCompatSpinner spinner1;
    AppCompatSpinner spinner2;
    private Button btnSelecionaImg;
    private List<Uri> uriLista = new ArrayList<Uri>();

    private List<String> textos = new ArrayList<String>();
    private View root;
    private TextView textSelecionado;
    private APIInterface apiInterface;

    private String token;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        root = inflater.inflate(R.layout.fragment_historia_social_cadastrar, container, false);
        Bundle bundle = getArguments();

        apiInterface = APIClient.getClient().create(APIInterface.class);

        Intent intent = getActivity().getIntent();
        token = intent.getStringExtra("token");

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_READ_EXTERNAL_STORAGE);
        }

        spinner1 = root.findViewById(R.id.spinnerAtividadeVidaDiaria);
        spinner2 = root.findViewById(R.id.spinnerHabilidadeSocial);

        btnSelecionaImg = root.findViewById(R.id.btnSelecionaImagens);
        btnSelecionaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetMultipleContentsLauncher.launch(new String[]{"image/*"});
            }
        });


        btnCadastrar = root.findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String titulo = ((EditText)root.findViewById(R.id.txtTituloHistoriaSocial)).getText().toString();
                String texto = ((EditText)root.findViewById(R.id.txtTextoHistoriaSocial)).getText().toString();

                HistoriaSocial historiaSocial = new HistoriaSocial();
                AtividadeDeVidaDiaria avd = new AtividadeDeVidaDiaria();
                HabilidadeSocial hs = new HabilidadeSocial();
                List<Imagem> listaImagens = new ArrayList<Imagem>();
                List<AtividadeDeVidaDiaria> listaAvd = new ArrayList<AtividadeDeVidaDiaria>();
                List<HabilidadeSocial> listaHabSoc = new ArrayList<HabilidadeSocial>();

                historiaSocial.setTitulo(titulo);
                historiaSocial.setTexto(texto);

                int i = 0;
                for(Uri uri : uriLista){
                    Imagem img = new Imagem();
                    img.setSeq(i);
                    img.setUrl(uri.toString());
                    img.setTexto(textos.get(i));
                    listaImagens.add(img);
                    i++;
                }
                historiaSocial.setImagens(listaImagens);

                avd.setDescricao(spinner1.getSelectedItem().toString());
                avd.setNome(spinner1.getSelectedItem().toString());
                avd.setHistoriaSocial(historiaSocial);
                listaAvd.add(avd);

                historiaSocial.setAtividadeDeVidaDiarias(listaAvd);

                hs.setNome(spinner2.getSelectedItem().toString());
                hs.setDescricao(spinner2.getSelectedItem().toString());
                hs.setHistoriaSocial(historiaSocial);

                listaHabSoc.add(hs);

                historiaSocial.setHabilidadesSociais(listaHabSoc);


                Call<Usuario> call = apiInterface.cadastroHistoriaSocial(token, historiaSocial);
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        Log.e("onResponse onClick ", "salvando historia social " + response.body());
                        Snackbar.make(getView(), "salvando historia social!", Snackbar.LENGTH_LONG)
                                .setTextColor(Color.GREEN).show();
                        //Navigation.findNavController(getView()).navigate(R.id.nav_lista_usuario_leitor_fragment);
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        call.cancel();
                        Log.e("post api", "entrou no onFailure" + t.getMessage());
                    }
                });

            }
        });

        return root;
    }


    ActivityResultLauncher<String[]> mGetMultipleContentsLauncher = registerForActivityResult(
            new ActivityResultContracts.OpenMultipleDocuments(),
            new ActivityResultCallback<List<Uri>>() {
                @Override
                public void onActivityResult(List<Uri> result) {
                    if (result != null && !result.isEmpty()) {
                        GridLayout imageContainer = root.findViewById(R.id.image_grid);

                        for (Uri uri : result) {
                            ImageView imageView = new ImageView(requireContext());
                            TextInputEditText textoHist = new TextInputEditText(requireContext());

                            imageView.setLayoutParams(
                                    new LinearLayout.LayoutParams(150,150)
                            );
                            textoHist.setLayoutParams(
                                    new LinearLayout.LayoutParams(150, 150)
                            );
                            imageView.setImageURI(uri);
                            imageContainer.addView(imageView);
                            imageContainer.addView(textoHist);
                            uriLista.add(uri);
                        }
                    }
                }
            });


}
