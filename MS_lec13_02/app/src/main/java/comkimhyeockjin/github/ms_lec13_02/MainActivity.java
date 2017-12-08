package comkimhyeockjin.github.ms_lec13_02;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView status;

    public static String DATABASENAME = null;
    private static String TABLENAME = "employee";
    private static int DATABASEVERSION = 1;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status = (TextView) findViewById(R.id.status);
        final EditText input = (EditText) findViewById(R.id.editText);


        Button inquireBtn = (Button) findViewById(R.id.inquireBtn);
        inquireBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DATABASENAME = input.getText().toString();
                boolean isOpen = openDatabase();
                if (isOpen) {
                    executeRawQuery();
                    executeRawQueryParam();
                }
            }
        });

    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASENAME, null, DATABASEVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            println("Creating table [" + TABLENAME + "].");

            try {
                String DROP_SQL = "drop table if exists " + TABLENAME ;
                sqLiteDatabase.execSQL(DROP_SQL);
            } catch (Exception e) {
                Log.e("MainActivity", "Exception in DROP_SQL", e);
            }

            String CREATE_SQL = "create table " + TABLENAME + "( _id integer PRIMARY KEY autoincrement, "
                    + "name text,  age integer,  phone text)";

            try {
                sqLiteDatabase.execSQL(CREATE_SQL);
            } catch (Exception e) {
                Log.e("MainActivity", "Exception in CREATE_SQL", e);
            }

            println("inserting records.");

            try {
                sqLiteDatabase.execSQL("insert into " + TABLENAME + "(name, age, phone) values ( 'KHZ', 22, '010-3474-9112');");
                sqLiteDatabase.execSQL("insert into " + TABLENAME + "(name, age, phone) values ( 'KSJ', 22, '010-3244-6407');");
                sqLiteDatabase.execSQL("insert into " + TABLENAME + "(name, age, phone) values ( 'John', 20, '010-7788-1234');");
            } catch (Exception e) {
                Log.e("MainActivity", "Exception in insert SQL", e);
            }
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            println("opened database [" + DATABASENAME + "].");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            Log.w("MainActivity", "upgrading database from version " + i + " to " + i1);
        }
    }

    private boolean openDatabase() {
        println("opening database [" + DATABASENAME + "].");

        dbHelper = new DatabaseHelper(this);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        return true;
    }

    private void executeRawQuery() {
        println("\nexecuteRawQuery called.\n");
        println(TABLENAME);

        Cursor c1 = sqLiteDatabase.rawQuery("select count(*) asTotal from " + TABLENAME, null);

        c1.moveToNext();
        println("record const : " +c1.getInt(0));
        c1.close();
    }

    private  void executeRawQueryParam() {
        println("\nexecuteRawQueryParam called.\n");

        String SQL = "select name, age, phone " + " from " + TABLENAME + " where age > ?";
        String[] args = {"30"};

        Cursor c1 = sqLiteDatabase.rawQuery(SQL, args);
        int recordCount =  c1.getCount();
        println("cursor count : " + recordCount + "\n");

        for (int i = 0; i < recordCount; ++i) {
            c1.moveToNext();
            String name = c1.getString(0);
            int age = c1.getInt(1);
            String phone = c1.getString(2);

            println("Record #"+ i + " : " + name + ", " + age + ", " + phone);
        }
        c1.close();
    }


    private void println(String string) {
        Log.d("MainActivity", string);
        status.append("\n"+string);
    }
}
