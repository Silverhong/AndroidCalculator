package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

public class Calculator extends AppCompatActivity {

    private TextView calculateArea;
    private TextView resultArea;
    private Double result=0.0;
    private Button btn;
    private ImageButton imageButton;
    private Boolean isDot=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        calculateArea = findViewById(R.id.calculateArea);
        resultArea=findViewById(R.id.calculateResult);
    }
    public void btnNumberClick(View view){
        btn = findViewById(view.getId());
        if(btn.getId()==R.id.clear) {
            Clear();
            return;
        }
        if(btn.getId()==R.id.dot) {
            if (!isDot) {
                if (calculateArea.length() > 0) {
                    Character lastChar = calculateArea.getText().charAt(calculateArea.length() - 1);
                    if (Character.isDigit(lastChar)) {
                        calculateArea.append(".");
                    }
                    else if(lastChar.toString().equals(".")){
                        return;
                    }
                    else{
                        calculateArea.append("0.");
                    }
                } else {
                    calculateArea.append("0.");
                }
                isDot=true;
            }
        }
        if(btn.getId()==R.id.backspace){
            if(resultArea.getText().length()>0){
                Clear();
                return;
            }
            if(calculateArea.length()>0){
                String lastText = String.valueOf(calculateArea.getText().charAt(calculateArea.length()-1));
                if(lastText.equals(" ")){
                    calculateArea.setText(calculateArea.getText().toString().substring(0,calculateArea.getText().toString().length()-3));
                    return;
                }
                calculateArea.setText(calculateArea.getText().toString().substring(0,calculateArea.getText().toString().length()-1));
                return;
            }
        }
        if(Character.isDigit(btn.getText().charAt(0))) {
            if(calculateArea.getText().length()==1)
                if(String.valueOf(calculateArea.getText().charAt(0)).equals("0")) {
                    calculateArea.setText(btn.getText().toString());
                    return;
                }
            calculateArea.append(btn.getText().toString());
        }
    }
    public void Calculate(){
        Expression e = new Expression(calculateArea.getText().toString());
        if(!Character.isDigit(calculateArea.getText().charAt(calculateArea.length()-1)))
            e.setExpressionString(calculateArea.getText().toString().substring(0,calculateArea.length()-1));
        result = e.calculate();
        Integer compare = Double.compare(result,result.intValue());
        if(compare==0){
            Integer intResult=result.intValue();
            resultArea.setText(intResult.toString());
            return;
        }
        resultArea.setText(result+"");
    }
    public void ibtnOperationClick(View view){
        imageButton = findViewById(view.getId());
        Character lastChar=0;
        if(calculateArea.getText().length()>0)
            lastChar = calculateArea.getText().charAt(calculateArea.length()-1);
        if(!Character.isDigit(lastChar))
            return;
        if(imageButton.getId()==R.id.plus){
            calculateArea.append("+");
        }
        else if(imageButton.getId()==R.id.minus){
            calculateArea.append("-");
        }
        else if(imageButton.getId()==R.id.multiply){
            calculateArea.append("*");
        }
        else if(imageButton.getId()==R.id.divided){
            calculateArea.append("/");
        }
        else if(imageButton.getId()==R.id.modular){
            calculateArea.append("%");
        }
        isDot=false;
    }
    public void EqualClick(View view){
         imageButton = findViewById(view.getId());
         if(calculateArea.length()>0)
            Calculate();
    }
    private void Clear(){
        result=0.0;
        calculateArea.setText("");
        resultArea.setText("");
        isDot=false;
    }
}
