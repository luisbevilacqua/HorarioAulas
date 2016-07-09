package com.example.luis.aplicativo1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<Aula> aulas;
    private static RecyclerView.Adapter adapter;
    private int[] tipo = new int[50];

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //int hour = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        //Toast.makeText(this,"Hora: " + hour, Toast.LENGTH_SHORT).show();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        viewPager.setCurrentItem(1);
    }

    public void abrirOpcoes(View v){

    }

    private void setupTabIcons() {
        final DrawerArrowDrawable menu_hamburguer = new DrawerArrowDrawable(this);
        menu_hamburguer.setColor(Color.parseColor("#FFFFFF"));
        tabLayout.getTabAt(0).setIcon(menu_hamburguer);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_query_builder_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_directions_bus_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_restaurant_menu_black_24dp);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
                if(position==0) {
                    menu_hamburguer.setProgress(1-positionOffset);
                }
                else{
                    menu_hamburguer.setProgress(0f);
                }
            }

            @Override
            public void onPageSelected(int position){

            }

            @Override
            public void onPageScrollStateChanged(int state){

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HamburguerMenu(), "Menu");
        adapter.addFragment(new Horarios(), "Horários");
        adapter.addFragment(new Fretados(), "Fretados");
        adapter.addFragment(new RestauranteUniversitario(), "Restaurante Universitario");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public float getPageWidth(int position){
            if(position==0)
                return(0.7f);
            else
                return 1;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
    public void onCardClick(View view){
        Toast.makeText(this,"Teste",Toast.LENGTH_SHORT).show();
    }
    public void logout(View view){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferences.edit().clear().commit();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
