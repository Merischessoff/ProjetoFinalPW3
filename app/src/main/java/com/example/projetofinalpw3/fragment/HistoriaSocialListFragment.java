package com.example.projetofinalpw3.fragment;

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
import com.example.projetofinalpw3.adapter.MyAdapterListHistSocial;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HistoriaSocialListFragment extends Fragment {

    List<HistoriaSocial> listaHistorias= new ArrayList<>();
    MyAdapterListHistSocial myAdapter;
    RecyclerView recyclerView;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_historia_social_list, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        carregaHistoriasSociais();

        //configurar o gerenciador de layout
        // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        //GridLayoutManager layoutManager3 = new GridLayoutManager(getContext(), 2);
        StaggeredGridLayoutManager layoutManager2 =
                new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

        //adiciona um separador entre os elementos da lista
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayout.VERTICAL));

        //definindo o layout do recycler
        recyclerView.setLayoutManager(layoutManager2);
        return root;
    }
    private void carregaHistoriasSociais(){
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("historiasocial");
        listaHistorias = new ArrayList<>();

        Log.e("ERRO", reference.toString());

        //associar os eventos ao nó pessoas para poder buscar os dados
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            //é chamado sempre que consegue recuperar algum dado
            //DataSnapshot é o retorno do Firebase => resultado da consulta
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    //para buscar todos os nós filhos de pessoa
                    HistoriaSocial historiaSocial = ds.getValue(HistoriaSocial.class);
                    historiaSocial.setId(ds.getKey());
                    listaHistorias.add(historiaSocial);
                }
                //configurar o adapter - que formata que o layout de cada item do recycler
                myAdapter = new MyAdapterListHistSocial (root.getContext(), listaHistorias);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setHasFixedSize(true);
                reference.removeEventListener(this);
            }
            @Override
            //chamado quando a requisição é cancelada
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}