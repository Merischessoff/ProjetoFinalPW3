package com.example.projetofinalpw3.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.model.BancoDeHistoriaSocial;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.example.projetofinalpw3.util.APIUtil;

import java.util.ArrayList;
import java.util.List;


public class MyAdapterListaBancoHistoriaSocialUsuarioLeitor extends RecyclerView.Adapter<MyAdapterListaBancoHistoriaSocialUsuarioLeitor.MyViewHolderListHistSocial> {
    private List<BancoDeHistoriaSocial> listaHistoria = new ArrayList<BancoDeHistoriaSocial>();
    private Context context;
    private String email;
    private String token;
    private APIUtil apiUtil;

    private HistoriaSocial historiaSocial;
    public MyAdapterListaBancoHistoriaSocialUsuarioLeitor(Context context, List<BancoDeHistoriaSocial> historias, String token, String email) {
        this.context = context;
        this.listaHistoria = historias;
        this.token = token;
        this.email= email;
    }

    @NonNull
    @Override
    public MyViewHolderListHistSocial onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_card_icones_lista_banco_historia_social_usuario_leitor, viewGroup, false);
        return new MyViewHolderListHistSocial(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderListHistSocial myViewHolderListHistSocial, @SuppressLint("RecyclerView") int position) {
        BancoDeHistoriaSocial b = listaHistoria.get(position);
        myViewHolderListHistSocial.titulo.setText(b.getTitulo());
        Bundle bundle = new Bundle();
        bundle.putString("id", listaHistoria.get(position).getId().toString());
        bundle.putString("titulo", listaHistoria.get(position).getTitulo());
        bundle.putString("texto", listaHistoria.get(position).getTexto());
        bundle.putString("token", token);
        bundle.putString("email", email);
        bundle.putParcelableArrayList("listaAvd", new ArrayList<>(listaHistoria.get(position).getAtividadesDeVidaDiarias()));
        bundle.putParcelableArrayList("listaHs", new ArrayList<>(listaHistoria.get(position).getHabilidadesSociais()));
        bundle.putParcelableArrayList("listaImagens", new ArrayList<>(listaHistoria.get(position).getImagens()));

        myViewHolderListHistSocial.btnVisual.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.nav_fragment_historia_social_Visualizar, bundle)
        );

    }

    @Override
    public int getItemCount() {
        return listaHistoria.size();
    }


    public class MyViewHolderListHistSocial extends RecyclerView.ViewHolder{
        TextView titulo;Button btnVisual;

        public MyViewHolderListHistSocial(View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewTituloBancoHistSoc);
            btnVisual = itemView.findViewById(R.id.btnVisualizarBancoHistSoc);
        }
    }
}
