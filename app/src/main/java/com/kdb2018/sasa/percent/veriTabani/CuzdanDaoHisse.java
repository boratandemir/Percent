package com.kdb2018.sasa.percent.veriTabani;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kdb2018.sasa.percent.model.CuzdanModel;

import java.util.ArrayList;

public class CuzdanDaoHisse {

    public ArrayList<CuzdanModel.CuzdanModelHisse> Cuzdan (VeriTabani vt){

        ArrayList<CuzdanModel.CuzdanModelHisse> cuzdanArrayList = new ArrayList<>();

        SQLiteDatabase db=vt.getWritableDatabase();

        Cursor c =db.rawQuery("Select * From hisse",null);

        while (c.moveToNext()){
            CuzdanModel.CuzdanModelHisse data = new CuzdanModel.CuzdanModelHisse(c.getInt(c.getColumnIndex("hisse_id"))
                    ,c.getString(c.getColumnIndex("hisse_adi"))
                    ,c.getFloat(c.getColumnIndex("hisse_alis_fiyati"))
                    ,c.getFloat(c.getColumnIndex("hisse_alis_adedi")));

            cuzdanArrayList.add(data);

        }
        db.close();

        return cuzdanArrayList;

    }

    public void HisseEkle(VeriTabani veriTabani,String hisse_adi,Float hisse_alis_fiyati,
                         Float hisse_alis_adedi){

        SQLiteDatabase db = veriTabani.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("hisse_adi",hisse_adi);
        values.put("hisse_alis_fiyati",hisse_alis_fiyati);
        values.put("hisse_alis_adedi",hisse_alis_adedi);

        db.insertOrThrow("hisse",null,values);
        db.close();

    }

    public void HisseSil(VeriTabani vt, int id){
        SQLiteDatabase db = vt.getWritableDatabase();
        db.delete("hisse","hisse_id=?",new String[]{String.valueOf(id)});
        db.close();
    }
}
