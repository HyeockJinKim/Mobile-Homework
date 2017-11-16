package comkimhyeockjin.github.ms_lec11_2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar bar;
    TextView textView;
    boolean isRunning = false;
    ProgressHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = (ProgressBar) findViewById(R.id.progress);
        textView = (TextView) findViewById(R.id.textView);

        handler = new ProgressHandler();
    }

    @Override
    public void onStart() {
        super.onStart();

        bar.setProgress(0);
        isRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 20 && isRunning; ++i) {
                        Thread.sleep(1000);
                        Message message = handler.obtainMessage();
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    Log.e("MainActivity", "Exception in processing message.", e);
                }
            }
        }).start();
    }

    @Override
    public void onStop() {
        super.onStop();

        isRunning = false;
    }

    class ProgressHandler extends Handler{
        public void handleMessage(Message message) {
            bar.incrementProgressBy(5);

            if (bar.getProgress() == bar.getMax()) {
                textView.setText("Done");
            } else {
                textView.setText("Working ..." + bar.getProgress());
            }

        }

    }
}
