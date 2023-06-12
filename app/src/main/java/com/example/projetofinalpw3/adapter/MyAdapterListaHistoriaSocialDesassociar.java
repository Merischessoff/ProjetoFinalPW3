package com.example.projetofinalpw3.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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


public class MyAdapterListaHistoriaSocialDesassociar extends RecyclerView.Adapter<MyAdapterListaHistoriaSocialDesassociar.MyViewHolderListHistSocial> {
    private List<HistoriaSocial> listaHistoria = new ArrayList<HistoriaSocial>();
    private Context context;
    private String idUsuarioLeitor;
    private String token;
    private APIUtil apiUtil;
    private String nomeUsuario;
    private String emailUsuarioLeitor;

    private String emailUsuarioResponsavel;

    public MyAdapterListaHistoriaSocialDesassociar(Context context, List<HistoriaSocial> historias, String token, String idUsuarioLeitor, String emailUsuarioLeitor, String emailUsuarioResponsavel, String nomeUsuario) {
        this.context = context;
        this.listaHistoria = historias;
        this.token = token;
        this.idUsuarioLeitor = idUsuarioLeitor;
        this.emailUsuarioLeitor = emailUsuarioLeitor;
        this.emailUsuarioResponsavel = emailUsuarioResponsavel;
        this.nomeUsuario = nomeUsuario;
        this.apiUtil = new APIUtil();
    }

    @NonNull
    @Override
    public MyViewHolderListHistSocial onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_card_icones_lista_historia_social_desassociar, viewGroup, false);
        return new MyViewHolderListHistSocial(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderListHistSocial myViewHolderListHistSocial, @SuppressLint("RecyclerView") int position) {
        myViewHolderListHistSocial.titulo.setText(listaHistoria.get(position).getTexto());
        Bundle bundle = new Bundle();
        bundle.putString("id", listaHistoria.get(position).getId().toString());
        bundle.putString("token", token);
        bundle.putString("idUsuarioLeitor", idUsuarioLeitor);
        bundle.putString("emailUsuarioLeitor", emailUsuarioLeitor);
        myViewHolderListHistSocial.titulo.setText(listaHistoria.get(position).getTitulo().toString());
        myViewHolderListHistSocial.btnDesassociarHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiUtil.desvincularHistoria(token, bundle, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaHistoria.size();
    }

    public class MyViewHolderListHistSocial extends RecyclerView.ViewHolder{
        TextView titulo;
        ImageButton btnDesassociarHist;

        public MyViewHolderListHistSocial(View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewTituloDesvincular);
            btnDesassociarHist = itemView.findViewById(R.id.btnDesvincularHistorias);
        }
    }
}
