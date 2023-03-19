package com.example.projetofinalpw3.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetofinalpw3.model.HistoriaSocial;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.projetofinalpw3.R;


public class MyAdapterListHistSocial extends RecyclerView.Adapter<MyAdapterListHistSocial.MyViewHolderListHistSocial> {
    List<HistoriaSocial> listaHistoria = new ArrayList<>();
    Context context;
    public MyAdapterListHistSocial(Context context, List<HistoriaSocial> historias) {
        this.context = context;
        this.listaHistoria = historias;
    }

    @NonNull
    @Override
    public MyViewHolderListHistSocial onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_card_icones_hist_soc, viewGroup, false);
        return new MyViewHolderListHistSocial(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderListHistSocial myViewHolderListHistSocial, @SuppressLint("RecyclerView") int position) {
        HistoriaSocial h = listaHistoria.get(position);
        myViewHolderListHistSocial.titulo.setText(h.getTitulo());
        myViewHolderListHistSocial.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerItem(position);
            }
        });

        Bundle bundle = new Bundle();
        //bundle.putString("ID", listaHistoria.get(position).getId());
        //bundle.putString("SEQ", listaHistoria.get(position).getSeq());
        //bundle.putString("URL", listaHistoria.get(position).getUrl());
        //bundle.putString("TITULO", listaHistoria.get(position).getTitulo());
        //bundle.putString("TEXTO", listaHistoria.get(position).getTexto());
        myViewHolderListHistSocial.btnVisual.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_fragment_historia_social_Visualizar, bundle));
        myViewHolderListHistSocial.btnEdit.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_fragment_historia_social_Edit,  bundle));
    }

    @Override
    public int getItemCount() {
        return listaHistoria.size();
    }

    public void removerItem(final int position) {
        new AlertDialog.Builder(context)
                .setTitle("Deletando história")
                .setMessage("Tem certeza que deseja deletar essa história?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //DatabaseReference reference = FirebaseDatabase.getInstance().getReference("HistoriaSocial");
                       // reference.child(listaHistoria.get(position).getId()).removeValue();
                        listaHistoria.remove(position);
                        notifyItemRemoved(position);
                    }}).setNegativeButton("Não", null).show();
    }

    public class MyViewHolderListHistSocial extends RecyclerView.ViewHolder{
        TextView titulo;
        ImageButton btnDelete;
        ImageButton btnEdit;
        ImageButton btnVisual;

        public MyViewHolderListHistSocial(View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewTitulo);
            btnDelete = itemView.findViewById(R.id.btnExcluir);
            btnEdit= itemView.findViewById(R.id.btnEditar);
            btnVisual = itemView.findViewById(R.id.btnVisualizar);
        }
    }
}
