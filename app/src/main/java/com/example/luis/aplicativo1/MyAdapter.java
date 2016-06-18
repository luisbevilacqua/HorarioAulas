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

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtHoras;
        TextView txtMinutos;
        TextView txtMateria;
        TextView txtProfessor;
        TextView txtSala;

        public MyViewHolder(View itemView){
            super(itemView);
            this.txtHoras = (TextView) itemView.findViewById(R.id.txtHoras);
            this.txtMinutos = (TextView) itemView.findViewById(R.id.txtMinutos);
            this.txtMateria = (TextView) itemView.findViewById(R.id.txtMateria);
            this.txtProfessor = (TextView) itemView.findViewById(R.id.txtProfessor);
            this.txtSala = (TextView) itemView.findViewById(R.id.txtSala);
        }
    }

    public MyAdapter(ArrayList<Aula> aulas) {
        this.aulas = aulas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition){
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

    @Override
    public int getItemCount(){
        return aulas.size();
    }
}
