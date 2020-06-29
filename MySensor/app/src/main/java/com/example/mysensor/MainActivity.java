package com.example.mysensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView accelerationtext;
    TextView gravitytext;
    TextView temperaturetext;
    TextView pressuretext;
    TextView lighttext;

    SensorManager manager;

    public void initViews(){
        accelerationtext=findViewById(R.id.accelerationtext);
        gravitytext=findViewById(R.id.gravitytext);
        temperaturetext=findViewById(R.id.temperaturetext);
        pressuretext=findViewById(R.id.pressuretext);
        lighttext=findViewById(R.id.lighttext);
    }


    public void initManager(){
        manager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }


    public void getAcceleration(){
        Sensor AccelerationSensor=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(AccelerationSensor!=null){
            manager.registerListener(new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    float value[]=event.values;
                    float x=value[0];
                    float y=value[1];
                    float z=value[2];

                    accelerationtext.setText("X方向的加速度为："+x+"\nY方向的加速度为："+y+"\nZ方向的加速度为："+z);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            },AccelerationSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }


    public void getGravity(){
        Sensor GravitySensor=manager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        if(GravitySensor!=null){
            manager.registerListener(new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    float value[]=event.values;

                    float x=value[0];
                    float y=value[1];
                    float z=value[2];

                    double g=Math.pow((Math.pow(x,3)+Math.pow(y,3)+Math.pow(z,3)),1/3);

                    gravitytext.setText("重力加速度为："+g);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            },GravitySensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }


    public void getTemperature(){
        Sensor TemperatureSensor=manager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);

        if(TemperatureSensor!=null){
            manager.registerListener(new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    float value[]=event.values;
                    float t=value[0];

                    temperaturetext.setText("周围温度为："+t);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            },TemperatureSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }


    public void getPressure(){
        Sensor PressureSensor=manager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        if(PressureSensor!=null){
            manager.registerListener(new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    float value[]=event.values;
                    float p=value[0];

                    pressuretext.setText("周围大气压为："+p);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            },PressureSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }


    public void getLight(){
        Sensor LightSensor=manager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if(LightSensor!=null){
            manager.registerListener(new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    float value[]=event.values;
                    float l=value[0];

                    lighttext.setText("光照强度为："+l);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            },LightSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initManager();

        //获得5个传感器的参数
        getAcceleration();
        getGravity();
        getTemperature();
        getPressure();
        getLight();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}