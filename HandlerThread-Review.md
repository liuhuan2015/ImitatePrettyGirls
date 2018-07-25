HandlerThread-Review.
>Handy class for starting a new thread that has a looper. The looper can then be used to create handler classes. Note that start() must still be called.<br>
HandlerThread类的源码行数不多，160行左右。
#### 一 . HandlerThread的特点
 * HandlerThread本质上是一个线程类，它继承自Thread；
 * HandlerThread有自己的内部Looper对象，可以进行looper循环；
 * 通过获取HandlerThread的looper对象传递给Handler对象，可以在handleMessage方法中执行异步任务；
 * 创建HandlerThread后必须先调用HandlerThread.start()方法，Thread会先调用run方法，创建Looper对象。
 * HandlerThread内部有一个Looper成员变量和Handler成员变量，它维持了一套消息循环系统；当然在使用的时候，我们可以只使用它的Looper来自己new出来一个Handler。
 
#### 二 . HandlerThread的使用

 我们在主线程中new出来的Handler，是使用的主线程的Looper构建的消息循环系统；<br>
 
 借助HandlerThread，因为其内部有一个Looper，我们可以使用它的Looper来构建一个消息循环系统，以减轻主线程的压力。<br>
  
 开发中如果多次使用类似new Thread(){...}.start()这种方式开启一个子线程，会创建多个匿名线程，使得程序运行起来越来越慢，<br>
 而HandlerThread自带Looper使它可以通过消息机制来多次重复使用当前线程，节省系统开销；<br>
 
 Android系统提供的Handler类内部的Looper默认绑定的是UI线程的消息队列，对于非UI线程又想使用消息机制的情况，HandlerThread是最合适的，<br>
 它不会干扰或阻塞UI线程。<br>
 
 比如说有需求：每隔五秒请求一下服务器，看数据是否有更新。详细使用见工程<br>
 
 代码片段：<br>
 ```java
 
 private HandlerThread mCheckMsgThread;

     ...
     
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         ...
         initBackThread();
       }
     
     private void initBackThread() {
         mCheckMsgThread = new HandlerThread("check-message-coming");
         mCheckMsgThread.start();
 
         //使用HandlerThread的Looper构建一个消息循环系统
         mCheckMsgHandler = new Handler(mCheckMsgThread.getLooper()) {
 
             @Override
             public void handleMessage(Message msg) {
                 super.handleMessage(msg);
 
                 checkForUpdate();
 
                 if (isUpdateInfo) {
                     //隔一秒发送一个空消息，促使这个消息循环系统一直循环下去，只要控制值 isUpdateInfo 不为 false .
                     mCheckMsgHandler.sendEmptyMessageDelayed(MSG_UPDATE_INFO, 5000);
                 }
 
             }
         };
     }
     
     ...
     @Override
     protected void onDestroy() {
          super.onDestroy();
          //释放资源
          mCheckMsgThread.quit();
       }
 ```
 
 
 
 
 
