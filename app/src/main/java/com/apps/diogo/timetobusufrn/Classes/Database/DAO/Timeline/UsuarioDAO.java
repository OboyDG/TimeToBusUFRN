package com.apps.diogo.timetobusufrn.Classes.Database.DAO.Timeline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apps.diogo.timetobusufrn.Classes.Database.BancoTimeline;
import com.apps.diogo.timetobusufrn.Classes.Modelos.Usuario;

/**
 * Created by Diogo on 27/10/2017.
 */

public class UsuarioDAO
{
    private SQLiteDatabase db;
    private BancoTimeline banco;
    
    public UsuarioDAO(Context context)
    {
        banco = new BancoTimeline(context);
    }
    
    public String insertUsuario(Usuario user)
    {
        ContentValues valores;
        long resultado;
        
        db = banco.getWritableDatabase();
        
        valores = new ContentValues();
        
        valores.put(BancoTimeline.MATRICULA, user.getMatricula());
        valores.put(BancoTimeline.SENHA, user.getSenha());
        valores.put(BancoTimeline.NOME, user.getNome());
        valores.put(BancoTimeline.FOTO, user.getFoto());
        
        resultado = db.insert(BancoTimeline.TABELA1, null, valores);
        db.close();
        
        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }
    
    public Cursor selectAllUsuarios()
    {
        Cursor cursor;
        
        String[] campos =  {banco.MATRICULA, banco.SENHA, banco.NOME, banco.FOTO};
        
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA1, campos, null, null, null, null, null, null);
        
        if(cursor!=null){
            cursor.moveToFirst();
        }
        
        db.close();
        
        return cursor;
    }
    
    public Cursor selectUsuarioByMatricula(int matricula, boolean senha, boolean foto)
    {
        Cursor cursor;
        String[] campos = BancoTimeline.getStringsUsuario(senha, foto);
        
        String where = BancoTimeline.MATRICULA + "=" + matricula;
        
        db = banco.getReadableDatabase();
        
        cursor = db.query(BancoTimeline.TABELA1,campos,where, null, null, null, null, null);
        
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    
    public Cursor selectUsuarioByMatricula(int matricula)
    {
        return selectUsuarioByMatricula(matricula, true, true);
    }
    
    public Cursor selectUsuarioNoPass(int matricula)
    {
        return selectUsuarioByMatricula(matricula, false, true);
    }
    
    public Cursor selectUsuarioNoPassNoPhoto(int matricula)
    {
        return selectUsuarioByMatricula(matricula, false, false);
    }
    
    public Cursor selectUsuarioValido(int matricula, String senha, boolean comFoto)
    {
        Cursor cursor;
        String[] campos;
        
        if( comFoto )
        {
            campos = new String[3];
            campos[0] = banco.MATRICULA;
            campos[1] = banco.NOME;
            campos[2] = banco.FOTO;
        }
        else
        {
            campos = new String[2];
            campos[0] = banco.MATRICULA;
            campos[1] = banco.NOME;
        }
        
        String where = BancoTimeline.MATRICULA + " = " + matricula + " and " + BancoTimeline.SENHA + " = '" + senha + "'";
        
        db = banco.getReadableDatabase();
        
        cursor = db.query(BancoTimeline.TABELA1,campos,where, null, null, null, null, null);
        
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    
    
    public void updateUsuario(Usuario user)
    {
        ContentValues valores;
        String where;
        
        db = banco.getWritableDatabase();
        
        where = BancoTimeline.MATRICULA + "=" + user.getMatricula();
        
        valores = new ContentValues();
        valores.put(BancoTimeline.SENHA, user.getSenha());
        valores.put(BancoTimeline.NOME, user.getNome());
        valores.put(BancoTimeline.FOTO, user.getFoto());
        
        db.update(BancoTimeline.TABELA1,valores,where,null);
        db.close();
    }
    
    public void deleteUsuario(int matricula)
    {
        String where = BancoTimeline.MATRICULA + "=" + matricula;
        db = banco.getReadableDatabase();
        db.delete(BancoTimeline.TABELA1,where,null);
        db.close();
    }
}
