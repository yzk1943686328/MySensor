Android传感器设计文档
传感器是一种物理装置或生物器官，能够探测、感受外界的信号、物理条件（如光、热、湿度）或化学组成（如烟雾），并将探知的信息传递给其他装置或器官。Android中传感器可以展示当前手机状态的应用，包括硬件信息、当前位置、加速计、陀螺仪、光感、磁场、定向、电池窗台，声压，同时还可以进行多点触控的测试。只要你的想象力足够丰富，完全可以利用这些信息做出一些很新奇得应用。

这里我们只介绍传感器的基础内容，至于怎样用传感器做出新奇的应用就要靠自己的脑洞了，本应用涉及到5个传感器，分别是加速度传感器，。

首先我们写一下前端界面，这里我写的前端界面比较简洁，因为实在是没有必要搞得花里胡哨，功能完成了就挺好，我的前端页面只有五个TextView，分别显示5个传感器的检测数据。

然后，我们来写后端逻辑，首先，我们当然要初始化五个TextView变量，然后，我们就开始传感器的编写了。
使用传感器首先要写一个SensorManager，负责传感器的管理及注册，就像这样:

manager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
然后，我们就开始逐个声明各个传感器，建立传感器的步骤是一样的，首先我们需要new一个传感器，比如，new一个加速度传感器：
Sensor AccelerationSensor=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
参数代表的就是加速度传感器，关于各传感器的介绍可以参考这一篇简书：https://www.jianshu.com/p/8440a5897944
，里面讲的非常清楚。
声明了传感器之后，我们还需要注册该传感器，注册传感器调用的是SensorManager的registerListener方法 ：
manager.registerListener(new SensorEventListener(){},AccelerationSensor,
SensorManager.SENSOR_DELAY_NORMAL);
registerListener方法有三个参数，第一个参数是SensorEventListener接口的一个实现类对象，该接口有两个方法，onSensorChanged和onAccuracyChanged，我们在onSensorChanged方法中可以调用event.value得到检测结果数组，有的结果数组中有三个值，分别代表三个方向的检测数据，有的只有一个值代表检测数据，就像这样：
public void onSensorChanged(SensorEvent event) {
    float value[]=event.values;
    float x=value[0];
    float y=value[1];
    float z=value[2];
    accelerationtext.setText("X方向的加速度为："+x+"\nY方向的加速度为："+y+"\nZ方向的加速度为："+z);
}


这是加速度传感器中的代码，这里我们分别显示三个方向的加速度值。

其他传感器中的代码就不一一展示了，其中，重力传感器得到的结果数组有三个值，分别代表三个方向的重力加速度值，我们这里利用Math.pow方法将这三个值合并为一个值，得到总的重力加速度然后显示在TextView中，温度传感器，压力传感器，和亮度传感器的结果数组只有一个值，直接显示在对应的TextView中。
registerListener的第二个参数代表需要注册的传感器。

registerListener的第三个参数代表检测的速度，有四个值可以选，分别是SENSOR_DELAY_UI，SENSOR_DELAY_NORMAL，SENSOR_DELAY_GAME，SENSOR_DELAY_FASTEST，速度是递增的。
这样分别建立并注册5个传感器就可以了。效果图如下所示（我用的是小米8 ，小米8没有温度传感器，所以检测不到温度值）：