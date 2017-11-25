package comkimhyeockjin.github.ms_hw04_201502043_kimhyeockjin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PermissionActivity extends AppCompatActivity {
    TextView permission;
    String menu;
    String success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        permission = (TextView) findViewById(R.id.permission);
        Intent intent = getIntent();
        menu =  intent.getExtras().getString("permission");
        success = intent.getExtras().getString("per");
        permission.setText(menu + " 권한 인증" + success);
        Toast.makeText(this, "titleMsg : " + menu + "권한 인증" + success, Toast.LENGTH_SHORT).show();

    }

    public void onClickMenuBtn(View v) {
        Intent intent = new Intent();
        intent.putExtra("permission", menu);
        setResult(RESULT_OK, intent);
        finish();
    }


}
