package com.example.projetofinalpw3.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.databinding.FragmentCadastroUsuarioLeitorBinding;
import com.example.projetofinalpw3.model.TipoUsuario;
import com.example.projetofinalpw3.model.Usuario;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;
import com.example.projetofinalpw3.ui.gallery.GalleryViewModel;
import com.example.projetofinalpw3.util.SenhaUtil;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;


public class CadastroUsuarioLeitorFragment extends Fragment {
        private FragmentCadastroUsuarioLeitorBinding binding;
        private TextInputEditText edtEmail;
        private TextInputEditText edtSenha;
        private TextInputEditText edtNome;
        private TextInputEditText edtCpf;
        private TextInputEditText edtConfSenha;
        private Button btnCancelar;
        private Button btnCadastrar;
        private  Usuario usuarioCadastrado;

        private String email;
        APIInterface apiInterface;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
            Intent intent = getActivity().getIntent();
            email = intent.getStringExtra("email");
            binding = com.example.projetofinalpw3.databinding.FragmentCadastroUsuarioLeitorBinding.inflate(inflater, container, false);
            View root = binding.getRoot();

            apiInterface = APIClient.getClient().create(APIInterface.class);

            btnCadastrar = root.findViewById(R.id.btnCadastrar);
            edtEmail = root.findViewById(R.id.edtEmail);
            edtSenha = root.findViewById(R.id.edtSenha);
            edtNome = root.findViewById(R.id.edtNome);
            edtCpf = root.findViewById(R.id.edtCpf);
            edtConfSenha = root.findViewById(R.id.edtConfSenha);
            btnCancelar = root.findViewById(R.id.btnCancelar);


            btnCadastrar.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if (!edtEmail.getText().toString().equals("")
                            && !edtSenha.getText().toString().equals("")
                            && !edtConfSenha.getText().toString().equals("")
                            && !edtNome.getText().toString().equals("")
                            && !edtCpf.getText().toString().equals("")) {
                        if (edtSenha.getText().toString().equals(edtConfSenha.getText().toString())) {

                            TipoUsuario tipo = TipoUsuario.LEITOR;
                            Usuario usu = new Usuario(0l, edtCpf.getText().toString(), edtNome.getText().toString(),
                                    edtEmail.getText().toString(),
                                    SenhaUtil.criptografarSenha(edtSenha.getText().toString()), tipo, email);

                            Call<Usuario> call = apiInterface.cadastroUsuario(usu);
                            call.enqueue(new Callback<Usuario>() {
                                @Override
                                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                    usuarioCadastrado = response.body();
                                    Snackbar.make(v, "Usuario " + usuarioCadastrado.getNome() + " cadastrado com sucesso!", Snackbar.LENGTH_LONG)
                                            .setTextColor(Color.GREEN).show();
                                    Navigation.findNavController(v).navigate(R.id.nav_fragment_cadastro_usuario_leitor);

                                }

                                @Override
                                public void onFailure(Call<Usuario> call, Throwable t) {
                                    call.cancel();
                                    Log.e("post api", "entrou no onFailure" + t.getMessage());
                                }
                            });
                        } else {
                            Snackbar.make(v, "Senha e confirmação de senha devem ser iguais!", Snackbar.LENGTH_LONG)
                                    .setTextColor(Color.RED).show();
                            Navigation.findNavController(v).navigate(R.id.nav_fragment_cadastro_usuario_leitor);

                        }
                    } else{
                        Snackbar.make(v, "Informe os dados para o cadastro!", Snackbar.LENGTH_LONG)
                                .setTextColor(Color.RED).show();
                        Navigation.findNavController(v).navigate(R.id.nav_fragment_cadastro_usuario_leitor);
                    }
                }
            });
            return root;
        }

}