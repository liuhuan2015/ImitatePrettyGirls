package com.liuh.lambda_learn;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.stream.Stream;

/**
 * Lambda表达式的有些语法只有在Android 7.0( API 24 )及以上版本才可以使用
 */
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
//        mListView.setOnItemClickListener(((parent, view, position, id) -> {
//            do something
//
//        }));

//        getRunnable().run();

//        sum();

//        getVoid();

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("This is from an anonymous class.");
//            }
//        });
//
//        Thread thread1 = new Thread(() -> System.out.println("this is from an anonymous method (lambda exp)."));

        //嵌套的Lambda表达式
//        Callable<Runnable> c1 = () -> () -> System.out.println("Nested Lambda");
//        c1.call().run();

        //用在条件表达式中
//        Callable<Integer> c2 = true ? (() -> 42) : (() -> 24);
//        System.out.println(c2.call());

        //定义一个递归函数，注意需用this限定
//        UnaryOperator<Integer> factorial = i -> i == 0 ? 1 : i * this.factorial.apply(i - 1);
//        System.out.println(factorial.apply(3));

        //生成并打印5个随机数
        Stream.generate(Math::random).limit(5).forEach(System.out::println);

    }

    private void getVoid() {
//        (int x, int y) -> System.out.println("sum: " + (x + y));
    }

//    @NonNull
//    private IntBinaryOperator sum() {
//        return (int x, int y) -> {
//            return x + y;
//        };
//    }

//    @NonNull
//    private Runnable getRunnable() {
//        return () -> System.out.println("Hello Lambda !");
//    }

//    Function<String, Integer> fun = s -> Integer.parseInt(s);

}
