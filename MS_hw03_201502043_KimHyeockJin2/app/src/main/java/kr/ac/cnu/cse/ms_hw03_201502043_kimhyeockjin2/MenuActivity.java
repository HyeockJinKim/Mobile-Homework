package kr.ac.cnu.cse.ms_hw03_201502043_kimhyeockjin2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    Button guest;
    Button sales;
    Button product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        guest = (Button) findViewById(R.id.guest);
        sales = (Button) findViewById(R.id.sales);
        product = (Button) findViewById(R.id.product);

        Intent intent = getIntent();
        String[] idpw = intent.getExtras().getStringArray("idpw");
        Toast.makeText(this, "username : " + idpw[0]
                + "\npassword : " + idpw[1], Toast.LENGTH_SHORT).show();
    }

    public void onClickBtn1(View v) {
        Intent intent = new Intent();
        intent.putExtra("manage", guest.getText());

        setResult(RESULT_OK, intent);
        finish();
    }

    public void onClickBtn2(View v) {
        Intent intent = new Intent();
        intent.putExtra("manage", sales.getText());

        setResult(RESULT_OK, intent);
        finish();
    }

    public void onClickBtn3(View v) {
        Intent intent = new Intent();
        intent.putExtra("manage", product.getText());

        setResult(RESULT_OK, intent);
        finish();
    }


}
