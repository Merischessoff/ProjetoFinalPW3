package com.example.projetofinalpw3.fragment;

import android.content.DialogInterface;
import android.graphics.Bitmap;
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

import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.model.HabilidadeSocial;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URI;

public class HistoriaSocialEditFragment extends Fragment {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_historia_social_edit, container, false);
        Bundle bundle = getArguments();
        txtSeq = root.findViewById(R.id.txtSeqHistoriaSocialEdit);
        txtSeq.setText(bundle.getString("SEQ"));
        String url = bundle.getString("URL");
        txtTitulo = root.findViewById(R.id.txtTituloHistoriaSocialEdit);
        txtTitulo.setText(bundle.getString("TITULO"));
        txtTexto = root.findViewById(R.id.txtTextoHistoriaSocialEdit);
        txtTexto.setText(bundle.getString("TEXTO"));
        imagemUpload = root.findViewById(R.id.uploadEdit);
        txtTextoURL = root.findViewById(R.id.txtSeqHistoriaSocialURLEdit);
        txtTextoURL.setText(url);

        /*if(url.contains("content")){
            try {
                Bitmap thumbnail = getActivity().getApplicationContext().getContentResolver().loadThumbnail(Uri.parse(url), new Size(400, 400), null);
                imagemUpload.setImageBitmap(thumbnail);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            carregaImagemURL(bundle.getString("URL"));
        }*/

        imagemUpload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mGetContent.launch("image/*");
            }
        });



        btnEditar = root.findViewById(R.id.btnEditar);
        key = bundle.getString("ID");

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarItem();
            }
        });
        return root;
    }
    private void editarItem() {
        new AlertDialog.Builder(getContext())
                .setTitle("Editando história")
                .setMessage("Tem certeza que deseja editar essa história?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("HistoriaSocial").child(key);
                        String url = "";
                        if(uri != null){
                            url = uri.toString();
                        }else{
                            url = txtTextoURL.getText().toString();
                        }
                        HistoriaSocial h = new HistoriaSocial(txtSeq.getText().toString(),
                                txtTitulo.getText().toString(),
                                txtTexto.getText().toString(),
                                url);

                        reference.setValue(h);
                        Snackbar.make(getView(), "item editado!!!", Snackbar.LENGTH_LONG).show();
                        Navigation.findNavController(getView()).navigate(R.id.nav_fragment_historia_social_List);
                    }
                }).setNegativeButton("Não", null).show();
    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
      @Override
      public void onActivityResult(Uri result) {
          if(result != null){
              imagemUpload.setImageURI(result);
              uri = result;
          }
      }
    });

    public void carregaImagemURL(String url){
        Picasso.with(getActivity().getBaseContext())
                .load(url) // Equivalent of what ends up in onBitmapLoaded
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_baseline_error_24)
                .centerCrop()
                .fit()
                .into(imagemUpload);
    }
}