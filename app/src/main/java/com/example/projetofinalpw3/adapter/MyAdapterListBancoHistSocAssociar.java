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


public class MyAdapterListBancoHistSocAssociar extends RecyclerView.Adapter<MyAdapterListBancoHistSocAssociar.MyViewHolderListHistSocial> {
    private List<BancoDeHistoriaSocial> listaHistoriaB = new ArrayList<BancoDeHistoriaSocial>();
    private Context context;
    private String idUsuarioLeitor;
    private String token;
    private APIUtil apiUtil;

    private String emailUsuarioLeitor;

    private String emailUsuarioResponsavel;

    public MyAdapterListBancoHistSocAssociar(Context context, List<BancoDeHistoriaSocial> bHistorias, String token, String idUsuarioLeitor, String emailUsuarioLeitor, String emailUsuarioResponsavel) {
        this.context = context;
        this.listaHistoriaB = bHistorias;
        this.token = token;
        this.idUsuarioLeitor = idUsuarioLeitor;
        this.emailUsuarioLeitor = emailUsuarioLeitor;
        this.emailUsuarioResponsavel = emailUsuarioResponsavel;
    }

    @NonNull
    @Override
    public MyViewHolderListHistSocial onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_associar_card_historia_social, viewGroup, false);
        return new MyViewHolderListHistSocial(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderListHistSocial myViewHolderListHistSocial, @SuppressLint("RecyclerView") int position) {
        myViewHolderListHistSocial.titulo.setText(listaHistoriaB.get(position).getTexto());
        Bundle bundle = new Bundle();
        bundle.putString("id", listaHistoriaB.get(position).getId().toString());
        bundle.putString("token", token);
        bundle.putString("idUsuarioLeitor", idUsuarioLeitor);

        myViewHolderListHistSocial.btnAssociarHist.setOnClickListener(
            apiUtil.vincularBancoHistoria(token, bundle)
        );

    }

    @Override
    public int getItemCount() {
        return listaHistoriaB.size();
    }

    public class MyViewHolderListHistSocial extends RecyclerView.ViewHolder{
        TextView titulo;
        TextView emailLeitor;
        Button btnAssociarHist;

        public MyViewHolderListHistSocial(View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewTituloVincular);
            emailLeitor = itemView.findViewById(R.id.textViewNomeUsuarioVincular);
            btnAssociarHist = itemView.findViewById(R.id.btnVincularHistória);
        }
    }
}
