package com.example.projetofinalpw3.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.databinding.FragmentHomeBinding;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button btnAbrirSite1 = root.findViewById(R.id.btnAbrirSite1);
        btnAbrirSite1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "https://neuroconecta.com.br/recursos-visuais-qual-a-importancia-no-dia-a-dia-de-pessoas-com-autismo"));
                startActivity(intent);
            }
        });

        Button btnAbrirSite2 = root.findViewById(R.id.btnAbrirSite2);
        btnAbrirSite2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "https://www.inspiradospeloautismo.com.br/a-abordagem/atividades-de-vida-diaria-para-pessoas-com-autismo/"));
                startActivity(intent);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}