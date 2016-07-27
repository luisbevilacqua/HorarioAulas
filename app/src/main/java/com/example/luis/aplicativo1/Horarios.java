package com.example.luis.aplicativo1;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

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
        ProgressBar progressBar = (ProgressBar)rootView.findViewById(R.id.progressoHorarios);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        PopularRecycler popularRecycler = new PopularRecycler(getContext());
        popularRecycler.execute();
       // recyclerView.smoothScrollToPosition(12);
    }

    public ArrayList<Aula> setarTipos(ArrayList<Aula> resultSet){
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
        return aulas;
    }

    public class PopularRecycler extends AsyncTask<String, Void, String>{
        private Context context;

        public PopularRecycler (Context context){
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            AcessoBD dbAcesso = AcessoBD.getInstance(context);
            dbAcesso.open();
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
            String RA = sharedPrefs.getString("RA", "");

            String quinzenal;
            int semana = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
            if(semana % 2 == 0){
                quinzenal = "quinzenal 1";
            }
            else{
                quinzenal = "quinzenal 2";
            }

            ArrayList<Aula> resultSet = dbAcesso.getAulas(RA, quinzenal);

            setarTipos(resultSet);

            return null;
        }
        public void onPostExecute (String result){
            recyclerView = (RecyclerView) getView().findViewById(R.id.my_recycler_view);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            adapter = new MyAdapter(aulas, MyData.tipo);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnItemTouchListener(
                    new RecyclerClickListener(context, new RecyclerClickListener.OnItemClickListener(){
                        @Override
                        public void onItemClick(View view, int position){
                            Log.d("DEBUG", "Chegou! Item: " + position);
                        }
                    })
            );
            ProgressBar progressBar = (ProgressBar)getView().findViewById(R.id.progressoHorarios);
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
