package com.example.luis.aplicativo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<Aula> aulas;
    private static RecyclerView.Adapter adapter;
    private int[] tipo = new int[50];

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
                new RecyclerClickListener(this, new RecyclerClickListener.OnItemClickListener(){
                    @Override
                    public void onItemClick(View view, int position){
                        Log.d("DEBUG", "Chegou! Item: " + position);
                    }
                })
        );
    }
    public void onCardClick(View view){
        Toast.makeText(this,"Teste",Toast.LENGTH_SHORT).show();
    }
}
