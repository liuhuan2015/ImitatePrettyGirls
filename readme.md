>这个项目是用来学习[一个基于MVP+Retrofit+RxJava+MaterialDesign和gank.io的MeiZhi客户端](https://github.com/PleaseCallMeCoder/PrettyGirls)的。

#### 一 . 项目结构
这个项目只有四个界面：SplashActivity，HomeActivity，GirlActivity，AboutActivity。每一个界面都是使用的内嵌一个Fragment来实现的。<br>

使用的是mvp的基本架构，将model（数据）层和View（界面展示）层分离开来，通过Presenter（业务处理）层将其连系起来。

mvp结构例如：<br>
* splash<br>
        * SplashActivity<br>
        * SplashContract //契约类<br>
        * SplashFragment<br>
        * SplashPresenter<br>
        
贴上一个契约类的代码：<br>
```java
public interface SplashContract {

    interface View extends BaseView<Presenter> {
        void showGirl(String girlUrl);

        void showGirl();
    }

    interface Presenter extends BasePresenter {

    }

}
```
在网络请求上面使用的是Retrofit + Rxjava的这种方式：<br>

接口定义：<br>
```java
public interface GirlsService {

    @GET("api/data/{type}/{count}/{page}")
    Observable<GirlsBean> getGirls(@Path("type") String type, @Path("count") int count,
                                   @Path("page") int page);
}
```
使用Retrofit对每一个接口进行具体的实现 + 数据回调处理，没有使用Lambda:<br>

```java
public class RemoteGirlsDataSource implements GirlsDataSource {
    @Override
    public void getGirls(int page, int size, final LoadGirlsCallback callback) {
        GirlsRetrofit.getRetrofit()
                .create(GirlsService.class)
                .getGirls("福利", size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GirlsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataNotAvailable();
                    }

                    @Override
                    public void onNext(GirlsBean girlsBean) {
                        callback.onGirlsLoaded(girlsBean);
                    }
                });
    }

    @Override
    public void getGirl(LoadGirlsCallback callback) {
        getGirls(1, 1, callback);
    }
}
```
#### 二 . 扩展知识点
##### 2.1 HandlerThread
HandlerThread的特点：<br>
 * HandlerThread本质上是一个线程类，它继承自Thread；
 * HandlerThread有自己的内部Looper对象，可以进行looper循环；
 * 通过获取HandlerThread的looper对象传递给Handler对象，可以在handleMessage方法中执行异步任务；
 * 创建HandlerThread后必须先调用HandlerThread.start()方法，Thread会先调用run方法，创建Looper对象。
 * HandlerThread内部有一个Looper成员变量和Handler成员变量，它维持了一套消息循环系统；当然在使用的时候，我们可以只使用它的Looper来自己new出来一个Handler。
 ```java
public class HandlerThread extends Thread {
      ... 
}
```

具体见[HandlerThread-Review.md](https://github.com/liuhuan2015/ImitatePrettyGirls/blob/master/HandlerThread-Review.md)以及其module。<br>

##### 2.2 IntentService
IntentService的特点：<br>
* IntentService继承自Service，并且是一个抽象类
* 它可以用于在后台执行耗时的异步任务，当任务完成后会自动停止
* 它拥有较高的优先级，不易被系统杀死（继承自Service的缘故），因此比较适合执行一些高优先级的异步任务
* 它内部通过HandlerThread和Handler实现异步操作
* 创建IntentService时，只需实现onHandleIntent和构造方法，onHandleIntent为异步方法，可以执行耗时操作
```java
  public abstract class IntentService extends Service {
        ...
  }
```  
具体见[IntentService-Review.md](https://github.com/liuhuan2015/ImitatePrettyGirls/blob/master/IntentService-Review.md)以及其module。<br>

##### 2.3 Lambda表达式
λ表达式由三部分组成：参数列表，箭头（->），以及一个表达式或语句块。<br>

λ表达式可以被当做是一个Object（注意措辞）。λ表达式的类型，叫做“目标类型（target type）”。<br>

λ表达式的目标类型是“函数接口（functional interface）”，这是Java8新引入的概念。<br>

它的定义是：**一个接口，如果只有一个显式声明的抽象方法，那么它就是一个函数接口**。一般用@FunctionalInterface标注出来（也可以不标）。举例如下：
```java
   @FunctionalInterface
   public interface Runnable{void run();}
   
   public interface Callable<V>{ V call() throws Exception;}
   
   public interface ActionListener{ void actionPerformed(ActionEvent e);}
   
   public interface Comparator<T>{ int compare(T o1,T o2);boolean equals(Object obj);}
```
可以用一个λ表达式为一个函数接口赋值，例如：<br>
```java
   Runnable r1 = () -> {System.out.println("Hello Lambda!");};
   //然后再赋值给一个Object
   Object obj = r1;
```
详细见[lambda-learn.md](https://github.com/liuhuan2015/ImitatePrettyGirls/blob/master/lambda-learn/lambda-learn2.md)以及其module。









  
         





