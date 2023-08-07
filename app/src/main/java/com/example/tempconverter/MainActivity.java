package com.example.tempconverter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //declaring variable
    private EditText editinput;
    private TextView textResult;
    private TextView texttype;
    private TextView textResultType;
    private LinearLayout selecttype;
    private String selectedunit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DecimalFormat df=new DecimalFormat("#.##");
        selectedunit="Fahrenheit";
        texttype=findViewById(R.id.textType);
        selecttype=findViewById(R.id.selectType);
        editinput = findViewById(R.id.editInput);
        textResult=findViewById(R.id.textResult);
        textResultType=findViewById(R.id.textResultType);
        selecttype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });
        editinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String resulttext;
                String inputval=editinput.getText().toString();
                if(!inputval.isEmpty())
                {
                    double doubleinput=Double.parseDouble(inputval);
                    if(selectedunit.equals("Fahrenheit"))
                    {
                        resulttext=df.format((doubleinput-32)*5/9);
                        textResultType.setText("Celsius");
                    }
                    else {
                        resulttext=df.format((doubleinput*9/5)+32);
                        textResultType.setText("Fahrenheit");
                    }
                    textResult.setText(resulttext);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Select Unit");
        final String[] items = new String[]{"Fahrenheit", "Celcius"};
        final int checkedItem = -1;
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedunit = items[which];
                texttype.setText(selectedunit);
                textResultType.setText(items[which]);
                dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton(android.R.string.ok, null);
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }
}