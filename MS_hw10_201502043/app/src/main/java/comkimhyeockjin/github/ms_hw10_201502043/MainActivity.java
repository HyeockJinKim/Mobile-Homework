package comkimhyeockjin.github.ms_hw10_201502043;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView userName;
    TextView phoneNum;
    TextView position;
    int count = 0;
    boolean isRunning = false;
    ProgressHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        userName = (TextView) findViewById(R.id.userName);
        phoneNum = (TextView) findViewById(R.id.phoneNum);
        position = (TextView) findViewById(R.id.position);
        handler = new ProgressHandler();

        imageView.setImageResource(R.drawable.customer);

        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRunning) {
                    isRunning = true;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (isRunning) {
                                    count = (count + 1) % 3;
                                    Message message = handler.obtainMessage();
                                    handler.sendMessage(message);
                                    Thread.sleep(2000);
                                }
                            } catch (Exception e) {
                                Log.e("MainActivity", "Exception occur", e);
                            }
                        }
                    }).start();
                }
            }
        });

        Button pause = (Button) findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = false;
            }
        });

    }

    class ProgressHandler extends Handler {
        public void handleMessage(Message message) {

            switch (count) {
                case 0:
                    userName.setText("김혁진");
                    phoneNum.setText("010-3474-9112");
                    position.setText("탄방동");
                   break;
                case 1:
                    userName.setText("김수진");
                    phoneNum.setText("010-3244-6407");
                    position.setText("전민동");
                    break;
                case 2:
                    userName.setText("김민호");
                    phoneNum.setText("010-8768-3802");
                    position.setText("충남대");
                    break;
            }


        }

    }

}
