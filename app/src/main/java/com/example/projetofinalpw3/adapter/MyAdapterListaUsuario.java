package com.example.projetofinalpw3.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.model.Usuario;


import java.util.ArrayList;
import java.util.List;


public class MyAdapterListaUsuario extends RecyclerView.Adapter<MyAdapterListaUsuario.MyViewHolderListaUsuario> {
    List<Usuario> listaUsuarios = new ArrayList<>();
    Context context;
    public MyAdapterListaUsuario(Context context, List<Usuario> usuarios) {
        this.context = context;
        this.listaUsuarios = usuarios;
    }

    @NonNull
    @Override
    public MyViewHolderListaUsuario onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_card_icones_lista_usuario, viewGroup, false);
        return new MyViewHolderListaUsuario(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterListaUsuario.MyViewHolderListaUsuario myViewHolderListaUsuario, @SuppressLint("RecyclerView") int position) {
        Usuario h = listaUsuarios.get(position);
        myViewHolderListaUsuario.nome.setText(h.getNome());
        myViewHolderListaUsuario.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerItem(position);
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("id", listaUsuarios.get(position).getId().toString());
        bundle.putString("cpf", listaUsuarios.get(position).getCpf());
        bundle.putString("email", listaUsuarios.get(position).getEmail());
        bundle.putString("nome", listaUsuarios.get(position).getNome());
        bundle.putString("senha", listaUsuarios.get(position).getSenha());//?
        //myViewHolderListaUsuario.btnVisual.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_fragment_usuario_Visualizar, bundle));
        //myViewHolderListaUsuario.btnEdit.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_fragment_usuario_Edit,  bundle));
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public void removerItem(final int position) {
        new AlertDialog.Builder(context)
                .setTitle("Deletando usuario")
                .setMessage("Tem certeza que deseja deletar esse perfil leitor?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Deleta api
                        listaUsuarios.remove(position);
                        notifyItemRemoved(position);
                    }}).setNegativeButton("Não", null).show();
    }

    public class MyViewHolderListaUsuario extends RecyclerView.ViewHolder{
        TextView nome;
        TextView sobrenome;
        ImageButton btnDelete;
        ImageButton btnEdit;
        ImageButton btnVisual;

        public MyViewHolderListaUsuario(View itemView){
            super(itemView);
            nome = itemView.findViewById(R.id.textViewNomeUsuarioIcones);

            btnDelete = itemView.findViewById(R.id.btnExcluir);
            btnEdit= itemView.findViewById(R.id.btnEditar);
            btnVisual = itemView.findViewById(R.id.btnVisualizar);
        }
    }
}