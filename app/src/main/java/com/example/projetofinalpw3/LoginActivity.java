package com.example.projetofinalpw3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetofinalpw3.dto.TipoUsuarioDTO;
import com.example.projetofinalpw3.dto.TokenDTO;
import com.example.projetofinalpw3.dto.UsuarioLoginDTO;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText edtEmail;
    private TextInputEditText edtSenha;
    private Button btnLogin;
    private Button btnCad;

    private APIClient client = new APIClient();
    private TipoUsuarioDTO tipoUsuario = new TipoUsuarioDTO("");
    private TokenDTO token = new TokenDTO("");
    private APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnCad = findViewById(R.id.btnCadastro);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")) {
                        validateLogin(edtEmail.getText().toString(), edtSenha.getText().toString());
                    } else
                        Toast.makeText(LoginActivity.this, "Informe email e senha!", Toast.LENGTH_SHORT).show();
                }
            });
            btnCad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this, CadUsuarioActivity.class);
                    startActivity(intent);
                }
            });

    }

    private void validateLogin(String email, String senha){
        UsuarioLoginDTO usu = new UsuarioLoginDTO(email,senha);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<TokenDTO> call = apiInterface.login(usu);
        call.enqueue(new Callback<TokenDTO>() {
            @Override
            public void onResponse(Call<TokenDTO> call, Response<TokenDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //Log.e("validateLogin", "entrou no onResponse " + response.body());
                    String body = String.valueOf(response.body().getToken());
                    token = new TokenDTO(response.body().getToken());
                    pesquisaUsuarioPorEmail(token);
                } else {
                    //Log.d("validateLogin", "dados inválidos!");
                    Toast.makeText(LoginActivity.this, "Dados inválidos!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<TokenDTO> call, Throwable t) {
                call.cancel();
                //Log.e("validateLogin", "entrou no onFailure " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Dados inválidos!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void pesquisaUsuarioPorEmail(TokenDTO token){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<TipoUsuarioDTO> call = apiInterface.pesquisaUsuarioPorEmail("Bearer " + token.getToken(), edtEmail.getText().toString());
        call.enqueue(new Callback<TipoUsuarioDTO>() {
            @Override
            public void onResponse(Call<TipoUsuarioDTO> call, Response<TipoUsuarioDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //Log.e("pesquisaUsuarioPorEmail", "entrou no onResponse " + response.body());
                    tipoUsuario = new TipoUsuarioDTO(response.body().getTipo());
                    if(!tipoUsuario.getTipo().equalsIgnoreCase("") && !token.getToken().equalsIgnoreCase("")) {
                        Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                        openMainWindow(tipoUsuario, token);
                    }else{
                        Toast.makeText(LoginActivity.this, "Dados inválidos!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<TipoUsuarioDTO> call, Throwable t) {
                call.cancel();
                Toast.makeText(LoginActivity.this, "Dados inválidos!", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void openMainWindow(TipoUsuarioDTO tipoUsuario, TokenDTO token){
        //Log.e("validateLogin", "openMainWindow " + tipoUsuario.getTipo() + " " + token.getToken());
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("tipoUsuario", tipoUsuario.getTipo());
        intent.putExtra("token", token.getToken());
        startActivity(intent);
    }

}