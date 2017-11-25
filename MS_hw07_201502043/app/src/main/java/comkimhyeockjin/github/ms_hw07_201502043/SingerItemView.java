package comkimhyeockjin.github.ms_hw07_201502043;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by user on 2017-10-27.
 */

public class SingerItemView extends LinearLayout {

    private ImageView icon;
    private TextView singer_name;
    private TextView birthDay;
    private TextView phone;

    public SingerItemView(Context context) {
        super(context);
        init(context);
    }

    public SingerItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item, this, true);

        icon = (ImageView) findViewById(R.id.imageView);
        singer_name = (TextView) findViewById(R.id.textView);
        birthDay = (TextView) findViewById(R.id.textView2);
        phone = (TextView) findViewById(R.id.textView3);
    }

    public void setIcon(int resId) {
        icon.setImageResource(resId);
    }

    public void setName(String name) {
        singer_name.setText(name);
    }

    public void setBirth(String birth) {
        birthDay.setText(birth);
    }

    public void setPhone(String phoneNum) {
        phone.setText(phoneNum);
    }
}
