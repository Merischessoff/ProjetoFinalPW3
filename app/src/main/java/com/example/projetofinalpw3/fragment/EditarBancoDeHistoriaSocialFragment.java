package com.example.projetofinalpw3.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.model.AtividadeDeVidaDiaria;
import com.example.projetofinalpw3.model.HabilidadeSocial;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.example.projetofinalpw3.model.Imagem;
import com.example.projetofinalpw3.model.Img;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;
import com.example.projetofinalpw3.util.APIUtil;
import com.example.projetofinalpw3.util.FirebaseUtil;
import com.example.projetofinalpw3.util.Util;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditarBancoDeHistoriaSocialFragment extends Fragment {
    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 123;
    private ImageView foto;
    private Button btnEditar;
    private AppCompatSpinner spinnerAvd;
    private AppCompatSpinner spinnerHabSoc;
    private Button btnSelecionaImg;
    private List<Imagem> listaImagens;
    private Map<Integer, Img> imagensId = new HashMap<Integer,Img>();
    private Map<Integer, Integer> imagensIdPos = new HashMap<Integer,Integer>();
    private View root;
    private TextView textSelecionado;
    private APIInterface apiInterface;
    private String token;
    private String emailUsuarioResponsavel;
    private boolean achou = false;
    private String id;
    private String titulo;

    private String texto;
    private TextInputEditText tituloHistoria;
    private TextInputEditText textoHistoria;
    private GridLayout imageContainer;
    private TextInputEditText t;
    private ActivityResultLauncher<String> requestPermissionLauncher;

    private Long idHistoria;
    private Util util;
    private APIUtil apiUtil;
    private FirebaseUtil firebaseUtil;


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_banco_de_historia_social_editar, container, false);
        Bundle bundle = getArguments();

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_READ_EXTERNAL_STORAGE);
        }else {
            apiInterface = APIClient.getClient().create(APIInterface.class);
            Intent intent = getActivity().getIntent();
            token = intent.getStringExtra("token");
            emailUsuarioResponsavel = bundle.getString("email");

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
                        deletaImagem(imageView.getId(), deleteButton.getId() , root);
                    }
                });
                deleteButton.setText("X");
                deleteButton.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
                imageView.setLayoutParams(new LinearLayout.LayoutParams(200,200));
                textoHist.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
                textoHist.setText(img.getTexto());

                try {
                    getImagensFirebase(imageView, img);
                }catch (Exception e){
                    Log.e("Exception", e.toString());
                }
                imageContainer.addView(imageView);
                imageContainer.addView(textoHist);
                imageContainer.addView(deleteButton);

                Img imgAux = new Img();
                imgAux.setUri(Uri.parse(img.getUrl()));
                imgAux.setIdImagem(imageView.getId());
                imgAux.setIdTexto(textoHist.getId());
                imagensId.put(imageView.getId(), imgAux);
                imagensIdPos.put(imageView.getId(),j);
                j++;
            }

            tituloHistoria = root.findViewById(R.id.txtTituloHistoriaSocial);
            textoHistoria = root.findViewById(R.id.txtTextoHistoriaSocial);
            tituloHistoria.setText(bundle.getString("titulo"));
            textoHistoria.setText(bundle.getString("texto"));

            spinnerAvd = root.findViewById(R.id.spinnerAtividadeVidaDiaria);
            String[] itens = getResources().getStringArray(R.array.itensAtividadeVidaDiariaNome);
            int pos = -1;
            if(listaAtividades.size()>0){
                for (int i = 0; i < itens.length; i++) {
                    if (itens[i].equals(listaAtividades.get(0).getNome())) {
                        pos = i;
                        break;
                    }
                }
                if (pos != -1) {
                    spinnerAvd.setSelection(pos);
                }
            }
            spinnerHabSoc = root.findViewById(R.id.spinnerHabilidadeSocial);
            String[] itens2 = getResources().getStringArray(R.array.itensHabilidadeSocialNome);
            int pos2 = -1;
            if(listaHabilidadesSociais.size()>0) {
                for (int i = 0; i < itens2.length; i++) {
                    if (itens2[i].equals(listaHabilidadesSociais.get(0).getNome())) {
                        pos2 = i;
                        break;
                    }
                }
                if (pos2 != -1) {
                    spinnerHabSoc.setSelection(pos2);
                }
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
                        avd.setId((long) spinnerAvd.getSelectedItemPosition()+1);
                        //Log.e("posicao spinner ", String.valueOf(avd.getId()));
                        avd.setDescricao(spinnerAvd.getSelectedItem().toString());
                        avd.setNome(spinnerAvd.getSelectedItem().toString());
                    }
                    if(!spinnerHabSoc.getSelectedItem().toString().trim().equalsIgnoreCase("")) {
                        hs = new HabilidadeSocial();
                        hs.setId((long) spinnerHabSoc.getSelectedItemPosition()+1);
                        //Log.e("posicao spinner ", String.valueOf(hs.getId()));
                        hs.setNome(spinnerHabSoc.getSelectedItem().toString());
                        hs.setDescricao(spinnerHabSoc.getSelectedItem().toString());
                    }
                    List<Imagem> imagens = new ArrayList<Imagem>();

                    String titulo = ((EditText)root.findViewById(R.id.txtTituloHistoriaSocial)).getText().toString();
                    String texto = ((EditText)root.findViewById(R.id.txtTextoHistoriaSocial)).getText().toString();
                    int i=1;
                    for (Map.Entry<Integer, Img> entry : imagensId.entrySet()) {
                        Imagem img = new Imagem();
                        img.setSeq(i);
                        img.setUrl(entry.getValue().getUri().toString());
                        TextInputEditText t = root.findViewById(entry.getValue().getIdTexto());
                        img.setTexto(t.getText().toString());
                        imagens.add(img);
                        i++;
                    }
                    HistoriaSocial historiaSocial = new HistoriaSocial()
                            .withEmail(emailUsuarioResponsavel)
                            .withTitulo(titulo)
                            .withTexto(texto)
                            .withHabilidadeSocial(hs)
                            .withAtividadeDeVidaDiaria(avd)
                            .withImagens(imagens)
                            .build();

                    Log.e("objeto historia social ", "id: " + idHistoria + " historia " + historiaSocial.toString());

                    apiUtil.cadastraHistoriaSocial(token, historiaSocial, v);

                }
            });
        }
        return root;
    }

    private void deletaImagem(int imageViewID, int deleteButtonId, View root) {
        Img img = new Img();
        int posImg = imagensIdPos.get(imageViewID);

        ImageView imgView = root.findViewById(imageViewID);
        imageContainer.removeView(imgView);

        img = imagensId.get(imageViewID);

        //Log.e("Img ", "aqui " + img.toString());
        TextView text = root.findViewById(img.getIdTexto());
        imageContainer.removeView(text);

        Button btn = root.findViewById(deleteButtonId);
        imageContainer.removeView(btn);

        imagensId.remove(imageViewID);
        imagensIdPos.remove(posImg);

    }

    ActivityResultLauncher<String[]> mGetMultipleContentsLauncher = registerForActivityResult(
            new ActivityResultContracts.OpenMultipleDocuments(),
            new ActivityResultCallback<List<Uri>>() {
                @Override
                public void onActivityResult(List<Uri> result) {
                    if (result != null && !result.isEmpty()) {
                        int d = 0;
                        for (Uri uri : result) {
                            ImageView imageView = new ImageView(requireContext());
                            TextInputEditText textoHist = new TextInputEditText(requireContext());
                            Button deleteButton = new Button(requireContext());
                            deleteButton.setId(util.geraId());
                            textoHist.setId(util.geraId());
                            imageView.setId(util.geraId());
                            //imageContainer.setId(util.geraId());
                            deleteButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    deletaImagem(imageView.getId(), deleteButton.getId() , getView());
                                }
                            });
                            deleteButton.setText("X");
                            deleteButton.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
                            imageView.setLayoutParams(
                                    new LinearLayout.LayoutParams(150,150)
                            );
                            textoHist.setLayoutParams(
                                    new LinearLayout.LayoutParams(150, 150)
                            );

                            imageView.setImageURI(uri);

                            imageContainer.addView(imageView);
                            imageContainer.addView(textoHist);
                            imageContainer.addView(deleteButton);

                            Img img = new Img();
                            img.setUri(uri);
                            img.setIdImagem(imageView.getId());
                            img.setIdTexto(textoHist.getId());

                            imagensId.put(imageView.getId(), img);
                            imagensIdPos.put(imageView.getId(),d);

                            d++;
                        }
                    }
                }
            });

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
        }

    }
}