package com.example.luis.aplicativo1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by luis on 20/08/16.
 */
public class AdaptadorPratos extends RecyclerView.Adapter<AdaptadorPratos.MyViewHolder> {
    private ArrayList<Prato> pratos;
    private int lastPosition = -1;

    public AdaptadorPratos(ArrayList<Prato> pratos) {
        this.pratos = pratos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_restaurante, parent,false);
        return new PratosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        PratosViewHolder holder = (PratosViewHolder) viewHolder;
        TextView tipo = holder.tipo;
        TextView nomeDoPrato = holder.nomeDoPrato;

        tipo.setText(pratos.get(position).getTipo());
        nomeDoPrato.setText(pratos.get(position).getNomeDoPrato());
        setAnimation(holder.itemView,position);
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            viewToAnimate.setTranslationY(250);
            viewToAnimate.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700)
                    .start();
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return pratos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
    public class PratosViewHolder extends MyViewHolder{
        TextView tipo;
        TextView nomeDoPrato;

        public PratosViewHolder(View itemView) {
            super(itemView);
            this.tipo = (TextView) itemView.findViewById(R.id.tipo_prato);
            this.nomeDoPrato = (TextView) itemView.findViewById(R.id.nome_prato);
        }
    }
}
