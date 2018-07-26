IntentService-Review
> IntentService is a base class for {@link Service}s that handle asynchronous
  requests (expressed as {@link Intent}s) on demand.  Clients send requests
  through {@link android.content.Context#startService(Intent)} calls; the
  service is started as needed, handles each Intent in turn using a worker
  thread, and stops itself when it runs out of work.
#### 一 . IntentService概述
```java
  public abstract class IntentService extends Service {
        ...
  }
```  
* IntentService继承自Service，并且是一个抽象类
* 它可以用于在后台执行耗时的异步任务，当任务完成后会自动停止
* 它拥有较高的优先级，不易被系统杀死（继承自Service的缘故），因此比较适合执行一些高优先级的异步任务
* 它内部通过HandlerThread和Handler实现异步操作
* 创建IntentService时，只需实现onHandleIntent和构造方法，onHandleIntent为异步方法，可以执行耗时操作
#### 二 . IntentService的使用
在代码中使用for循环多次启动IntentService，然后去下载图片，(虽然我们多次启动了IntentService，但是IntentService的实例只有一个，这和传统的Service是一样的)；<br>

最终IntentService会去调用onHandleIntent执行异步任务，因为IntentService中真正执行异步任务的是HandlerThread+Handler，每次启动都会把下载图片的任务添加到依附的消息队列中，<br>
最后由HandlerThread+Handler去执行。<br>

代码片段：<br>
```java
class MainActivity{
    
    onCreate(...){
        ...
        
        Intent intent = new Intent(this, MyIntentService.class);
            for (int i = 0; i < urls.length; i++) {
                  //启动循环任务
                  intent.putExtra(MyIntentService.DOWNLOAD_URL, urls[i]);
                  intent.putExtra(MyIntentService.INDEX_FLAG, i);
                  startService(intent);
              }
              MyIntentService.setUpdateUI(this);
        
    }
    
}


class MyIntentService{
    
    ...
    
    /**
     * 实现异步任务的方法
     *
     * @param intent Activity传递过来的Intent，数据封装在intent中
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("-------", "Thread.currentThread().getName(): " + Thread.currentThread().getName());
    
        //在子线程中进行网络请求
        Bitmap bitmap = downloadUrlBitmap(intent.getStringExtra(DOWNLOAD_URL));
        Message msg1 = new Message();
        msg1.what = intent.getIntExtra(INDEX_FLAG, 0);
        msg1.obj = bitmap;
    
        //通知主线程去更新UI
        if (updateUI != null) {
            updateUI.updateUI(msg1);
        }
    
        Log.e("-------", "onHandleIntent");
    }
    
}

```

Log打印：
```text
07-26 10:27:14.834 30398-30398/com.liuh.intentservice_review E/-------: onCreate
07-26 10:27:14.835 30398-30398/com.liuh.intentservice_review E/-------: onStartCommand
07-26 10:27:14.835 30398-30398/com.liuh.intentservice_review E/-------: onStart
07-26 10:27:14.835 30398-30921/com.liuh.intentservice_review E/-------: Thread.currentThread().getName(): IntentService[MyIntentService]
07-26 10:27:14.836 30398-30398/com.liuh.intentservice_review E/-------: onStartCommand
07-26 10:27:14.836 30398-30398/com.liuh.intentservice_review E/-------: onStart
07-26 10:27:14.836 30398-30398/com.liuh.intentservice_review E/-------: onStartCommand
07-26 10:27:14.836 30398-30398/com.liuh.intentservice_review E/-------: onStart
07-26 10:27:14.837 30398-30398/com.liuh.intentservice_review E/-------: onStartCommand
07-26 10:27:14.837 30398-30398/com.liuh.intentservice_review E/-------: onStart
07-26 10:27:14.837 30398-30398/com.liuh.intentservice_review E/-------: onStartCommand
07-26 10:27:14.837 30398-30398/com.liuh.intentservice_review E/-------: onStart
07-26 10:27:14.838 30398-30398/com.liuh.intentservice_review E/-------: onStartCommand
07-26 10:27:14.838 30398-30398/com.liuh.intentservice_review E/-------: onStart
07-26 10:27:14.838 30398-30398/com.liuh.intentservice_review E/-------: onStartCommand
07-26 10:27:14.838 30398-30398/com.liuh.intentservice_review E/-------: onStart
07-26 10:27:15.302 30398-30921/com.liuh.intentservice_review E/-------: onHandleIntent
07-26 10:27:15.303 30398-30921/com.liuh.intentservice_review E/-------: Thread.currentThread().getName(): IntentService[MyIntentService]
07-26 10:27:15.613 30398-30921/com.liuh.intentservice_review E/-------: onHandleIntent
07-26 10:27:15.613 30398-30921/com.liuh.intentservice_review E/-------: Thread.currentThread().getName(): IntentService[MyIntentService]
07-26 10:27:15.819 30398-30921/com.liuh.intentservice_review E/-------: onHandleIntent
07-26 10:27:15.820 30398-30921/com.liuh.intentservice_review E/-------: Thread.currentThread().getName(): IntentService[MyIntentService]
07-26 10:27:16.035 30398-30921/com.liuh.intentservice_review E/-------: onHandleIntent
07-26 10:27:16.036 30398-30921/com.liuh.intentservice_review E/-------: Thread.currentThread().getName(): IntentService[MyIntentService]
07-26 10:27:16.181 30398-30921/com.liuh.intentservice_review E/-------: onHandleIntent
07-26 10:27:16.181 30398-30921/com.liuh.intentservice_review E/-------: Thread.currentThread().getName(): IntentService[MyIntentService]
07-26 10:27:16.448 30398-30921/com.liuh.intentservice_review E/-------: onHandleIntent
07-26 10:27:16.449 30398-30921/com.liuh.intentservice_review E/-------: Thread.currentThread().getName(): IntentService[MyIntentService]
07-26 10:27:16.666 30398-30921/com.liuh.intentservice_review E/-------: onHandleIntent
07-26 10:27:16.667 30398-30398/com.liuh.intentservice_review E/-------: onDestroy
```
通过打印的log可以看出，在MyIntentService中onHandleIntent方法的执行是有次序的。<br>

onCreate方法只启动了一次，而onStartCommand和onStart多次启动，这也证明了前面说的，虽然使用了for循环多次启动MyIntentService，但是它的实例只有一个；<br>

最后所有任务都执行完成后，MyIntentService自动销毁。<br>

#### 三 . IntentService源码分析
IntentService有一个内部成员ServiceHandler,它继承自Handler，它使用的是HandlerThread的Looper构建的消息循环系统。<br>

onHandleIntent方法是一个异步方法，如果后台任务只有一个的时候，onHandleIntent执行完，服务就会销毁；<br>
但是如果后台任务有多个的话，onHandleIntent执行完最后一个任务时，服务才会销毁。<br>

代码片段：
```java
public abstract class IntentService extends Service{
 private volatile ServiceHandler mServiceHandler;

 ...

 private final class ServiceHandler extends Handler {
     public ServiceHandler(Looper looper) {
         super(looper);
     }

     @Override
     public void handleMessage(Message msg) {
         onHandleIntent((Intent)msg.obj);
         stopSelf(msg.arg1);
     }
 }
 
 ...
 
 //在IntentService的onCreate方法中开启HandlerThread，然后使用它的Looper new出来ServiceHandler
 @Override
 public void onCreate() {
     // TODO: It would be nice to have an option to hold a partial wakelock
     // during processing, and to have a static startService(Context, Intent)
     // method that would launch the service & hand off a wakelock.
 
     super.onCreate();
     HandlerThread thread = new HandlerThread("IntentService[" + mName + "]");
     thread.start();
 
     mServiceLooper = thread.getLooper();
     mServiceHandler = new ServiceHandler(mServiceLooper);
 }
 
 //这里接收传递过来的Intent，然后发送到MessageQueue中，在mServiceHandler的onHandleIntent方法中进行处理
 @Override
 public void onStart(@Nullable Intent intent, int startId) {
     Message msg = mServiceHandler.obtainMessage();
     msg.arg1 = startId;
     msg.obj = intent;
     mServiceHandler.sendMessage(msg);
 }

    ...

@WorkerThread
protected abstract void onHandleIntent(@Nullable Intent intent);

}
```














  
  