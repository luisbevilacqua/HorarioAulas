package com.example.luis.aplicativo1;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        AcessoBD dbAcesso = AcessoBD.getInstance(this.getContext());
        dbAcesso.open();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        String RA = sharedPrefs.getString("RA", "");
        ArrayList<Aula> resultSet = dbAcesso.getAulas(RA);

        aulas = new ArrayList<Aula>();
        int j = 0;
        for(int i = 0; i < resultSet.size(); i++){
            if(i==0||!resultSet.get(i).getDia().equals(resultSet.get(i-1).getDia())){
                aulas.add(new Aula("","","","","",resultSet.get(i).getDia().substring(0,1).toUpperCase()+resultSet.get(i).getDia().substring(1)));
                MyData.tipo[j]=1;
                j++;
            }
            aulas.add(resultSet.get(i));
            MyData.tipo[j] = 0;
            j++;
        }

        dbAcesso.close();
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
