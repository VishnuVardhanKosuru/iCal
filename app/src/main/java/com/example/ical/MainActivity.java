package com.example.ical;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button one, two, three, four, five, six, seven, eight, nine, zero, zerozero, divide, multiply, sum, substract, modulo, ac, C, equalsto, decimal;
    TextView txtExpression, txtResult;
    String expression;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        zerozero = findViewById(R.id.zerozero);
        decimal = findViewById(R.id.decimal);
        divide = findViewById(R.id.divide);
        substract = findViewById(R.id.substract);
        modulo = findViewById(R.id.modulo);
        sum = findViewById(R.id.sum);
        ac = findViewById(R.id.ac);
        C = findViewById(R.id.C);
        equalsto = findViewById(R.id.equalsto);
        multiply = findViewById(R.id.multiply);

        // Set onClick listeners
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        zerozero.setOnClickListener(this);
        decimal.setOnClickListener(this);
        divide.setOnClickListener(this);
        substract.setOnClickListener(this);
        modulo.setOnClickListener(this);
        sum.setOnClickListener(this);
        ac.setOnClickListener(this);
        C.setOnClickListener(this);
        equalsto.setOnClickListener(this);
        multiply.setOnClickListener(this);
        txtExpression = findViewById(R.id.txtexpression);
        txtResult= findViewById(R.id.txtresult);

        // Apply window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {
        // Handle button clicks here
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        expression = txtExpression.getText().toString();
        if(buttonText.equals("AC")){
            txtExpression.setText("");
            txtResult.setText("");
            expression="";
        }
        else if(buttonText.equals("C")){
            txtResult.setText("");
            expression=expression.substring(0,expression.length()-1);
            txtExpression.setText(expression);
        }
        else if(buttonText.equals("=")){
            double result = evaluateExpression(expression);
            String finalResult = String.valueOf(result);
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            txtResult.setText(expression);
            txtExpression.setText(finalResult);
            expression="";
        }
        else{
            expression = expression+buttonText;
            txtExpression.setText(expression);
            txtResult.setText("");

        }
    }

    private double evaluateExpression(String expression) {
        try{
            double result = new ExpressionBuilder(expression).build().evaluate();
            if(Double.isNaN(result)){
                throw new ArithmeticException("Invalid expression or Division by Zero");
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return Double.NaN;
        }
    }
}
