package com.liuh.lambda_learn;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;

public class MainActivity extends AppCompatActivity {

    private Button btn1;

    private ListView mListView;

    int x, y;

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

        getRunnable().run();

        sum();

        getVoid();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is from an anonymous class.");
            }
        });

        Thread thread1 = new Thread(() -> System.out.println("this is from an anonymous method (lambda exp)."));

    }

    private void getVoid() {
//        (int x, int y) -> System.out.println("sum: " + (x + y));
    }

    @NonNull
    private IntBinaryOperator sum() {
        return (int x, int y) -> {
            return x + y;
        };
    }

    @NonNull
    private Runnable getRunnable() {
        return () -> System.out.println("Hello Lambda !");
    }

    Function<String, Integer> fun = s -> Integer.parseInt(s);

}
