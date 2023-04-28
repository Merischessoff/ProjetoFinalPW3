package com.example.projetofinalpw3.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projetofinalpw3.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;


public class ListaBancoDeHistoriasFragment extends Fragment {

    private View root;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_banco_de_historias, container, false);
        Bundle bundle = getArguments();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a reference to a file from a Cloud Storage URI
        StorageReference gsReference = storage.getReferenceFromUrl("gs://projetofinalpw3.appspot.com/feliz.jpg");
        File localFile = new File(getActivity().getExternalFilesDir(null), "feliz.jpg");

        gsReference.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Arquivo baixado com sucesso, faça o que quiser com ele
                        Log.d("TAG", "Arquivo baixado com sucesso!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Ocorreu um erro ao baixar o arquivo
                        Log.e("TAG", "Erro ao baixar o arquivo.", exception);
                    }
                });


        return root;
    }


}