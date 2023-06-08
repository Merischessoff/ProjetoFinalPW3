package com.example.projetofinalpw3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.projetofinalpw3.dto.UsuarioCadDTO;
import com.example.projetofinalpw3.model.TipoUsuario;
import com.example.projetofinalpw3.model.Usuario;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;
import com.example.projetofinalpw3.util.SenhaUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadUsuarioActivity extends AppCompatActivity {

    //private FirebaseAuth mAuth;
    private TextInputEditText edtEmail;
    private TextInputEditText edtSenha;

    private TextInputEditText edtNome;

    private TextInputEditText edtCpf;
    private TextInputEditText edtConfSenha;
    private Button btnCancelar;
    private Button btnCadastrar;
    private  Usuario usuarioCadastrado;
    private FirebaseAuth mAuth;
    private APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        edtNome = findViewById(R.id.edtNome);
        edtConfSenha = findViewById(R.id.edtConfSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtEmail.getText().toString().equals("")
                        && !edtSenha.getText().toString().equals("")
                        && !edtConfSenha.getText().toString().equals("")
                        && !edtNome.getText().toString().equals("")) {
                    if (edtSenha.getText().toString().equals(edtConfSenha.getText().toString())) {
                        TipoUsuario tipo = TipoUsuario.RESPONSAVEL;
                        UsuarioCadDTO usu = new UsuarioCadDTO(edtNome.getText().toString(),
                                edtEmail.getText().toString(),
                                SenhaUtil.criptografarSenha(edtSenha.getText().toString()), tipo, "");

                        Call<Usuario> call = apiInterface.cadastroUsuario(usu);
                        call.enqueue(new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                usuarioCadastrado = response.body();

                                /*mAuth = FirebaseAuth.getInstance();
                                mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtSenha.getText().toString()).
                                        addOnCompleteListener(CadUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    Log.e("CADASTRO", "Usuario Firebase Sucesso");
                                                } else {
                                                    Log.e("CADASTRO", "Usuario Firebase Falha");
                                                }
                                            }
                                        });*/

                                Toast.makeText(CadUsuarioActivity.this, "Usuario " + usuarioCadastrado.getNome() + " cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CadUsuarioActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {
                                call.cancel();
                                Log.e("post api", "entrou no onFailure" + t.getMessage());
                            }
                        });
                    } else {
                        Toast.makeText(CadUsuarioActivity.this, "Senha e confirmação de senha devem ser iguais!", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(CadUsuarioActivity.this, "Informe os dados para o cadastro!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadUsuarioActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }



}