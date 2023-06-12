package com.example.projetofinalpw3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.adapter.MyAdapterListaBancoHistoriaSocialDesassociar;
import com.example.projetofinalpw3.adapter.MyAdapterListaHistoriaSocialDesassociar;
import com.example.projetofinalpw3.model.BancoDeHistoriaSocial;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class ListaHistoriaSocialDesassociarFragment extends Fragment {

    private RecyclerView recyclerView;
    private View root;

    private String token;
    private String emailUsuarioLeitor;
    private String idUsuarioLeitor;
    private String emailUsuarioResponsavel;
    private List<HistoriaSocial> historias = new ArrayList<HistoriaSocial>();
    private String nomeUsuario;
    private APIInterface apiInterface;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_lista_de_historia_social_desassociar, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewDesassociarHistoria);
        Bundle bundle = getArguments();
        Intent intent = getActivity().getIntent();
        emailUsuarioLeitor = bundle.getString("emailUsuarioLeitor");
        idUsuarioLeitor = bundle.getString("idUsuarioLeitor");
        token = intent.getStringExtra("token");
        emailUsuarioResponsavel = intent.getStringExtra("email");
        nomeUsuario = bundle.getString("nome");
        carregaHistoriasSociais();
        return root;
    }
    private void carregaHistoriasSociais(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<HistoriaSocial>> call2 = apiInterface.pesquisaHistoriaUsuarioDesassociado(token, emailUsuarioLeitor);

        call2.enqueue(new Callback<List<HistoriaSocial>>() {
            @Override
            public void onResponse(Call<List<HistoriaSocial>> call, Response<List<HistoriaSocial>> response) {
                historias = response.body();
                MyAdapterListaHistoriaSocialDesassociar myAdapterListHistDesassociar =
                        new MyAdapterListaHistoriaSocialDesassociar(getActivity().getBaseContext(), historias, token, idUsuarioLeitor, emailUsuarioLeitor, emailUsuarioResponsavel, nomeUsuario);
                recyclerView.setAdapter(myAdapterListHistDesassociar);
                recyclerView.setHasFixedSize(true);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<HistoriaSocial>> call, Throwable t) {
                call.cancel();
                Log.e("onFailure", "ListaHistoriaSocialFragment " + t.getMessage());
            }
        });



    }
}