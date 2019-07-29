package com.example.quiz_poke.db.accessor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quiz_poke.PokeQuizConst;

/* DBAccessor for PokemonDB */
public class DBA_PokemonDb extends SQLiteOpenHelper {

    public DBA_PokemonDb(Context context) {
        super(context, PokeQuizConst.DATABASE_NAME, null, PokeQuizConst.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
