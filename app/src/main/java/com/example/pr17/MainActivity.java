package com.example.pr17;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd, btnRead, btnClear;
    EditText etName, etAnimal, etSize, etWeight;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etAnimal = (EditText) findViewById(R.id.etAnimal);
        etSize = (EditText) findViewById(R.id.etSize);
        etWeight = (EditText) findViewById(R.id.etWeight);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();
        String Animal = etAnimal.getText().toString();
        String Size = etSize.getText().toString();
        String Weight = etWeight.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {

            case R.id.btnAdd:
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_Animal, Animal);
                contentValues.put(DBHelper.KEY_Size, Size);
                contentValues.put(DBHelper.KEY_Weight, Weight);

                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                break;

            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int AnimalIndex = cursor.getColumnIndex(DBHelper.KEY_Animal);
                    int SizeIndex = cursor.getColumnIndex(DBHelper.KEY_Size);
                    int WeightIndex = cursor.getColumnIndex(DBHelper.KEY_Weight);
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) + ", name = " + cursor.getString(nameIndex) + ", Animal = " + cursor.getString(AnimalIndex) +", Size =" + cursor.getString(SizeIndex)+"Weight = "+ cursor.getString(WeightIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");

                cursor.close();
                break;

            case R.id.btnClear:
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;
        }
        dbHelper.close();
    }
}

class DBHelper  extends SQLiteOpenHelper {
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_Animal = "Animal";
    public static final String KEY_Size = "Size";
    public static final String KEY_Weight = "Weight";

    public DBHelper(Context context) {
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID
                + " integer primary key," + KEY_NAME + " text," + KEY_Animal + " text" + KEY_Size + "text" + KEY_Weight + "text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);

    }
}