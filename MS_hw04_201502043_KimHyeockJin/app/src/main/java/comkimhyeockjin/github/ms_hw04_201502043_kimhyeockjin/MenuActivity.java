package comkimhyeockjin.github.ms_hw04_201502043_kimhyeockjin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    Button position;
    Button camera;
    Button calendar;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        position = (Button) findViewById(R.id.position);
        camera = (Button) findViewById(R.id.camera);
        calendar = (Button) findViewById(R.id.calendar);

        Intent intent = getIntent();
        String[] idpw = intent.getExtras().getStringArray("idpw");
        Toast.makeText(this, "username : " + idpw[0]
                + ", password : " + idpw[1], Toast.LENGTH_SHORT).show();
    }

    public void onClickPositionBtn(View v) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    public void onClickCameraBtn(View v) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 2);
    }

    public void onClickCalendarBtn(View v) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR}, 3);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int [] grantResults) {
        Intent intent = new Intent(MenuActivity.this, PermissionActivity.class);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    intent.putExtra("permission", position.getText());
                    intent.putExtra("per", "");
                } else {
                    intent.putExtra("permission", position.getText());
                    intent.putExtra("per", "실패");
                }
                startActivityForResult(intent, 102);
                return;
            }
            case 2: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    intent.putExtra("permission", camera.getText());
                    intent.putExtra("per", "");
                } else {
                    intent.putExtra("permission", camera.getText());
                    intent.putExtra("per", "실패");
                }
                startActivityForResult(intent, 102);
                return;
            }
            case 3: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    intent.putExtra("permission", calendar.getText());
                    intent.putExtra("per", "");
                } else {
                    intent.putExtra("permission", calendar.getText());
                    intent.putExtra("per", "실패");
                }
                startActivityForResult(intent, 102);
                return;
            }

        }
    }

    public void onClickLoginBtn(View v) {
        Intent intent = new Intent();
        intent.putExtra("login", "로그인 종료");

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Toast.makeText(getApplicationContext(), data.getExtras().getString("permission") + " 권한 응답, result code:"
                            + resultCode + "\nmessage: result message is OK!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
