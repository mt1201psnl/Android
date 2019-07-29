package com.example.quiz_poke.domainLogic;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quiz_poke.PokeQuizConst;
import com.example.quiz_poke.vo.PokemonVo;

import java.util.LinkedList;

public class ReadAllRecord {

    public LinkedList<PokemonVo> execute(SQLiteDatabase db) {

        Cursor cursor = db.query(PokeQuizConst.TABLE_NAME, null, null, null, null, null, "ID ASC");
        cursor.moveToFirst();

        LinkedList<PokemonVo> allRecordList = new LinkedList<>();

        for (int i = 0; i < cursor.getCount(); i++) {

            PokemonVo vo = new PokemonVo();

            vo.setImage(cursor.getString(1));
            vo.setName(cursor.getString(2));
            vo.setType_1(cursor.getString(3));
            vo.setType_2(cursor.getString(4));
            vo.setCharacteristic_1(cursor.getString(5));
            vo.setCharacteristic_2(cursor.getString(6));
            vo.setCharacteristic_dream(cursor.getString(7));
            vo.setSpecies(cursor.getString(8));
            vo.setMost_used_move(cursor.getString(9));

            allRecordList.add(vo);
            cursor.moveToNext();
        }

        cursor.close();
        return allRecordList;
    }
}
