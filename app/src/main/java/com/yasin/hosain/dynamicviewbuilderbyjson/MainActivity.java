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
        String viewJson="{\n" +
                "  \"views\": [\n" +
                "    {\n" +
                "      \"type\": \"imageView\",\n" +
                "      \"src\": \"https://i.imgur.com/DvpvklR.png\",\n" +
                "      \"tag\": \"imageView1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"textView\",\n" +
                "      \"text\": \"Sample Text View\",\n" +
                "      \"tag\": \"textView1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"editText\",\n" +
                "      \"description\": \"Edit Text\",\n" +
                "      \"inputType\": \"text\",\n" +
                "      \"tag\": \"editText1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"radioGroup\",\n" +
                "      \"description\": \"Choose one among these:\",\n" +
                "      \"options\": [\n" +
                "        \"One\",\n" +
                "        \"Two\",\n" +
                "        \"Three\"\n" +
                "      ],\n" +
                "      \"tag\": \"radioGroup1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"checkbox\",\n" +
                "      \"description\": \"Do you like this library?\",\n" +
                "      \"tag\": \"checkbox1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"dropDownList\",\n" +
                "      \"description\": \"Choose one among these:\",\n" +
                "      \"options\": [\n" +
                "        \"One\",\n" +
                "        \"Two\",\n" +
                "        \"Three\"\n" +
                "      ],\n" +
                "      \"tag\": \"dropDownList1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"dropDownList\",\n" +
                "      \"description\": \"Choose one among these:\",\n" +
                "      \"options\": [\n" +
                "        \"four\",\n" +
                "        \"five\",\n" +
                "        \"six\"\n" +
                "      ],\n" +
                "      \"tag\": \"dropDownList2\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"button\",\n" +
                "      \"name\": \"Submit\",\n" +
                "      \"tag\": \"button1\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";


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
