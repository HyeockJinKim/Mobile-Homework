package comkimhyeockjin.github.ms_lec13_01;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String databaseName;
    String tableName;
    TextView status;
    boolean isDatabaseCreated = false;
    boolean isTableCreated = false;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText databaseNameInput = (EditText) findViewById(R.id.database);
        final EditText tableNameInput = (EditText) findViewById(R.id.table);

        Button databaseBtn = (Button) findViewById(R.id.databaseBtn);
        Button tableBtn = (Button) findViewById(R.id.tableBtn);

        databaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseName = databaseNameInput.getText().toString();
                createDatabase(databaseName);
            }
        });

        tableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableName = databaseNameInput.getText().toString();
                createTable(tableName);
                int count = insertRecord(tableName);
                println(count + " records inserted.");
            }
        });

        status = (TextView) findViewById(R.id.status);
    }

    private void createDatabase(String name) {
        println("creating database [" + name + "].");
        try {
            sqLiteDatabase = openOrCreateDatabase(name, Activity.MODE_PRIVATE, null);
            isDatabaseCreated = true;
        } catch (Exception e) {
            e.printStackTrace();
            println("database is not created");
        }

    }

    private void createTable(String name) {
        println("creating table [" + name + "].");
        try {
            sqLiteDatabase.execSQL("create table if not exists " + name + "("
                            + "_id integer PRIMARY KEY autoincrement, "
                            + "name text, age integer, phone text);");
            isTableCreated = true;
        } catch (Exception e) {
            e.printStackTrace();
            println("database is not created");
        }

    }

    private int insertRecord(String name) {
        println("inserting records into table " + name + ".");

        int count = 3;
        sqLiteDatabase.execSQL("insert into " + name + "(name, age, phone) values ('KHZ', 22, 010-3474-9112);");
        sqLiteDatabase.execSQL("insert into " + name + "(name, age, phone) values ('KSJ', 22, 010-3244-6407);");
        sqLiteDatabase.execSQL("insert into " + name + "(name, age, phone) values ('John', 20, 010-7788-1234);");
        return count;
    }

    private void println(String string) {
        Log.d("MainActivity", string);
        status.append("\n"+string);
    }

    private int insertRecordParam(String name) {
        println("inserting records using parameters.");
        int count = 1;
        ContentValues recordValues = new ContentValues();

        recordValues.put("name", "Rice");
        recordValues.put("age", 201502043);
        recordValues.put("phone", "010-3474-9112");
        int rowPosition = (int) sqLiteDatabase.insert(name, null, recordValues);

        return count;
    }

    private int updateRecordParam(String name) {
        println("updating records using parameters.");

        ContentValues recordValues = new ContentValues();

        recordValues.put("age", 201502043);
        String[] whereArgs = {"Rice"};

        int rowAffected = sqLiteDatabase.update(name, recordValues, "name = ?", whereArgs);
        return rowAffected;
    }

    private int deleteRecordParam(String name) {
        println("deleting records using parameters");
        String[] whereArgs = {"Rice"};

        int rowAffected = sqLiteDatabase.update(name, recordValues, "name = ?", whereArgs);
        return rowAffected;
    }

    private void executeRawQueryParam2(String tableName) {
        println("\nexecuteRawQueryParam2 called.\n");
        String[] columns = {"name", "age", "phone"};
        String whereStr = " age > ?";
        String[] whereParams = {"30"};
        Cursor c1 = sqLiteDatabase.query(tableName, columns, whereStr, whereParams, null, null, null);

        int recordCount = c1.getCount();
        println("cursor count : " + recordCount + "\n");

        for (int i = 0; i < recordCount; ++i) {
            c1.moveToNext();
            String name = c1.getString(0);
            int age = c1.getInt(1);
            String phone = c1.getString(2);

            println("Record #" + i + " : " + ", "+ age + ", " + phone);
        }
        c1.close();

    }
}
