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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.projetofinalpw3.R;


public class MyAdapterListHistSocial extends RecyclerView.Adapter<MyAdapterListHistSocial.MyViewHolder> {
    List<HistoriaSocial> listaHistoria = new ArrayList<>();
    Context context;
    public MyAdapterListHistSocial(Context context, List<HistoriaSocial> historias) {
        this.context = context;
        this.listaHistoria = historias;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //chamado para criar as visualizações - somente as primeiras que aparecem para o usuário
        //convertendo o XML em uma visualização
        //cria um objeto do tipo view
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_card_icones_hist_soc, viewGroup, false);
        //se usar adapter_card -> ajustar o ViewHolder para usar Button
        //retorna o itemList que é passado para o construtor da MyViewHolder
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {
        //exibe os itens no Recycler
        HistoriaSocial h = listaHistoria.get(position);
        myViewHolder.titulo.setText(h.getTitulo());
        myViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //removerItem(position);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("ID", listaHistoria.get(position).getId());
        bundle.putString("SEQ", listaHistoria.get(position).getSeq());
        bundle.putString("URL", listaHistoria.get(position).getUrl());
        bundle.putString("TITULO", listaHistoria.get(position).getTitulo());
        bundle.putString("TEXTO", listaHistoria.get(position).getTexto());
        myViewHolder.btnEdit.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_fragment_historia_social_Visualizar, bundle));
    }

    @Override
    public int getItemCount() {
        //retorna a quantidade de itens que será exibida
        return listaHistoria.size();
    }

 /*   public void removerItem(final int position) {
        new AlertDialog.Builder(context)
                .setTitle("Deletando pessoa")
                .setMessage("Tem certeza que deseja deletar essa pessoa?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("pessoas");
                        //pega a pessoa que tem essa chave e remove do FB
                        reference.child(listaPessoas.get(position).getId()).removeValue();
                        listaPessoas.remove(position);
                        notifyItemRemoved(position);
                    }}).setNegativeButton("Não", null).show();
    }*/

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //dados da pessoa que serão exibidos no recycler
        TextView titulo;
        ImageButton btnDelete;
        ImageButton btnEdit;
        ImageButton btnVisual;
        //se usar adapter_card -> ajustar o ViewHolder para usar Button
        //Button btnDelete;
        //Button btnEdit;
        public MyViewHolder(View itemView){
            super(itemView);
            //passa uma referência para os componentes que estão na interface
            titulo = itemView.findViewById(R.id.txtTituloHistoriaSocial);
            btnDelete = itemView.findViewById(R.id.btnExcluir);
            btnEdit= itemView.findViewById(R.id.btnEditar);
            btnVisual = itemView.findViewById(R.id.btnVisualizar);
        }
    }
}
