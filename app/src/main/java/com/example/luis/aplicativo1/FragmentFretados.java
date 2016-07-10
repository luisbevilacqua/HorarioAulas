package com.example.luis.aplicativo1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentFretados extends Fragment {


    private static RecyclerView recyclerView;
    private static RecyclerView.LayoutManager layoutManager;
    private static RecyclerView.Adapter adapter;

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

        recyclerView = (RecyclerView) rootView.findViewById(R.id.fretados_recycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        AcessoBD dbAcesso = AcessoBD.getInstance(this.getContext());
        dbAcesso.open();

        ArrayList<Fretado> fretados= dbAcesso.getFretados("STA", "TML", "semana");

        adapter = new AdaptadorFretados(fretados);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

}
