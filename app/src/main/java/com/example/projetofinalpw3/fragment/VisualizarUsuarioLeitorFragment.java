package com.example.projetofinalpw3.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.retrofit.APIInterface;
import com.google.android.material.textfield.TextInputEditText;


public class VisualizarUsuarioLeitorFragment extends Fragment {
    private TextInputEditText textNome;
    private TextInputEditText textCpf;
    private TextInputEditText textEmail;
    private TextInputEditText textSenha;
    private Button btnOk;

    private Button btnAssociar;
    private Button btnAssociarBanco;

    private Button btnDesassociar;

    private Button btnDesassociarBanco;

    private Button btnok;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_visualizar_usuario_leitor, container, false);
        Bundle bundle = getArguments();

        textNome = root.findViewById(R.id.visualNomeUsuarioLeitor);
        textNome.setText(bundle.getString("nome"));

        textEmail = root.findViewById(R.id.visualEmailUsuarioLeitor);
        textEmail.setText(bundle.getString("emailUsuarioLeitor"));

        btnOk = root.findViewById(R.id.btnOkUsuarioLeitorU);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.createNavigateOnClickListener(R.id.nav_home);
            }
        });

        btnAssociar = root.findViewById(R.id.btnVincularHistoriasU);
        btnAssociar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_lista_historias_associar_fragment, bundle);
            }
        });

        btnDesassociar = root.findViewById(R.id.btnDesvincularHistoriasU);
        btnDesassociar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_lista_historias_desassociar_fragment, bundle);
            }
        });

        btnAssociarBanco = root.findViewById(R.id.btnVincularBancoHistoriasU);
        btnAssociarBanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_lista_banco_historias_associar_fragment,  bundle);
            }
        });

        btnDesassociarBanco = root.findViewById(R.id.btnDesvincularBancoHistoriasU);
        btnDesassociarBanco.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Navigation.findNavController(v).navigate(R.id.nav_lista_banco_historias_desassociar_fragment, bundle);
            }
        });
        return root;
    }
}