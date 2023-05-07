package com.example.projetofinalpw3.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.model.BancoDeHistoriaSocial;
import com.example.projetofinalpw3.util.APIUtil;

import java.util.ArrayList;
import java.util.List;


public class MyAdapterListaBancoHistoriaSocialDesassociar extends RecyclerView.Adapter<MyAdapterListaBancoHistoriaSocialDesassociar.MyViewHolderListHistSocial> {
    private List<BancoDeHistoriaSocial> listaHistoriaB = new ArrayList<BancoDeHistoriaSocial>();
    private Context context;
    private String idUsuarioLeitor;
    private String token;
    private APIUtil apiUtil;
    private String nomeUsuario;
    private String emailUsuarioLeitor;

    private String emailUsuarioResponsavel;

    public MyAdapterListaBancoHistoriaSocialDesassociar(Context context, List<BancoDeHistoriaSocial> bHistorias, String token, String idUsuarioLeitor, String emailUsuarioLeitor, String emailUsuarioResponsavel, String nomeUsuario) {
        this.context = context;
        this.listaHistoriaB = bHistorias;
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
        View itemList = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_card_icones_lista_banco_historia_social_desassociar, viewGroup, false);
        return new MyViewHolderListHistSocial(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderListHistSocial myViewHolderListHistSocial, @SuppressLint("RecyclerView") int position) {
        myViewHolderListHistSocial.titulo.setText(listaHistoriaB.get(position).getTexto());
        Bundle bundle = new Bundle();
        bundle.putString("id", listaHistoriaB.get(position).getId().toString());
        bundle.putString("token", token);
        bundle.putString("idUsuarioLeitor", idUsuarioLeitor);

        myViewHolderListHistSocial.titulo.setText(listaHistoriaB.get(position).getTitulo().toString());
        myViewHolderListHistSocial.texto.setText(listaHistoriaB.get(position).getTexto().toString());

        myViewHolderListHistSocial.btnDesassociarHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiUtil.desvincularBancoHistoria(token, bundle);
                Navigation.findNavController(v).navigate(R.id.nav_lista_banco_historias_desassociar_fragment, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaHistoriaB.size();
    }

    public class MyViewHolderListHistSocial extends RecyclerView.ViewHolder{
        TextView titulo;
        TextView texto;
        ImageButton btnDesassociarHist;

        public MyViewHolderListHistSocial(View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewTituloDesvincularB);
            texto = itemView.findViewById(R.id.textViewDesvincularB);
            btnDesassociarHist = itemView.findViewById(R.id.btnDesvincularBancoHistorias);
        }
    }
}
