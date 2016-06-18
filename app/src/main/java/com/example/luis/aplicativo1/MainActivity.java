package com.example.luis.aplicativo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<Aula> aulas;
    private static RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        aulas = new ArrayList<Aula>();
        for(int i = 0; i < MyData.disciplina.length; i++){
            aulas.add(new Aula(MyData.horas[i],MyData.minutos[i],
                    MyData.disciplina[i],MyData.professor[i],MyData.sala[i],MyData.dia[i]));
        }

        adapter = new MyAdapter(aulas);
        recyclerView.setAdapter(adapter);
    }
}
