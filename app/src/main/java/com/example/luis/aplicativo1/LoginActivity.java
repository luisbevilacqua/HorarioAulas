package com.example.luis.aplicativo1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(sharedPreferences.contains("RA")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            setContentView(R.layout.activity_login);
            final TextInputLayout nomeWrapper = (TextInputLayout) findViewById(R.id.nomeWrapper);
            final TextInputLayout raWrapper = (TextInputLayout) findViewById(R.id.raWrapper);
            nomeWrapper.setHint("Login");
            raWrapper.setHint("RA");
        }
    }
    public void logar (View view){
        EditText Nome = (EditText) findViewById(R.id.editNome);
        EditText RA = (EditText) findViewById(R.id.editRA);
        TextView txtInfoUser = (TextView) findViewById(R.id.txtInfoUser);
        final TextInputLayout nomeWrapper = (TextInputLayout) findViewById(R.id.nomeWrapper);
        final TextInputLayout raWrapper = (TextInputLayout) findViewById(R.id.raWrapper);
        AcessoBD dbAcesso = AcessoBD.getInstance(this.getBaseContext());
        dbAcesso.open();
        if(!Nome.getText().toString().matches("[a-z.]*")&&!Nome.getText().toString().equals("")){
            nomeWrapper.setError(" ");
            txtInfoUser.setTextColor(Color.RED);
        }
        else if(dbAcesso.isValidRA(RA.getText().toString())) {
            raWrapper.setErrorEnabled(false);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("RA", RA.getText().toString());
            editor.commit();
            dbAcesso.close();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            txtInfoUser.setTextColor(Color.GRAY);
            nomeWrapper.setErrorEnabled(false);
            raWrapper.setError("RA inválido");
        }
    }
}
