package com.liuh.intentservice_review;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements MyIntentService.UpdateUI {

    private String[] urls = {
            "https://goss.veer.com/creative/vcg/veer/800water/veer-141978971.jpg",
            "https://goss.veer.com/creative/vcg/veer/800water/veer-147164321.jpg",
            "https://goss.veer.com/creative/vcg/veer/800water/veer-317728649.jpg",
            "https://goss.veer.com/creative/vcg/veer/800water/veer-318452774.jpg",
            "https://goss.veer.com/creative/vcg/veer/800water/veer-154673951.jpg",
            "https://goss.veer.com/creative/vcg/veer/800water/veer-149449636.jpg",
            "https://goss.veer.com/creative/vcg/veer/800water/veer-168982398.jpg"
    };

    private static ImageView imageView;

    private static final Handler mUIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            imageView.setImageBitmap((Bitmap) msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);

        Intent intent = new Intent(this, MyIntentService.class);
        for (int i = 0; i < urls.length; i++) {
            //启动循环任务
            intent.putExtra(MyIntentService.DOWNLOAD_URL, urls[i]);
            intent.putExtra(MyIntentService.INDEX_FLAG, i);
            startService(intent);
        }
        MyIntentService.setUpdateUI(this);
    }

    //必须通过Handler去更新，该方法为异步方法，不可更新UI
    @Override
    public void updateUI(Message message) {
        mUIHandler.sendMessageDelayed(message, message.what * 1000);
    }
}
