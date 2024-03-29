package com.example.projetofinalpw3.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.dto.UsuarioDTO;
import com.example.projetofinalpw3.dto.UsuarioEditarDTO;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.example.projetofinalpw3.model.TipoUsuario;
import com.example.projetofinalpw3.model.Usuario;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;
import com.example.projetofinalpw3.util.SenhaUtil;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditarUsuarioLeitorFragment extends Fragment {

    private TextInputEditText textNome;
    private TextInputEditText textCpf;
    private TextInputEditText textEmail;
    private TextInputEditText textSenha;
    private TextInputEditText textConfSenha;
    private String key;
    private Button btnEditar;

    private Button btnCancelar;
    private APIInterface apiInterface;

    private String email;

    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editar_usuario_leitor, container, false);
        Bundle bundle = getArguments();

        apiInterface = APIClient.getClient().create(APIInterface.class);

        textNome = root.findViewById(R.id.edtNomeUsuarioLeitorVinculado);
        textNome.setText(bundle.getString("nome"));

        textSenha = root.findViewById(R.id.edtSenhaUsuarioLeitorVinculado);
        //textSenha.setText(bundle.getString("senha"));

        textConfSenha = root.findViewById(R.id.edtConfSenhaUsuarioLeitorVinculado);
        //textConfSenha.setText(bundle.getString("senha"));
        email = bundle.getString("emailUsuarioLeitor");

        Intent intent = getActivity().getIntent();
        token = intent.getStringExtra("token");

        btnEditar = root.findViewById(R.id.btnEditarUsuarioVinculado);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarUsuarioLeitor();
            }
        });

        btnCancelar = root.findViewById(R.id.btnCancelarUsuarioLeitorVinculado);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.nav_lista_usuario_leitor_fragment);
            }
        });
        return root;
    }

    public void editarUsuarioLeitor(){
        new AlertDialog.Builder(getContext())
                .setTitle("Editando usuário")
                .setMessage("Tem certeza que deseja editar esse usuário?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (!textNome.getText().toString().equals("")
                                && !textSenha.getText().toString().equals("")
                                && !textConfSenha.getText().toString().equals("")) {
                            if (textSenha.getText().toString().equals(textConfSenha.getText().toString())) {

                                UsuarioEditarDTO usu = new UsuarioEditarDTO(textNome.getText().toString(), SenhaUtil.criptografarSenha(textSenha.getText().toString()));

                                Log.e("usuario ", "usu " + " " + token + " " + " " + email + " " + usu);

                                Call<UsuarioEditarDTO> call = apiInterface.editaUsuarioLeitor(token, email, usu);
                                call.enqueue(new Callback<UsuarioEditarDTO>() {
                                    @Override
                                    public void onResponse(Call<UsuarioEditarDTO> call, Response<UsuarioEditarDTO> response) {
                                        Log.e("onResponse ", "editarUsuarioLeitor " + response.body());
                                        Snackbar.make(getView(), "Usuario editado com sucesso!", Snackbar.LENGTH_LONG)
                                                .setTextColor(Color.BLUE).show();
                                        Navigation.findNavController(getView()).navigate(R.id.nav_lista_usuario_leitor_fragment);
                                    }

                                    @Override
                                    public void onFailure(Call<UsuarioEditarDTO> call, Throwable t) {
                                        call.cancel();
                                        Log.e("post api", "entrou no onFailure" + t.getMessage());
                                    }
                                });
                            } else {
                                Snackbar.make(getView(), "Senha e confirmação de senha devem ser iguais!", Snackbar.LENGTH_LONG)
                                        .setTextColor(Color.RED).show();
                            }
                        } else{
                            Snackbar.make(getView(), "Dados inválidos!!!", Snackbar.LENGTH_LONG).show();
                        }
                    }
                }).setNegativeButton("Não", null).show();
    }

}