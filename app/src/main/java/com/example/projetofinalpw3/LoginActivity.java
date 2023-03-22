package com.example.projetofinalpw3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetofinalpw3.dto.TokenDTO;
import com.example.projetofinalpw3.dto.UsuarioLoginDTO;
import com.example.projetofinalpw3.model.Usuario;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText edtEmail;
    private TextInputEditText edtSenha;
    private Button btnLogin;
    private Button btnCad;
    private TokenDTO token;

    private APIClient client = new APIClient();

    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnCad = findViewById(R.id.btnCadastro);

        /*if(usuarioLogado()){
            openMainWindow();
        }else {*/
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
            //mAuth = FirebaseAuth.getInstance();
        //}
    }

    private Boolean usuarioLogado(){
        //Se o usuário já está logado não precisa fazer login novamente
        //Log.d("Login", "instance"+ FirebaseAuth.getInstance());
        //se o usuário não está logado retorna null, se o user já está logado ele retorna os dados de autenticação
        //FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        //if(currentUser == null) return false;
        return false;
    }

    private void validateLogin(String email, String senha){
        //mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            //@Override
        UsuarioLoginDTO usu = new UsuarioLoginDTO(email,senha);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<TokenDTO> call = apiInterface.login(usu);
        call.enqueue(new Callback<TokenDTO>() {
            @Override
            public void onResponse(Call<TokenDTO> call, Response<TokenDTO> response) {
                // Verificar se a chamada foi bem-sucedida e se a resposta não é nula
                if (response.isSuccessful() && response.body() != null) {
                    // A resposta foi bem-sucedida e contém um objeto TokenDTO
                    TokenDTO token = response.body();
                    openMainWindow();
                    Toast.makeText(LoginActivity.this, "sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Dados de login inválidos!", Toast.LENGTH_SHORT).show();
                    Log.d("LOGIN", "dados inválidos!");
                }
            }

            @Override
            public void onFailure(Call<TokenDTO> call, Throwable t) {
                call.cancel();
                Log.e("post api", "entrou no onFailure " + t.getMessage());
            }
        });


    }



    private void openMainWindow(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}