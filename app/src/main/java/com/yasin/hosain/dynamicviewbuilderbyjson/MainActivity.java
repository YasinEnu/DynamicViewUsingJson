package com.yasin.hosain.dynamicviewbuilderbyjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.gson.JsonSyntaxException;
import com.yasin.hosain.dynamicviewusingjson.FormBuilder;
import com.yasin.hosain.dynamicviewusingjson.model.DYView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<DYView> views=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String viewJson="{ \"views\": [ { \"type\": \"imageView\",\"gravity\": \"center\", \"src\": \"https://i.imgur.com/DvpvklR.png\", \"tag\": \"textView1\" }, { \"type\": \"textView\", \"text\": \"Sample Text View\", \"textSize\": 20, \"tag\": \"textView1\" }, { \"type\": \"editText\", \"description\": \"Edit Text in HINT mode - single line\", \"mode\": 1, \"singleLine\": true, \"tag\": \"editText1\" }, { \"type\": \"radioGroup\", \"description\": \"Choose one among these:\", \"options\": [ \"One\", \"Two\", \"Three\" ], \"tag\": \"radioGroup1\" }, { \"type\": \"checkbox\", \"description\": \"Do you like this library?\", \"tag\": \"checkbox1\" }, { \"type\": \"checkboxGroup\", \"description\": \"Choose any number of these:\", \"options\": [ \"One\", \"Two\", \"Three\" ], \"tag\": \"checkBoxGroup1\" }, { \"type\": \"dropDownList\", \"description\": \"Choose one among these:\", \"options\": [ \"One\", \"Two\", \"Three\" ], \"tag\": \"dropDownList1\" }, { \"type\": \"dropDownList\", \"description\": \"Choose one among these:\", \"options\": [ \"four\", \"five\", \"six\" ], \"tag\": \"dropDownList2\" }, { \"type\": \"button\", \"name\": \"Sample Text View\", \"tag\": \"button1\" } ] }";
        try {
            FormBuilder formBuilder=new FormBuilder(this,((LinearLayout)findViewById(R.id.mainView)));
            formBuilder.createFromJson(viewJson);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
