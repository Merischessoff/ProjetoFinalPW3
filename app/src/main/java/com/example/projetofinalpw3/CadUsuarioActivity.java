package com.example.projetofinalpw3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadUsuarioActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputEditText edtEmail;
    private TextInputEditText edtSenha;
    private TextInputEditText edtConfSenha;
    private Button btnCancelar;
    private Button btnCadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        edtConfSenha = findViewById(R.id.edtConfSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("") && !edtConfSenha.getText().toString().equals("")){
                    if( edtSenha.getText().toString().equals(edtSenha.getText().toString())) {
                        mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtSenha.getText().toString()).
                                addOnCompleteListener(CadUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()) {
                                            Intent intent = new Intent(CadUsuarioActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            Toast.makeText(CadUsuarioActivity.this, "sucesso!", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                            Toast.makeText(CadUsuarioActivity.this, "Erro ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else{
                        Toast.makeText(CadUsuarioActivity.this, "Senha e confirmação de senha devem ser iguais!", Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(CadUsuarioActivity.this, "Informe os dados para o cadastro!", Toast.LENGTH_SHORT).show();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(CadUsuarioActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        mAuth = FirebaseAuth.getInstance();

    }
}