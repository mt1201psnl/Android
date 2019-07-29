package com.example.quiz_poke.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quiz_poke.PokeQuizConst;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* SQLiteDBファイルを取り込むためのクラス */
public class InsertData extends SQLiteOpenHelper {

    private Context context;
    private File dbPath;

    public InsertData(Context context) {
        super(context, PokeQuizConst.DATABASE_NAME, null, PokeQuizConst.DB_VERSION);
        this.context = context;
        this.dbPath = context.getDatabasePath(PokeQuizConst.DATABASE_NAME);
    }

    @Override
    public synchronized SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 対象dbをopen
        super.onOpen(db);
    }

    public SQLiteDatabase copyDatabase(SQLiteDatabase database) throws IOException {

        database.close();

        InputStream input = context.getAssets().open(PokeQuizConst.DB_FILE_NAME);
        OutputStream output = new FileOutputStream(this.dbPath);
        int record = copy(input, output);
        System.out.println(record + "件取り込み");

        return super.getWritableDatabase();
    }

    private static int copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024 * 4];
        int count = 0;
        int n;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
