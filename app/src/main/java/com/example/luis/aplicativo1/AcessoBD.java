package com.example.luis.aplicativo1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaCodecInfo;

import java.util.ArrayList;

/**
 * Created by luis on 08/07/16.
 */
public class AcessoBD {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static AcessoBD instance;

    private AcessoBD(Context context){
        this.openHelper = new ExportadorDeDatabase(context);
    }

    public static AcessoBD getInstance(Context context){
        if(instance == null){
            instance = new AcessoBD(context);
        }
        return instance;
    }

    public void open(){
        this.database = openHelper.getWritableDatabase();
    }
    public void close(){
        if(database != null){
            this.database.close();
        }
    }

    public ArrayList<Aula> getAulas(String RA){
        Cursor cursor = database.rawQuery("SELECT nomeDisciplina, Hora, Dia, Professor, Sala FROM aulas, matriculas, disciplina WHERE matriculas.RA = \""+ RA +"\" AND aulas.codDisciplina = matriculas.codDisciplina AND matriculas.codDisciplina = disciplina.codDisciplina " +
                "ORDER BY (CASE WHEN Dia = 'segunda' THEN 1 WHEN Dia = 'terca' THEN 2 WHEN Dia = 'quarta' THEN 3 WHEN Dia = 'quinta' THEN 4 WHEN Dia = 'sexta' THEN 5 WHEN Dia = 'sabado' THEN 6 END), Hora;",null);
        cursor.moveToFirst();
        ArrayList<Aula> resultSet = new ArrayList<>();
        if(cursor.getCount()==0){
            Aula aula = new Aula("00","00","NENHUMA AULA","","","Sem aulas");
            resultSet.add(aula);
            return resultSet;
        }
        do{
            String horas = cursor.getString(1).substring(0,2);
            String minutos = cursor.getString(1).substring(3,5);
            String disciplina = cursor.getString(0);
            String professor = cursor.getString(3);
            String sala = cursor.getString(4);
            String dia = cursor.getString(2);
            Aula aula = new Aula(horas,minutos,disciplina,professor,sala,dia);
            resultSet.add(aula);
        }while(cursor.moveToNext());
        database.close();
        cursor.close();
        return resultSet;
    }

    public boolean isValidRA (String RA){
        Cursor cursor = database.rawQuery("SELECT * FROM alunos WHERE RA = \"" + RA + "\"",null);

        return(cursor.getCount()>0);
    }
}
