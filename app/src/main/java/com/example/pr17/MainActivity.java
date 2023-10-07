package com.example.pr17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        ContentValues cv = new ContentValues();
}
    @Override
    public void onClick(View view) {
    }
    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(MainActivity context) {
        // конструкторсуперкласса
        super(context, "myDB", null, 1);
        }

        @Override
            public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаемтаблицусполями
            db.execSQL("create table mytable ("
            + "id integer primary key autoincrement,"
            + "name text,"
            + "email text"+ ");");
            }

            onClick(){
            switch()
                case     :
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                SQLiteDatabasedb = dbHelper.getWritableDatabase();
                cv.put("name", name);
                cv.put("email", email);
                // вставляем запись и получаем ее ID
                longrowID = db.insert("mytable", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = "+ rowID);
                break;
                case R.id.btnRead:
                Log.d(LOG_TAG, "--- Rows in mytable: ---");
                // делаем запрос всех данных из таблицы mytable, получаем Cursor
                Cursor c = db.query("mytable", null, null, null, null, null, null);

                // ставим позицию курсора на первую строку выборки
                // если в выборке нет строк, вернется false
                if(c.moveToFirst()) {

                    // определяем номера столбцов по имени в выборке
                    intidColIndex = c.getColumnIndex("id");
                    intnameColIndex = c.getColumnIndex("name");
                    intemailColIndex = c.getColumnIndex("email");

                    do{
                        // получаем значения по номерам столбцов и пишем все в лог
                        Log.d(LOG_TAG,
                                "ID = "+ c.getInt(idColIndex) +
                                        ", name = "+ c.getString(nameColIndex) +
                                        ", email = "+ c.getString(emailColIndex));
                        // переход на следующую строку
                        // а если следующей нет (текущая - последняя), то false - выходим из цикла
                    } while(c.moveToNext());
                } else
                    Log.d(LOG_TAG, "0 rows");
                c.close();
                break;
                caseR.id.btnClear:
                Log.d(LOG_TAG, "--- Clear mytable: ---");
                // удаляемвсезаписи
                intclearCount = db.delete("mytable", null, null);
                Log.d(LOG_TAG, "deleted rows count = "+ clearCount);
                break;
            }
        // закрываем подключение к БД
    dbHelper.close();
            }

        @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
            }
}
