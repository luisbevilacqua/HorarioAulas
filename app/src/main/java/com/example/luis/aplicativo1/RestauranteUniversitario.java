package com.example.luis.aplicativo1;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RestauranteUniversitario extends Fragment {
    private static RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<Prato> pratos;
    private static RecyclerView.Adapter adapter;

    // TODO: Rename and change types of parameters
    public RestauranteUniversitario() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurante_universitario,container,false);
        ProgressBar progressBar = (ProgressBar)rootView.findViewById(R.id.progresso_restaurante);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.restaurante_recycler);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        PopularRestaurante popularRestaurante = new PopularRestaurante(getContext());
        popularRestaurante.execute();
        // recyclerView.smoothScrollToPosition(12);
    }

    public class PopularRestaurante extends AsyncTask<String, Void, String>{
        Context context;

        public PopularRestaurante(Context context){
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            Gson gson = new Gson();
            pratos = new ArrayList<>();
            try {
                URL url = new URL("http://192.168.1.100:8080/RestRU/recursos/restauranteuniversitario");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    StringBuilder strBuilder = new StringBuilder();
                    String tmp;
                    while ((tmp = br.readLine()) != null){
                        strBuilder.append(tmp);
                    }
                    httpURLConnection.disconnect();
                    pratos = gson.fromJson(strBuilder.toString(), new TypeToken<List<Prato>>(){}.getType());
                }
                else{
                    return "Deu pau...";
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        public void onPostExecute (String resultado){
            recyclerView = (RecyclerView) getView().findViewById(R.id.restaurante_recycler);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            adapter = new AdaptadorPratos(pratos);
            recyclerView.setAdapter(adapter);

            ProgressBar progressBar = (ProgressBar)getView().findViewById(R.id.progresso_restaurante);

            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

}
