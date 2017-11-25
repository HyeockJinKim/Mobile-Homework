package comkimhyeockjin.github.ms_hw06_201502043;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    EditText url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView);
        url = (EditText) findViewById(R.id.url);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("http://m.naver.com");
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String faillingUrl) {
            super.onReceivedError(view, errorCode, description, faillingUrl);
            Toast.makeText(MainActivity.this, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
        }

    }

    public void clickNaver(View v) {
        webView.loadUrl("http://m.naver.com");
    }
    public void clickMove(View v) {
        String urlText = url.getText().toString();

        if (URLUtil.isValidUrl(urlText)) {
            webView.loadUrl(urlText);
        } else {
            Toast.makeText(MainActivity.this, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
        }

    }

}
