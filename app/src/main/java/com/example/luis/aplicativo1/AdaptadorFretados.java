package com.example.luis.aplicativo1;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by luis on 10/07/16.
 */
public class AdaptadorFretados extends RecyclerView.Adapter<AdaptadorFretados.MyViewHolder>{
    private ArrayList<Fretado> fretados;
    private int lastPosition = -1;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_fretado_layout, parent,false);
        return new FretadosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        FretadosViewHolder holder = (FretadosViewHolder) viewHolder;
        TextView numeroLinha = holder.numeroLinha;
        TextView horarioPartida = holder.horarioPartida;
        TextView localPartida = holder.localPartida;
        TextView horarioChegada = holder.horarioChegada;
        TextView localChegada = holder.localChegada;
        ImageView fretadoFavicon = holder.fretadoFavicon;
        String sigla, local;

        if(fretados.get(position).getLinha().equals("expresso")){
            numeroLinha.setText("Expresso");
        }
        else{
            numeroLinha.setText("Linha " + fretados.get(position).getLinha());
        }

        horarioPartida.setText(fretados.get(position).getHorarioPartida());
        horarioChegada.setText(fretados.get(position).getHorarioChegada());

        if(Integer.parseInt(fretados.get(position).getHorarioPartida().substring(0,2))> Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                || (Integer.parseInt(fretados.get(position).getHorarioPartida().substring(3,5))> Calendar.getInstance().get(Calendar.MINUTE)
                && Integer.parseInt(fretados.get(position).getHorarioPartida().substring(0,2))== Calendar.getInstance().get(Calendar.HOUR_OF_DAY))){
            numeroLinha.setTextColor(Color.parseColor("#000000"));
            fretadoFavicon.setImageResource(R.drawable.ic_directions_bus_black2_24dp);
        }
        else{
            numeroLinha.setTextColor(Color.parseColor("#CCCCCC"));
            fretadoFavicon.setImageResource(R.drawable.ic_directions_bus_grey_24dp);
        }

        sigla = fretados.get(position).getLocalPartida();
        local = siglaLocal(sigla);
        localPartida.setText(local);

        sigla = fretados.get(position).getLocalChegada();
        local = siglaLocal(sigla);
        localChegada.setText(local);
        setAnimation(holder.itemView,position);
    }

    public String siglaLocal (String sigla){
        String local="Unknown";
        if(sigla.equals("SBC")){
            local = "São Bernardo";
        }
        else if(sigla.equals("STA")){
            local = "Santo André";
        }
        else if(sigla.equals("TML")){
            local = "Terminal Leste";
        }
        else if (sigla.equals("TSB")){
            local = "Terminal SBC";
        }
        else if (sigla.equals("PCE")){
            local = "Pça. Exped.";
        }
        return local;
    }

    public int getNextDepartureID(){
        int id = 0;
        for(int x = 0; x< fretados.size(); x++){
            if(Integer.parseInt(fretados.get(x).getHorarioPartida().substring(0,2))> Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                    || (Integer.parseInt(fretados.get(x).getHorarioPartida().substring(3,5))> Calendar.getInstance().get(Calendar.MINUTE)
                    && Integer.parseInt(fretados.get(x).getHorarioPartida().substring(0,2))== Calendar.getInstance().get(Calendar.HOUR_OF_DAY))){
                return x;
            }
        }
        return id;
    }

    @Override
    public int getItemCount() {
        return fretados.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(View itemView) {
            super(itemView);
        }
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
    
    public class FretadosViewHolder extends MyViewHolder{
        TextView numeroLinha;
        TextView horarioPartida;
        TextView localPartida;
        TextView horarioChegada;
        TextView localChegada;
        ImageView fretadoFavicon;

        public FretadosViewHolder(View itemView) {
            super(itemView);
            this.numeroLinha = (TextView) itemView.findViewById(R.id.numeroLinha);
            this.horarioPartida = (TextView) itemView.findViewById(R.id.horarioPartida);
            this.localPartida = (TextView) itemView.findViewById(R.id.localPartida);
            this.horarioChegada = (TextView) itemView.findViewById(R.id.horarioChegada);
            this.localChegada = (TextView) itemView.findViewById(R.id.localChegada);
            this.fretadoFavicon = (ImageView) itemView.findViewById(R.id.fretadoFavicon);
        }
    }

    public AdaptadorFretados(ArrayList<Fretado> fretados){
        this.fretados = fretados;
    }
}
