package android.csulb.edu.arttherapy;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Sumeet on 3/14/2017.
 */

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private long lastUpdate;
    DrawActivity view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager =(SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        lastUpdate=System.currentTimeMillis();
        setContentView(R.layout.activity_main);
        view = (DrawActivity) findViewById(R.id.drawingView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            checkShake(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void checkShake(SensorEvent event){
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float accelationSquareRoot = (x*x+y*y+z*z)/(SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH);
        long actualTime = System.currentTimeMillis();
        if(accelationSquareRoot>=2){
            if((actualTime-lastUpdate)<200){
                return;
            }
            lastUpdate=actualTime;
            Toast.makeText(this,"shaked",Toast.LENGTH_SHORT).show();
            Intent serviceIntent = new Intent(this,SoundNotification.class);
            startService(serviceIntent);
            view.clearCanvas();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
