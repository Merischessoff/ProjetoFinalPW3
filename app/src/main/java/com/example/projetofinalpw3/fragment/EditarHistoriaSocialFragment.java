package com.example.projetofinalpw3.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.model.AtividadeDeVidaDiaria;
import com.example.projetofinalpw3.model.HabilidadeSocial;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.example.projetofinalpw3.model.Imagem;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;
import com.example.projetofinalpw3.util.Util;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarHistoriaSocialFragment extends Fragment {
    private static final int MY_PERMISSIONS_INTERNET = 123;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private ImageView foto;
    private Button btnEditar;
    private AppCompatSpinner spinnerAvd;
    private AppCompatSpinner spinnerHabSoc;
    private Button btnSelecionaImg;
    private List<Imagem> listaImagens;
    private Map<Integer, Uri> imagensId = new HashMap<Integer,Uri>();
    private Map<Integer, Integer> imagensIdPos = new HashMap<Integer,Integer>();
    private Map<Integer, Integer> textosIdPos = new HashMap<Integer,Integer>();
    private List<Integer> textosId = new ArrayList<Integer>();
    private View root;
    private TextView textSelecionado;
    private APIInterface apiInterface;
    private String token;
    private String email;
    private boolean achou = false;
    private String id;
    private String titulo;

    private String texto;
    private TextInputEditText tituloHistoria;
    private TextInputEditText textoHistoria;
    private GridLayout imageContainer;
    private TextInputEditText t;
    private ActivityResultLauncher<String> requestPermissionLauncher;
    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 123;

    private Util util;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_historia_social_editar, container, false);
        Bundle bundle = getArguments();

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_READ_EXTERNAL_STORAGE);
        }else {
            // Acesso permitido, você pode prosseguir com o código que estava tentando executar
        }

        apiInterface = APIClient.getClient().create(APIInterface.class);

        Intent intent = getActivity().getIntent();
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");

        List<AtividadeDeVidaDiaria> listaAtividades = bundle.getParcelableArrayList("listaAvd");
        List<HabilidadeSocial> listaHabilidadesSociais = bundle.getParcelableArrayList("listaHs");
        listaImagens = bundle.getParcelableArrayList("listaImagens");

        imageContainer = root.findViewById(R.id.image_grid);
        int j = 0;
        for (Imagem img: listaImagens) {
            ImageView imageView = new ImageView(requireContext());
            TextInputEditText textoHist = new TextInputEditText(requireContext());
            Button deleteButton = new Button(requireContext());

            textoHist.setId(util.geraId());
            imageView.setId(util.geraId());
            imageContainer.setId(util.geraId());
            deleteButton.setId(util.geraId());
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletaImagem(imageView.getId(), textoHist.getId(), deleteButton.getId() , root);
                }
            });
            deleteButton.setText("X");
            deleteButton.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
            imageView.setLayoutParams(new LinearLayout.LayoutParams(200,200));
            textoHist.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
            textoHist.setText(img.getTexto());

            Glide.with(requireContext()).load(img.getUrl()).into(imageView);
            imageContainer.addView(imageView);
            imageContainer.addView(textoHist);
            imageContainer.addView(deleteButton);

            imagensId.put(imageView.getId(), Uri.parse(img.getUrl()));
            imagensIdPos.put(imageView.getId(),j);
            textosId.add(textoHist.getId());
            textosIdPos.put(textoHist.getId(),j);
            j++;
        }

        //começa a carregar o titulo e o texto do bundle que foi buscado com o adapter
        tituloHistoria = root.findViewById(R.id.txtTituloHistoriaSocial);
        textoHistoria = root.findViewById(R.id.txtTextoHistoriaSocial);
        tituloHistoria.setText(bundle.getString("titulo"));
        textoHistoria.setText(bundle.getString("texto"));

        spinnerAvd = root.findViewById(R.id.spinnerAtividadeVidaDiaria);
        String[] itens = getResources().getStringArray(R.array.itensAtividadeVidaDiariaNome);
        int pos = -1;
        for (int i = 0; i < itens.length; i++) {
                if (itens[i].equals(listaAtividades.get(0).getNome())) {
                    pos = i;
                    break;
                }
        }
        if (pos != -1) {
            spinnerAvd.setSelection(pos);
        }
        spinnerHabSoc = root.findViewById(R.id.spinnerHabilidadeSocial);
        String[] itens2 = getResources().getStringArray(R.array.itensHabilidadeSocialNome);
        int pos2 = -1;
        for (int i = 0; i < itens2.length; i++) {
            if (itens2[i].equals(listaHabilidadesSociais.get(0).getNome())) {
                pos2 = i;
                break;
            }
        }
        if (pos2 != -1) {
            spinnerHabSoc.setSelection(pos2);
        }

        btnSelecionaImg = root.findViewById(R.id.btnSelecionaImagens);
        btnSelecionaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetMultipleContentsLauncher.launch(new String[]{"image/*"});
            }
        });

        btnEditar = root.findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AtividadeDeVidaDiaria avd = null;
                HabilidadeSocial hs = null;
                if(!spinnerAvd.getSelectedItem().toString().trim().equalsIgnoreCase("")){
                    avd = new AtividadeDeVidaDiaria();
                    avd.setDescricao(spinnerAvd.getSelectedItem().toString());
                    avd.setNome(spinnerAvd.getSelectedItem().toString());
                }
                if(!spinnerHabSoc.getSelectedItem().toString().trim().equalsIgnoreCase("")) {
                    hs = new HabilidadeSocial();
                    hs.setNome(spinnerAvd.getSelectedItem().toString());
                    hs.setDescricao(spinnerAvd.getSelectedItem().toString());
                }
                List<Imagem> imagens = new ArrayList<Imagem>();

                int i = 0;
                for (Map.Entry<Integer, Uri> entry : imagensId.entrySet()) {
                    Imagem img = new Imagem();
                    img.setSeq(i+1);
                    img.setUrl(entry.getValue().toString());

                    Log.e("uri ", entry.getValue().toString());

                    t = root.findViewById(textosId.get(i));
                    img.setTexto(t.getText().toString());

                    Log.e("textos ", t.getText().toString());

                    imagens.add(img);
                    i++;
                }

                HistoriaSocial historiaSocialNova = new HistoriaSocial()
                        .withEmail(email)
                        .withTitulo(((EditText)root.findViewById(R.id.txtTituloHistoriaSocial)).getText().toString())
                        .withTexto(((EditText)root.findViewById(R.id.txtTextoHistoriaSocial)).getText().toString())
                        .withHabilidadeSocial(hs)
                        .withAtividadeDeVidaDiaria(avd)
                        .withImagens(imagens)
                        .build();

                Log.e("token ", token);
                Log.e("email ", email);

                Call<HistoriaSocial> call = apiInterface.cadastroHistoriaSocial(token, historiaSocialNova);
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

    private void deletaImagem(int imageViewID, int textoHistID, int deleteButtonId, View root) {
        Log.e("imagens ", "ids "+ imagensId);
        Log.e("textos ", "ids "+ textosId);

        int posImg = imagensIdPos.get(imageViewID);
        imagensId.remove(posImg);
        ImageView img = root.findViewById(imageViewID);
        int posText = textosIdPos.get(textoHistID);
        textosId.remove(posText);
        TextView text = root.findViewById(textoHistID);
        Button btn = root.findViewById(deleteButtonId);
        imageContainer.removeView(img);
        imageContainer.removeView(text);
        imageContainer.removeView(btn);

        Log.e("imagens ", "ids "+ imagensId);
        Log.e("textos ", "ids "+ textosId);

    }

    ActivityResultLauncher<String[]> mGetMultipleContentsLauncher = registerForActivityResult(
            new ActivityResultContracts.OpenMultipleDocuments(),
            new ActivityResultCallback<List<Uri>>() {
                @Override
                public void onActivityResult(List<Uri> result) {
                    if (result != null && !result.isEmpty()) {
                        //imageContainer = root.findViewById(R.id.image_grid);
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