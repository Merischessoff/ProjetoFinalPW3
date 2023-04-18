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

import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.projetofinalpw3.util.Util;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroHistoriaSocialFragment extends Fragment {
    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 123;
    private ImageView foto;
    private Button btnCadastrar;
    private AppCompatSpinner spinnerAvd;
    private AppCompatSpinner spinnerHabSoc;
    private Button btnSelecionaImg;

    private Map<Integer, Uri> imagensId = new HashMap<Integer,Uri>();

    private List<Integer> textosId = new ArrayList<Integer>();
    private View root;
    private TextView textSelecionado;
    private APIInterface apiInterface;

    private String token;

    private String email;
    private GridLayout imageContainer;
    private Util util;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        root = inflater.inflate(R.layout.fragment_historia_social_cadastrar, container, false);
        Bundle bundle = getArguments();
        imageContainer = root.findViewById(R.id.image_grid);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Intent intent = getActivity().getIntent();
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_READ_EXTERNAL_STORAGE);
        }else {
            // Acesso permitido, você pode prosseguir com o código que estava tentando executar
        }

        spinnerAvd = root.findViewById(R.id.spinnerAtividadeVidaDiaria);
        spinnerHabSoc = root.findViewById(R.id.spinnerHabilidadeSocial);

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
                AtividadeDeVidaDiaria avd = null;
                HabilidadeSocial hs = null;
                if(!spinnerAvd.getSelectedItem().toString().trim().equalsIgnoreCase("")){
                    avd = new AtividadeDeVidaDiaria();
                    avd.setId((long) spinnerAvd.getSelectedItemPosition()+1);
                    Log.e("posicao spinner ", String.valueOf(avd.getId()));
                    avd.setDescricao(spinnerAvd.getSelectedItem().toString());
                    avd.setNome(spinnerAvd.getSelectedItem().toString());
                }
                if(!spinnerHabSoc.getSelectedItem().toString().trim().equalsIgnoreCase("")) {
                    hs = new HabilidadeSocial();
                    hs.setId((long) spinnerHabSoc.getSelectedItemPosition()+1);
                    Log.e("posicao spinner ", String.valueOf(hs.getId()));
                    hs.setNome(spinnerHabSoc.getSelectedItem().toString());
                    hs.setDescricao(spinnerHabSoc.getSelectedItem().toString());
                }
                List<Imagem> imagens = new ArrayList<Imagem>();

                String titulo = ((EditText)root.findViewById(R.id.txtTituloHistoriaSocial)).getText().toString();
                String texto = ((EditText)root.findViewById(R.id.txtTextoHistoriaSocial)).getText().toString();

                int i = 0;
                for (Map.Entry<Integer, Uri> entry : imagensId.entrySet()) {
                    Imagem img = new Imagem();
                    img.setSeq(i+1);
                    img.setUrl(entry.getValue().toString());

                    Log.e("uri ", entry.getValue().toString());

                    TextInputEditText t = root.findViewById(textosId.get(i));
                    img.setTexto(t.getText().toString());

                    Log.e("textos ", t.getText().toString());
                    imagens.add(img);
                    i++;
                }

                HistoriaSocial historiaSocial = new HistoriaSocial()
                        .withEmail(email)
                        .withTitulo(titulo)
                        .withTexto(texto)
                        .withHabilidadeSocial(hs)
                        .withAtividadeDeVidaDiaria(avd)
                        .withImagens(imagens)
                        .build();

                Log.e("token ", token);
                Log.e("email ", email);
                Log.e("historiaSocial ", historiaSocial.toString());
                Call<HistoriaSocial> call = apiInterface.cadastroHistoriaSocial(token, historiaSocial);
                call.enqueue(new Callback<HistoriaSocial>() {
                    @Override
                    public void onResponse(Call<HistoriaSocial> call, Response<HistoriaSocial> response) {
                        Log.e("onResponse onClick ", "salvando historia social " + response.body());
                        Snackbar.make(v, "História salva com sucesso!", Snackbar.LENGTH_LONG)
                                .setTextColor(Color.GREEN).show();
                        Navigation.findNavController(v).navigate(R.id.nav_fragment_historia_social_List);
                    }

                    @Override
                    public void onFailure(Call<HistoriaSocial> call, Throwable t) {
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

                        for (Uri uri : result) {
                            ImageView imageView = new ImageView(requireContext());
                            TextInputEditText textoHist = new TextInputEditText(requireContext());

                            imageView.setLayoutParams(
                                    new LinearLayout.LayoutParams(150,150)
                            );
                            textoHist.setLayoutParams(
                                    new LinearLayout.LayoutParams(150, 150)
                            );
                            textoHist.setId(util.geraId());
                            imageView.setId(util.geraId());
                            imageView.setImageURI(uri);
                            imageContainer.addView(imageView);
                            imageContainer.addView(textoHist);
                            imageContainer.setId(util.geraId());
                            imagensId.put(imageView.getId(), uri);
                            textosId.add(textoHist.getId());
                        }
                    }
                }
            });

}
