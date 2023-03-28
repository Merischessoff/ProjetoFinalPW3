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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_visualizar_usuario_leitor, container, false);
        Bundle bundle = getArguments();

        textNome = root.findViewById(R.id.visualNomeUsuarioLeitorVinculado);
        textNome.setText(bundle.getString("nome"));

        textCpf = root.findViewById(R.id.visualCpfUsuarioLeitorVinculado);
        textCpf.setText(bundle.getString("cpf"));

        textEmail = root.findViewById(R.id.visualEmailUsuarioLeitorVinculado);
        textEmail.setText(bundle.getString("email"));

        //textSenha = root.findViewById(R.id.visualSenhaUsuarioVinculado);
        //textSenha.setText(bundle.getString("senha"));

        btnOk = root.findViewById(R.id.btnOkUsuarioLeitorVinculado);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.nav_lista_usuario_leitor_fragment);
            }
        });
        return root;
    }
}