package comkimhyeockjin.github.ms_hw04_201502043_kimhyeockjin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText password;
    EditText idValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password = (EditText) findViewById(R.id.password);
        idValue = (EditText) findViewById(R.id.idValue);
    }
    public void onClickBtn(View v) {
        if (idValue.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "사용자명이나 비밀번호가 입력되지 않았습니다."
                    , Toast.LENGTH_SHORT).show();
            return ;
        }
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        String[] idPw = new String[2];
        idPw[0] = idValue.getText().toString();
        idPw[1] = password.getText().toString();
        intent.putExtra("idpw", idPw);

        startActivityForResult(intent, 101);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 101) {
                if (data != null) {
                    Toast.makeText(getApplicationContext(), data.getExtras().getString("login"),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


}
