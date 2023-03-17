package com.example.projetofinalpw3.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.adapter.MyAdapterListHistSocial;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HistoriaSocialListFragment extends Fragment {
    RecyclerView recyclerView;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_historia_social_list, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewHistSocList);
        carregaHistoriasSociais();

        StaggeredGridLayoutManager layoutManager2 =
                new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager2);
        return root;
    }
    private void carregaHistoriasSociais(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("HistoriaSocial");
        ArrayList<HistoriaSocial> lista = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    //para buscar todos os nós filhos de produtos
                    HistoriaSocial historia = ds.getValue(HistoriaSocial.class);
                    historia.setId(ds.getKey());
                    lista.add(historia);
                }

                //configurar o adapter - que formata que o layout de cada item do recycler
                MyAdapterListHistSocial myAdapter = new MyAdapterListHistSocial(root.getContext(), lista);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setHasFixedSize(true);
                reference.removeEventListener(this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}