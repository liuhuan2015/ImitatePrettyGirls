package com.liuh.lambda_learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.function.Function;

public class MainActivity extends AppCompatActivity {

    private Button btn1;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        //使用Lambda表达式
        btn1.setOnClickListener(view -> {
            //do something
            Toast.makeText(this, "我是按钮", Toast.LENGTH_SHORT).show();
        });

        mListView = findViewById(R.id.list);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        //使用Lambda表达式
        mListView.setOnItemClickListener(((parent, view, position, id) -> {
            //do something

        }));

    }

    Function<String, Integer> fun = s -> Integer.parseInt(s);

}
