package com.example.luis.aplicativo1;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import com.example.luis.aplicativo1.R;


public class Horarios extends Fragment {
    private static RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<Aula> aulas;
    private static RecyclerView.Adapter adapter;
    private int[] tipo = new int[50];

    public Horarios() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_horarios, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        aulas = new ArrayList<Aula>();
        int j = 0;
        for(int i = 0; i < MyData.disciplina.length; i++){
            if(i==0||!MyData.dia[i].equals(MyData.dia[i-1])){
                aulas.add(new Aula("","","","","",MyData.dia[i]));
                MyData.tipo[j]=1;
                j++;
            }
            aulas.add(new Aula(MyData.horas[i],MyData.minutos[i],
                    MyData.disciplina[i],MyData.professor[i],MyData.sala[i],MyData.dia[i]));
            MyData.tipo[j] = 0;
            j++;
        }

        adapter = new MyAdapter(aulas, MyData.tipo);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerClickListener(this.getContext(), new RecyclerClickListener.OnItemClickListener(){
                    @Override
                    public void onItemClick(View view, int position){
                        Log.d("DEBUG", "Chegou! Item: " + position);
                    }
                })
        );
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
       // recyclerView.smoothScrollToPosition(12);
    }
}
