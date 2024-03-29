package com.example.projetofinalpw3.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetofinalpw3.R;
import com.example.projetofinalpw3.model.HistoriaSocial;
import com.example.projetofinalpw3.retrofit.APIClient;
import com.example.projetofinalpw3.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyAdapterListaHistoriaSocialUsuarioLeitor extends RecyclerView.Adapter<MyAdapterListaHistoriaSocialUsuarioLeitor.MyViewHolderListHistSocial> {
    List<HistoriaSocial> listaHistoria = new ArrayList<HistoriaSocial>();
    Context context;

    String token;
    public MyAdapterListaHistoriaSocialUsuarioLeitor(Context context, List<HistoriaSocial> historias, String token) {
        this.context = context;
        this.listaHistoria = historias;
        this.token = token;
    }

    @NonNull
    @Override
    public MyViewHolderListHistSocial onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_card_icones_lista_historia_social_usuario_leitor, viewGroup, false);
        return new MyViewHolderListHistSocial(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderListHistSocial myViewHolderListHistSocial, @SuppressLint("RecyclerView") int position) {
        HistoriaSocial h = listaHistoria.get(position);
        myViewHolderListHistSocial.titulo.setText(h.getTitulo());
        Bundle bundle = new Bundle();
        bundle.putString("id", listaHistoria.get(position).getId().toString());
        bundle.putString("titulo", listaHistoria.get(position).getTitulo());
        bundle.putString("texto", listaHistoria.get(position).getTexto());
        bundle.putString("emailUsuarioResponsavel", listaHistoria.get(position).getEmailUsuarioResponsavel());
        bundle.putString("token", token);
        bundle.putParcelableArrayList("listaAvd", new ArrayList<>(listaHistoria.get(position).getAtividadesDeVidaDiarias()));
        bundle.putParcelableArrayList("listaHs", new ArrayList<>(listaHistoria.get(position).getHabilidadesSociais()));
        bundle.putParcelableArrayList("listaImagens", new ArrayList<>(listaHistoria.get(position).getImagens()));

        myViewHolderListHistSocial.btnVisual.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_fragment_historia_social_Visualizar, bundle));
    }

    @Override
    public int getItemCount() {
        return listaHistoria.size();
    }


    public class MyViewHolderListHistSocial extends RecyclerView.ViewHolder{
        TextView titulo;
        ImageButton btnVisual;

        public MyViewHolderListHistSocial(View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewTitulo);
            btnVisual = itemView.findViewById(R.id.btnVisualizar);
        }
    }
}
