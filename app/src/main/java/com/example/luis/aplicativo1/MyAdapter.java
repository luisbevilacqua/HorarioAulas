package com.example.luis.aplicativo1;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by luis on 16/06/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private ArrayList<Aula> aulas;
    private int[] tipo;

    public static final int card_type = 0;
    public static final int text_type = 1;

    private int lastPosition = -1;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(View itemView){
            super(itemView);
        }
    }

    public class CardViewHolder extends MyViewHolder {
        TextView txtHoras;
        TextView txtMinutos;
        TextView txtMateria;
        TextView txtProfessor;
        TextView txtSala;

        public CardViewHolder(View itemView) {
            super(itemView);
            this.txtHoras = (TextView) itemView.findViewById(R.id.txtHoras);
            this.txtMinutos = (TextView) itemView.findViewById(R.id.txtMinutos);
            this.txtMateria = (TextView) itemView.findViewById(R.id.txtMateria);
            this.txtProfessor = (TextView) itemView.findViewById(R.id.txtProfessor);
            this.txtSala = (TextView) itemView.findViewById(R.id.txtSala);
        }
    }

    public class TextViewHolder extends MyViewHolder{
        TextView txtDia;

        public TextViewHolder(View itemView){
            super(itemView);
            this.txtDia = (TextView) itemView.findViewById(R.id.txtDia2);
        }
    }


    public MyAdapter(ArrayList<Aula> aulas, int[] tipo) {
        this.aulas = aulas;
        this.tipo = tipo;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v;
        if(viewType == card_type) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);
            return new CardViewHolder(v);
        }
        else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dias_layout, parent, false);
            return new TextViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int listPosition){
        if(viewHolder.getItemViewType() == card_type){
            CardViewHolder holder = (CardViewHolder) viewHolder;
            TextView txtHoras = holder.txtHoras;
            TextView txtMinutos = holder.txtMinutos;
            TextView txtMateria = holder.txtMateria;
            TextView txtProfessor = holder.txtProfessor;
            TextView txtSala = holder.txtSala;


            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            int minutes = Calendar.getInstance().get(Calendar.MINUTE);
            int dia = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            String diaDaSemana = aulas.get(listPosition).getDia();
            boolean mesmoDia = false;
            if(diaDaSemana.equals("Segunda")&&dia==2){
                mesmoDia = true;
            }
            if(diaDaSemana.equals("Terça")&&dia==3){
                mesmoDia = true;
            }
            if(diaDaSemana.equals("Quarta")&&dia==4){
                mesmoDia = true;
            }
            if(diaDaSemana.equals("Quinta")&&dia==5){
                mesmoDia = true;
            }
            if(diaDaSemana.equals("Sexta")&&dia==6){
                mesmoDia = true;
            }
            if(diaDaSemana.equals("Sábado")&&dia==7){
                mesmoDia = true;
            }

            txtMinutos.setTextColor(Color.parseColor("#9e9e9e"));
            txtHoras.setTextColor(Color.parseColor("#9e9e9e"));
            txtMinutos.setTextSize(45);
            txtHoras.setText(aulas.get(listPosition).getHoras() + ":");
            txtMinutos.setText(aulas.get(listPosition).getMinutos() + " ");

            if(Integer.parseInt(aulas.get(listPosition).getHoras())==(hour+1)&&minutes>30&&mesmoDia){
                txtMinutos.setTextColor(Color.parseColor("#77acee"));
                txtHoras.setTextColor(Color.parseColor("#77acee"));
                txtMinutos.setTextSize(38);
                txtHoras.setText(":" + ((60-minutes<10)?"0":"") + (60-minutes));
                txtMinutos.setText("min");
            }

            txtMateria.setText(aulas.get(listPosition).getDisciplina());
            txtProfessor.setText(aulas.get(listPosition).getProfessor());
            txtSala.setText(aulas.get(listPosition).getSala());
            setAnimation(holder.itemView, listPosition);
        }
        else{
            TextViewHolder holder = (TextViewHolder) viewHolder;
            TextView txtDia = holder.txtDia;

            txtDia.setText(" " + aulas.get(listPosition).getDia() + " ");
            setAnimation(holder.itemView, listPosition);
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
    @Override
    public int getItemViewType(int position){
        return MyData.tipo[position];
    }

    @Override
    public int getItemCount(){
        return aulas.size();
    }
}
