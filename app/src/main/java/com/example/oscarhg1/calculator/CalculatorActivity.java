package com.example.oscarhg1.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity
{

    private enum operation
    {
        None,
        Add,
        Sub,
        Mult,
        Div;

            public static operation fromInteger(int x) {
                switch(x) {
                    case 0:
                        return None;
                    case 1:
                        return Add;
                    case 2:
                        return Sub;
                    case 3:
                        return Mult;
                    case 4:
                        return Div;
                }
                return null;

        }
    }

    private operation operationType = operation.None;

    private String num = "";
    private String num0 = "";
    private TextView numView;
    private TextView numView0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        numView = findViewById(R.id.curr_num);
        numView0 = findViewById(R.id.first_num);
    }

    public void onClickDigit(View view)
    {
        Button b = (Button)view;
        num += b.getText().toString().charAt(0);

        numView.setText(num);
    }

    public void onClickOper(View view)
    {
        if (!num.isEmpty())
        {
            num0 = num;
            num = "";

            numView0.setText(num0);
            numView.setText(num);

            Button b = (Button) view;

            operationType = operation.fromInteger(Integer.parseInt(b.getTag().toString()));
        }
    }

    public void OnClickEquals(View view)
    {
        if (!num0.isEmpty() && !num.isEmpty())
        {
            int number0 = Integer.parseInt(num0);
            int number1 = Integer.parseInt(num);

            num0 += (" + " + num);

            num = Integer.toString(number0 + number1);

            numView0.setText(num0);
            numView.setText(num);
        }
    }

}
