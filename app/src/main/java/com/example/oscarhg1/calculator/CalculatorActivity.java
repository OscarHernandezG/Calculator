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
    private String textNum = "";
    private TextView numView;
    private TextView numView0;

    private Boolean isPeriodActive = false;
    private Boolean isNewOperation = true;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        numView = findViewById(R.id.curr_num);
        numView0 = findViewById(R.id.first_num);
    }

    public void onClickDigit(View view)
    {
        if (isNewOperation)
        {
            CleanCalculator();
            isNewOperation = false;
        }

        Button b = (Button)view;
        num += b.getText().toString().charAt(0);

        numView.setText(num);
    }

    public void onClickOper(View view) {

        if (!num.isEmpty()) {
            num0 = num;
            num = "";

            numView.setText(num);

            Button b = (Button) view;

            operationType = operation.fromInteger(Integer.parseInt(b.getTag().toString()));
            switch (operationType) {
                case Add:
                    textNum = num0 + " + ";
                    break;
                case Sub:
                    textNum = num0 + " - ";
                    break;
                case Mult:
                    textNum = num0 + " x ";
                    break;
                case Div:
                    textNum = num0 + " ÷ ";
                    break;
            }

            numView0.setText(textNum);

            isPeriodActive = false;
            isNewOperation = false;
        }
    }

    public void OnClickEquals(View view)
    {
        if (!num0.isEmpty() && !num.isEmpty())
        {
            double number0 = Double.parseDouble(num0);
            double number1 = Double.parseDouble(num);

            textNum += num;

            double result = 0;

            switch (operationType)
            {
                case Add:
                    result = number0 + number1;
                    break;
                case Sub:
                    result = number0 - number1;
                    break;
                case Mult:
                    result = number0 * number1;
                    break;
                case Div:
                    result = number0 / number1;
                    break;
            }
            num = Double.toString(result);

            numView0.setText(textNum);
            numView.setText(num);

            isPeriodActive = false;
            isNewOperation = true;
        }
    }

    public void OnClickPeriod(View view)
    {
        if (!isPeriodActive)
        {
            num += ".";

            numView.setText(num);

            isPeriodActive = true;
        }
    }

    public void OnClickReset(View view)
    {
        CleanCalculator();
    }


    public void CleanCalculator()
    {
        num = "";
        num0 = "";
        textNum = "";
        isPeriodActive = false;

        numView0.setText(textNum);
        numView.setText(num);
    }
}
