package com.kdb2018.sasa.percent.veriTabani;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kdb2018.sasa.percent.model.CuzdanModel;

import java.util.ArrayList;

public class CuzdanDaoKripto {
    public ArrayList<CuzdanModel.CuzdanModelKripto> Cuzdan(VeriTabani vt){
        ArrayList<CuzdanModel.CuzdanModelKripto> cuzdanArrayList = new ArrayList<>();

        SQLiteDatabase db=vt.getWritableDatabase();

        Cursor c =db.rawQuery("Select * From kripto",null);

        while (c.moveToNext()){
            CuzdanModel.CuzdanModelKripto data = new CuzdanModel.CuzdanModelKripto(c.getInt(c.getColumnIndex("coin_id"))
                                ,c.getString(c.getColumnIndex("coin_adi"))
                                ,c.getFloat(c.getColumnIndex("coin_alis_fiyati"))
                                ,c.getFloat(c.getColumnIndex("coin_alis_adedi")));

            cuzdanArrayList.add(data);

        }
        db.close();
        return cuzdanArrayList;
    }

    public void CoinEkle(VeriTabani veriTabani,String coin_adi,Float coin_alis_fiyati,
                         Float coin_alis_adedi){

        SQLiteDatabase db = veriTabani.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("coin_adi",coin_adi);
        values.put("coin_alis_fiyati",coin_alis_fiyati);
        values.put("coin_alis_adedi",coin_alis_adedi);

        db.insertOrThrow("kripto",null,values);
                db.close();

    }
    public void CoinSil(VeriTabani vt, int id){
        SQLiteDatabase db = vt.getWritableDatabase();
        db.delete("kripto","coin_id=?",new String[]{String.valueOf(id)});
        db.close();
    }
}
