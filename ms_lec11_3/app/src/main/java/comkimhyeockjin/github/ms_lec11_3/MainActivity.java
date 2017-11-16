package comkimhyeockjin.github.ms_lec11_3;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar bar;
    TextView textView;
    boolean isRunning = true;
    Handler handler;
    ProgressRunnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = (ProgressBar) findViewById(R.id.progress);
        textView = (TextView) findViewById(R.id.textView);

        handler = new Handler();
        runnable = new ProgressRunnable();
    }

    @Override
    protected void onStart() {
        super.onStart();

        bar.setProgress(0);
        isRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 20 && isRunning; ++i) {
                        Thread.sleep(1000);

                        handler.post(runnable);
                    }
                } catch (Exception e) {
                    Log.e("SampleThreadActivity", "Exception in processing message.", e);
                }
            }
        }).start();
    }

    @Override
    public void onStop() {
        super.onStop();
        isRunning = false;
    }


    public class ProgressRunnable implements Runnable {
        @Override
        public void run() {
            bar.incrementProgressBy(5);

            if (bar.getProgress() == bar.getMax()) {
                textView.setText("Runnable Done");
            } else {
                textView.setText("Runnable Working ..." + bar.getProgress());
            }
        }
    }
}
