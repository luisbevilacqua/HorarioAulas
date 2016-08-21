package com.example.luis.aplicativo1;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
    private int item_origem_atual = 0;
    private int item_destino_atual = 0;
    private int item_semana_atual = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)%2;

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
        final View tb1 = findViewById(R.id.toolbarOpcoes);
        final View tb2 = findViewById(R.id.toolbarNormal);
        int cx = (tb2.getLeft()+ tb2.getRight());
        int cy = (tb2.getTop() + tb2.getBottom())/2;
        int finalRadius = Math.max(tb2.getWidth(),tb2.getHeight());
        if(viewPager.getCurrentItem()!=0){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                Animator anim = ViewAnimationUtils.createCircularReveal(tb1,cx,cy,0,finalRadius);
                anim.setDuration(200);
                anim.setInterpolator(new AccelerateDecelerateInterpolator());
                tb1.setVisibility(View.VISIBLE);
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#9E9E9E"));
                anim.start();
            }
            switch(viewPager.getCurrentItem()) {
                case 1:
                    Spinner spinner = (Spinner) findViewById(R.id.spinner1);
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Semanas,R.layout.spinner_item);
                    adapter.setDropDownViewResource(R.layout.spinner_item);
                    spinner.setAdapter(adapter);
                    Spinner spinner3 = (Spinner) findViewById(R.id.spinner2);
                    spinner3.setVisibility(View.GONE);
                    spinner.setSelection(item_semana_atual);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            item_semana_atual = position;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    break;
                case 2:
                    final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
                    final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Lugares,R.layout.spinner_item);
                    adapter1.setDropDownViewResource(R.layout.spinner_item);
                    spinner1.setAdapter(adapter1);
                    spinner1.setSelection(item_origem_atual);

                    final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
                    spinner2.setVisibility(View.VISIBLE);

                    final ArrayAdapter<CharSequence> adapterSpinnerDestino = new ArrayAdapter<CharSequence>(this,R.layout.spinner_item,R.id.spinner_texto);
                    spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(position!=item_origem_atual){
                                item_origem_atual = position;
                                item_destino_atual = 0;
                            }

                            adapterSpinnerDestino.setDropDownViewResource(R.layout.spinner_item);
                            adapterSpinnerDestino.clear();
                            switch(spinner1.getSelectedItem().toString()){
                                case "STA":
                                    adapterSpinnerDestino.add("TML");
                                    adapterSpinnerDestino.add("SBC");
                                break;
                                case "SBC":
                                    adapterSpinnerDestino.add("STA");
                                    adapterSpinnerDestino.add("TSB");
                                    adapterSpinnerDestino.add("TML");
                                    adapterSpinnerDestino.add("PCE");
                                break;
                                case "TSB":
                                    adapterSpinnerDestino.add("SBC");
                                    adapterSpinnerDestino.add("PCE");
                                break;
                                case "TML":
                                    adapterSpinnerDestino.add("STA");
                                    adapterSpinnerDestino.add("SBC");
                                break;
                                case "PCE":
                                    adapterSpinnerDestino.add("SBC");
                                    adapterSpinnerDestino.add("TSB");
                                break;
                            }
                            spinner2.setAdapter(adapterSpinnerDestino);
                            spinner2.setSelection(item_destino_atual);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            item_destino_atual = position;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    break;
            }
        }
    }

    public void scrollToNext(View view){
        RecyclerView rv = (RecyclerView) findViewById(R.id.fretados_recycler);
        AdaptadorFretados adaptadorFretados = (AdaptadorFretados) rv.getAdapter();
        rv.smoothScrollToPosition(adaptadorFretados.getNextDepartureID());
        //rv.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(this));
        //rv.smoothScrollToPosition(adaptadorFretados.getNextDepartureID());
    }

    public void confirmaOpcoes(int tipo){
        final View tb1 = findViewById(R.id.toolbarOpcoes);
        final View tb2 = findViewById(R.id.toolbarNormal);
        int cx = (tb1.getLeft()+ tb1.getRight());
        int cy = (tb1.getTop() + tb1.getBottom())/2;
        int finalRadius = Math.max(tb2.getWidth(),tb2.getHeight());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animator anim = ViewAnimationUtils.createCircularReveal(tb1,cx,cy,finalRadius,0);
            anim.setDuration(200);
            anim.setInterpolator(new AccelerateDecelerateInterpolator());
            anim.start();
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(Color.parseColor("#388E3C"));
                    }
                    tb1.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }
    public void confirmaOpcoes(View v){
        final View tb1 = findViewById(R.id.toolbarOpcoes);
        final View tb2 = findViewById(R.id.toolbarNormal);
        int cx = (tb1.getLeft()+ tb1.getRight());
        int cy = (tb1.getTop() + tb1.getBottom())/2;
        int finalRadius = Math.max(tb2.getWidth(),tb2.getHeight());
        if(viewPager.getCurrentItem()==2) {
            AcessoBD dbAcesso = AcessoBD.getInstance(this);
            dbAcesso.open();
            Spinner spinner = (Spinner) findViewById(R.id.spinner1);
            Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
            ArrayList<Fretado> fretados = dbAcesso.getFretados(spinner.getSelectedItem().toString(), spinner2.getSelectedItem().toString(), "semana");
            RecyclerView rvFretados = (RecyclerView) findViewById(R.id.fretados_recycler);
            RecyclerView.Adapter adaptador = new AdaptadorFretados(fretados);
            rvFretados.swapAdapter(adaptador, true);
        }else if(viewPager.getCurrentItem()==1){
            AcessoBD dbAcesso = AcessoBD.getInstance(this);
            dbAcesso.open();
            Spinner spinner = (Spinner) findViewById(R.id.spinner1);
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
            String RA = sharedPrefs.getString("RA", "");
            String semana;
            if(spinner.getSelectedItem().toString().equals("Semana 1")){
                semana = "quinzenal 1";
            }
            else{
                semana = "quinzenal 2";
            }
            ArrayList<Aula> aulas = dbAcesso.getAulas(RA, semana);
            RecyclerView rvAulas = (RecyclerView) findViewById(R.id.my_recycler_view);
            Horarios hr = new Horarios();
            aulas = hr.setarTipos(aulas);
            RecyclerView.Adapter adaptador = new MyAdapter(aulas, MyData.tipo);
            rvAulas.swapAdapter(adaptador, true);
        }
        if(true){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                Animator anim = ViewAnimationUtils.createCircularReveal(tb1,cx,cy,finalRadius,0);
                anim.setDuration(200);
                anim.setInterpolator(new AccelerateDecelerateInterpolator());
                anim.start();
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            window.setStatusBarColor(Color.parseColor("#388E3C"));
                        }
                        tb1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }

        }
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
                FloatingActionButton fabFretados = (FloatingActionButton)findViewById(R.id.FretadoFAB);
                if(position==2){
                    fabFretados.show();
                }
                else{
                    fabFretados.hide();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state){
                if(state==1) {
                    confirmaOpcoes(0);
                }
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HamburguerMenu(), "Menu");
        adapter.addFragment(new Horarios(), "Horários");
        adapter.addFragment(new FragmentFretados(), "FragmentFretados");
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
    public void irParaConfiguracoes(View view){
        Toast.makeText(this, "Nada para ser configurado ainda!",Toast.LENGTH_SHORT).show();
    }
    public void irParaRU(View view){
        viewPager.setCurrentItem(3,true);
    }
    public void irParaHorarios(View view){
        viewPager.setCurrentItem(1,true);
    }
    public void irParaFretados(View view){
        viewPager.setCurrentItem(2,true);
    }
}
