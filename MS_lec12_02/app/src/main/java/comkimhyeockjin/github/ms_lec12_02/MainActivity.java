package comkimhyeockjin.github.ms_lec12_02;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textMsg;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText.setText("http://m.naver.com");
        textMsg = (TextView) findViewById(R.id.textMsg);

        Button requestBtn = (Button) findViewById(R.id.button);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String url = editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String output = request(url);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                textMsg.setText(output);
                            }
                        });

                    }
                }).start();
            }
        });
    }

    private String request(String urlStr) {
        StringBuilder output = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection != null) {
                connection.setConnectTimeout(10000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line = null;
                while (true) {
                    line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    output.append(line + "\n");
                }

                reader.close();
                connection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();

    }
}
