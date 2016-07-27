package com.example.luis.aplicativo1;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class FragmentFretados extends Fragment {


    private static RecyclerView recyclerView;
    private static RecyclerView.LayoutManager layoutManager;
    private static RecyclerView.Adapter adapter;
    private ArrayList<Fretado> fretados;

    // TODO: Rename and change types of parameters
    public FragmentFretados() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fretados,container,false);
        ProgressBar progressBar = (ProgressBar)rootView.findViewById(R.id.progressoFretados);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fretados_recycler);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        PopularFretados popularFretados = new PopularFretados(getContext());
        popularFretados.execute();
    }

    public class PopularFretados extends AsyncTask<String, Void, String>{
        Context context;
        public PopularFretados(Context context){
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {


            AcessoBD dbAcesso = AcessoBD.getInstance(context);
            dbAcesso.open();

            fretados= dbAcesso.getFretados("STA", "TML", "semana");

            return null;
        }
        public void onPostExecute (String resultado){
            recyclerView = (RecyclerView) getView().findViewById(R.id.fretados_recycler);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            adapter = new AdaptadorFretados(fretados);
            recyclerView.setAdapter(adapter);

            ProgressBar progressBar = (ProgressBar)getView().findViewById(R.id.progressoFretados);
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
