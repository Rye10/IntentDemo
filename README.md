# IntentDemo
点击listview中的item跳转人物信息
===
点击Listview中的item跳转人物的信息一开始做法是，每点击一个item新建一个线程，在线程里边获取对应position的Json数据。</br>
写完发现有10个数据，新建了10个线程，仔细观察网络连接，获取Json数据代码都是一致的，取出数据不同的区别在于JSONArray中的index，只要改变index就可以取得
不同的Json数据，所以只需要一个线程，把点击item的position和JSONArray的index相关联就可以减少大量代码</br>
</br>
解决方法：在点击item的position的代码中传入需要的index，点击item，把index传递到线程中，线程去寻找对应的index，获取JSON数据</br>
activity间数据的传输，putExtra方法，key-value形式传递。在另外一个activity中通过getIntent（），getStringExtra或者getIntExtra方法获取。</br>
getStringExtra参数只需要key值，getIntExtra除了key值，还要defaultValue默认数值  </br>
</br>
</br>
记录三个ERROR</br>
1、在onCreate之前获取数据Intent intent=getInent（）会报空指针错误，因为还没有识别到你的activity,在对应的方法中获取  </br>
2、Unable to find explicit activity class    问题是在AndroidManifest.xml中没有定义新的activity的配置信息  </br>
3、java.lang.RuntimeException:Unable to startactivity ComponentInfo android.content.res.Resources$NotFoundException: String resource ID ###</br>
setText（）里边只能为string类型参数，如果为int类型就会报错</br>
解决方法  在int类型后边+""可以转换为string类型   </br>
例子中的ItemAdapter viewHolder.tvId.setText(list.get(position).id+"");  </br>
list.get(position).id为int型，+""转换为String类型</br>
转换int为String类型有三种方法</br>
1、tv.setText(num+"");  </br>
2、tv.setText(String.valueOf(num);</br>
3、tv.setText(Integer.toString(num));</br>
</br>
</br>
</br>
使用bundle传递信息</br>
1.fragment：</br>
Bundle bundle=new Bundle();</br>
bundle.putString("key","value");</br>
Fragment fragment=new Fragment();</br>
fragment.setArguments(bundle);</br>
</br>
Fragment中获取</br>
Bundle bundle=getArguments();</br>
String key=bundle.getString("key");</br>
</br>
2.intent:</br>
Intent intent=new Intent(MainActivity.this,SecondActivity.class);</br>
Bundle bundle=new Bundle();</br>
bundle.putString("name","Tom");</br>
bundle.putInt("age",21);</br>
intent.putExtras(bundle);</br>
startActivity(intent);</br>
</br>
SecondActivity中获取</br>
Intent intent=getIntent();</br>
Bundle bundle=intent.getExtras();</br>
String name=bundle.getString("name");</br>
int age=bundle.getInt("age");</br>
