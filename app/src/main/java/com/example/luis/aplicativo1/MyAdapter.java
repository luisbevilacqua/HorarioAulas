package com.example.luis.aplicativo1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by luis on 16/06/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private ArrayList<Aula> aulas;
    private int[] tipo;

    public static final int card_type = 0;
    public static final int text_type = 1;

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

            txtHoras.setText(aulas.get(listPosition).getHoras() + ":");
            txtMinutos.setText(aulas.get(listPosition).getMinutos() + " ");
            txtMateria.setText(aulas.get(listPosition).getDisciplina());
            txtProfessor.setText(aulas.get(listPosition).getProfessor());
            txtSala.setText(aulas.get(listPosition).getSala());
        }
        else{
            TextViewHolder holder = (TextViewHolder) viewHolder;
            TextView txtDia = holder.txtDia;

            txtDia.setText(" " + aulas.get(listPosition).getDia() + " ");
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
