package kr.ac.cnu.cse.ms_hw03_201502043_kimhyeockjin1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edittext;
    EditText result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext = (EditText) findViewById(R.id.edittext);
        result =  (EditText) findViewById(R.id.result);
        Button btn1 = (Button) findViewById(R.id.n1);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext.append("1");
            }
        });
        Button btn2 = (Button) findViewById(R.id.n2);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext.append("2");
            }
        });

        Button btn3 = (Button) findViewById(R.id.n3);
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext.append("3");
            }
        });
        Button btn4 = (Button) findViewById(R.id.n4);
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext.append("4");
            }
        });

        Button btn5 = (Button) findViewById(R.id.n5);
        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext.append("5");
            }
        });
        Button btn6 = (Button) findViewById(R.id.n6);
        btn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext.append("6");
            }
        });

        Button btn7 = (Button) findViewById(R.id.n7);
        btn7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext.append("7");
            }
        });
        Button btn8 = (Button) findViewById(R.id.n8);
        btn8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext.append("8");
            }
        });
        Button btn9 = (Button) findViewById(R.id.n9);
        btn9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext.append("9");
            }
        });
        Button btn0 = (Button) findViewById(R.id.n0);
        btn0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext.append("0");
            }
        });

        Button clearBtn = (Button) findViewById(R.id.clear);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext.setText("");
                result.setText("");
            }
        });
        Button divBtn = (Button) findViewById(R.id.div);
        divBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int lastNum = edittext.length();
                CharSequence last = edittext.getText().subSequence(lastNum-1,lastNum);
                if ("+-*/".contains(last)) {
                    edittext.setText(edittext.getText().subSequence(0,lastNum-1));
                }
                edittext.append("/");
            }
        });

        Button mulBtn = (Button) findViewById(R.id.mul);
        mulBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int lastNum = edittext.length();
                CharSequence last = edittext.getText().subSequence(lastNum-1,lastNum);
                if ("+-*/".contains(last)) {
                    edittext.setText(edittext.getText().subSequence(0,lastNum-1));
                }
                edittext.append("*");
            }
        });

        Button plusBtn = (Button) findViewById(R.id.plu);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int lastNum = edittext.length();
                CharSequence last = edittext.getText().subSequence(lastNum-1,lastNum);
                if ("+-*/".contains(last)) {
                    edittext.setText(edittext.getText().subSequence(0,lastNum-1));
                }
                edittext.append("+");
            }
        });

        Button subBtn = (Button) findViewById(R.id.sub);
        subBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int lastNum = edittext.length();
                CharSequence last = edittext.getText().subSequence(lastNum-1,lastNum);
                if ("+-*/".contains(last)) {
                    edittext.setText(edittext.getText().subSequence(0,lastNum-1));
                }
                edittext.append("-");
            }
        });
        Button eqBtn = (Button) findViewById(R.id.eq);
        eqBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calculate();
            }
        });

    }

    private void calculate(){
        String str = edittext.getText().toString();
        String pattern1 = "[\\*\\+-/]";
        String pattern2 = "[0-9]+";
        String[] operand = str.split(pattern1);
        String[] operator = str.split(pattern2);

        List<String> operands = new ArrayList<>(Arrays.asList(operand));
        List<String> operators = new ArrayList<>(Arrays.asList(operator));

        for (int i = 0; i < operators.size(); ++i) {
            if (operators.get(i).equals("*") || operators.get(i).equals("/")) {
                int operand2 = Integer.parseInt(operands.remove(i));
                int operand1 = Integer.parseInt(operands.remove(i-1));
                if (operators.remove(i).equals("*")) {
                    int result = operand1 * operand2;
                    operands.add(i-1, String.valueOf(result));
                } else{
                    int result = operand1 / operand2;
                    operands.add(i-1, String.valueOf(result));
                }
                --i;
            }
        }
        for (int i = 0; i < operators.size(); ++i) {
            if (operators.get(i).equals("+") || operators.get(i).equals("-")) {
                int operand2 = Integer.parseInt(operands.remove(i));
                int operand1 = Integer.parseInt(operands.remove(i-1));
                if (operators.remove(i).equals("+")) {
                    int result = operand1 + operand2;
                    operands.add(i-1, String.valueOf(result));
                } else{
                    int result = operand1 - operand2;
                    operands.add(i-1, String.valueOf(result));
                }
                --i;
            }
        }
        result.setText(operands.get(0));
        edittext.setText("");

    }
}
