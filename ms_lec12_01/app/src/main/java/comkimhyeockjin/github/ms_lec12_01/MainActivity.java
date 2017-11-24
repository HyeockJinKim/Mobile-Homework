package comkimhyeockjin.github.ms_lec12_01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String addr = editText.getText().toString().trim();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int port = 5001;

                        try {
                            Socket socket = new Socket(addr, port);
                            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                            outputStream.writeObject("모바일소프트웨어설계");
                            outputStream.flush();

                            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                            String obj = (String) inputStream.readObject();

                            Log.d("MainActivity", obj);
                            socket.close();
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
