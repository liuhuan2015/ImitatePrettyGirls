Android Lambda学习
>学习目标文章[android lambda的使用总结及运行原理](https://blog.csdn.net/smileiam/article/details/73921066)
#### 前言
为了支持函数式编程，Java 8引入了Lambda表达式，Android N已经开始支持Java 8了。<br>

使用Lambda可以大大减少代码的编写，只关注最重要的部分。虽然使代码的可读性变差，但是用习惯了就会喜欢上Lambda表达式，<br>
它使代码变得比较干净整洁。<br>

**1. Lambda表达式是使用的内部类来实现的吗？**

使用匿名内部类的话，编译器会为每一个匿名内部类创建一个类文件，而类在使用前需要加载类文件并进行验证，这个过程会影响应用的启动性能。<br>

如果Lambda是使用的匿名内部类实现的，会使应用内存占用增加，同时也会使Lambda表达式与匿名内部类的字节码生成机制绑定。所以Lambda表达式不是使用的匿名内部类来实现的。<br>

Lambda使用了Java中的动态指令。<br>

**2. Lambda表达式是怎么运行的？**

Lambda表达式将翻译策略推迟到运行时，主要是将表达式转成字节码invoked dynamic指令，主要有以下两步：<br>

1)生成一个invoked dynamic调用点（dynamic工厂），当lambda表达式被调用时，会返回一个lambda表达式转化成的函数式接口实例；<br>

2)将lambda表达式的方法体转换成方法供invoked dynamic指令调用，对于大多数情况下，lambda表达式要比匿名内部类性能更优。

**3. Lambda表达式是怎么翻译成机器识别的代码？**

对于Lambda表达式翻译成实际运行代码，分为对变量捕获和不对变量捕获方法，即是否需要访问外部变量。<br>

对于下面的表达式：<br>
```java
   Function<String, Integer> fun = s -> Integer.parseInt(s);
```
1)对于不进行变量捕获的lambda表达式，其方法实现会被提取到一个与之具有相同签名的静态方法中，这个静态方法和lambda表达式位于同一个类上。<br>

上面的表达式会变为：<br>
```java
   static Integer lambda$1(String s){
       return Integer.parseInt(s);
   }
```
2)对于捕获变量的lambda表达式，lambda表达式依然会被提取到一个静态方法中，被捕获的变量会同正常的参数一样传入到这个方法中。<br>
```java
   static Integer lambda$1(int offset , String s){
    return Integer.parseInt(s) + offset;
   }
```
**4. Lambda表达式相对于匿名内部类来说有什么优点？**

1)连接方面，上面提到的lambda工厂，这一步相当于匿名内部类的类加载过程，虽然预加热会消耗时间，但随着调用点连接的增加，<br>
代码被频繁调用后，性能会提升，另一方面，如果连接不频繁，lambda工厂方法也会比匿名内部类加载快，最高可达100倍；<br>
 
2)如果lambda不用捕获变量，会自动进行优化，避免基于lambda工厂实现下额外创建对象，而匿名内部类，这一步对应的是创建外部类的实例，需要更多的内存；<br>

**5. Lambda表达式是Java所特有的吗？**

Lambda表达式并非Java 8 所特有，scala曾经通过匿名内部类的形式支持Lambda表达式。<br>

**6. 在Android Studio中使用Lambda表达式**
>在AS 3.0里面只进行1和4就可以了，2和3不用，如果添加了，会有以下warn：<br>
```java
Warning:One of the plugins you are using supports Java 8 language features. To try the support built into the Android plugin, remove the following from your build.gradle:
    apply plugin: 'me.tatarka.retrolambda'
To learn more, go to https://d.android.com/r/tools/java-8-support-message.html
```
1) Download jdk8 and set it as your default.

2) 在工程的build.gradle的buildscript下的dependencies下加入依赖：<br>
```java
   classpath 'me.tatarka:gradle-retrolambda:3.3.1'
```
3) 在app的build.gradle中最头上引入lambda包<br>
```java
   apply plugin: 'me.tatarka.retrolambda'
```
4) 在android闭包标签下添加：<br>
```java
compileOptions {
        sourceCompatibility 1.8
        targetCompatibility 1.8
    }
```
这样就可以在程序中直接使用Lambda快速进行开发了。<br>

**7. Lambda的一些常用替换写法**
1) ListView的setOnItemClickListener的替换写法：
```java
  //使用Lambda表达式
        mListView.setOnItemClickListener(((parent, view, position, id) -> {
            //do something

        }));
```
2) View的setOnClickListener的替换写法：
```java
   //使用Lambda表达式
        btn1.setOnClickListener(view -> {
            //do something
            Toast.makeText(this, "我是按钮", Toast.LENGTH_SHORT).show();
        });
```
3) 遇到Rxjava的Action或者Fun函数时，结合Lambda，可以使代码更加简洁。(Rxjava不熟，这里就不贴代码了)
























