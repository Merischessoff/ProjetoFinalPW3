package com.example.projetofinalpw3.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.adapter.MyAdapterListaUsuario;
import com.example.projetofinalpw3.model.Usuario;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaUsuarioLeitorFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private APIInterface apiInterface;
    private String emailUsuarioVinculado;
    private String token;
    private List<Usuario> lista = new ArrayList<>();

    RecyclerView recyclerView;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_lista_usuario_leitor_list, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewUsuarioLeitor);

        Intent intent = getActivity().getIntent();
        emailUsuarioVinculado = intent.getStringExtra("email");
        token = intent.getStringExtra("token");

        Log.e("onCreateView", "ListaUsuarioLeitorFragment email " + emailUsuarioVinculado);
        Log.e("onCreateView", "ListaUsuarioLeitorFragment token " + token);

        listaUsuarioLeitorFragment();



        StaggeredGridLayoutManager layoutManager2 =
                new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager2);
        return root;



    }

    private void listaUsuarioLeitorFragment() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<Usuario>> call = apiInterface.pesquisaUsuariosVinculadosPorEmail(token, emailUsuarioVinculado);

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                lista = response.body();
                Log.e("onResponse", "ListaUsuarioLeitorFragment " + response.body());
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                call.cancel();
                Log.e("onFailure", "ListaUsuarioLeitorFragment " + t.getMessage());
            }
        });

        //configurar o adapter - que formata que o layout de cada item do recycler
        MyAdapterListaUsuario myAdapter = new MyAdapterListaUsuario(root.getContext(), lista);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setHasFixedSize(true);

    }

}