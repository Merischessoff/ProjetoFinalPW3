package com.example.projetofinalpw3.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.adapter.MyAdapterListHistSocial;
import com.example.projetofinalpw3.dto.HistoriaSocialDTO;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListaHistoriaSocialFragment extends Fragment {
    private RecyclerView recyclerView;
    private View root;

    private String token;
    private String emailUsuarioVinculado;
    private List<HistoriaSocial> historias = new ArrayList<HistoriaSocial>();
    private APIInterface apiInterface;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_historia_social_lista, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewHistSocList);

        Intent intent = getActivity().getIntent();
        emailUsuarioVinculado = intent.getStringExtra("email");
        token = intent.getStringExtra("token");

        carregaHistoriasSociais();
        return root;
    }
    private void carregaHistoriasSociais(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<HistoriaSocial>> call = apiInterface.pesquisaHistoriasPropriasPorEmail(token, emailUsuarioVinculado);

        call.enqueue(new Callback<List<HistoriaSocial>>() {
            @Override
            public void onResponse(Call<List<HistoriaSocial>> call, Response<List<HistoriaSocial>> response) {
                historias = response.body();
                MyAdapterListHistSocial myAdapterListaHistorias = new MyAdapterListHistSocial(getActivity().getBaseContext(),historias, token);
                recyclerView.setAdapter(myAdapterListaHistorias);
                recyclerView.setHasFixedSize(true);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

                recyclerView.setLayoutManager(layoutManager);
                Log.e("onResponse", "ListaHistoriaSocialFragment " + response.body());
                Log.e("onResponse ", "lista tamanho = " + historias.size());
            }

            @Override
            public void onFailure(Call<List<HistoriaSocial>> call, Throwable t) {
                call.cancel();
                Log.e("onFailure", "ListaHistoriaSocialFragment " + t.getMessage());
            }
        });


    }
}