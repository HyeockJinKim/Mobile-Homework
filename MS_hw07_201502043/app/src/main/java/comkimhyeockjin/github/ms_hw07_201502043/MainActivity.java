package comkimhyeockjin.github.ms_hw07_201502043;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AlertDialogLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText phone;
    EditText birth;
    TextView numOfPeople;
    ListView listView;
    CustomerAdapter adapter;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        birth = (EditText) findViewById(R.id.birth);
        numOfPeople = (TextView) findViewById(R.id.numOfPeople);
        listView = (ListView) findViewById(R.id.listView);

        adapter = new CustomerAdapter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);

                alertDialogBuilder.setTitle("안내");
                alertDialogBuilder.setMessage("삭제하시겠습니까?")
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return ;
                            }
                        })
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.removeItem(position);
                                listView.setAdapter(adapter);
                                numOfPeople.setText(Integer.parseInt(numOfPeople.getText().toString().split(" ")[0])-1 +" 명");

                            }
                        });

                alertDialogBuilder.show();

            }
        });
    }

    public void addInfo(View v) {
        adapter.addItem(new SingerItem(R.drawable.customer, name.getText().toString(),
                birth.getText().toString(), phone.getText().toString()));
        name.setText("");
        birth.setText("");
        phone.setText("");
        numOfPeople.setText(Integer.parseInt(numOfPeople.getText().toString().split(" ")[0])+1 +" 명");
        listView.setAdapter(adapter);

    }

    class CustomerAdapter extends BaseAdapter {
        ArrayList<SingerItem> items = new ArrayList<>();

        @Override
        public int getCount() {
            return items.size();
        }
        public void addItem(SingerItem item) { items.add(item);}
        public void removeItem(int position) { items.remove(position); }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SingerItemView view = new SingerItemView(getApplicationContext());

            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setBirth(item.getBirth());
            view.setPhone(item.getPhone());
            view.setIcon(item.getIcon());
            return view;
        }
    }
}


