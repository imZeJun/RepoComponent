package com.demo.lizejun.repocomponent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.lizejun.repocomponent.chapter1.LiveAppCompactActivity;
import com.demo.lizejun.repocomponent.chapter2.LiveActivity;
import com.demo.lizejun.repocomponent.chapter3.LiveDataActivity;
import com.demo.lizejun.repocomponent.chapter4.LiveDataExtendActivity;
import com.demo.lizejun.repocomponent.chapter5.ViewModelShareActivity;
import com.demo.lizejun.repocomponent.chapter6.PageActivity;

/**
 * Demo 入口。
 *
 * @author lizejun
 */
public class MainActivity extends AppCompatActivity {

    private static final int[] BTN_ID = new int[] {
            R.id.bt_chapter1,
            R.id.bt_chapter2,
            R.id.bt_chapter3,
            R.id.bt_chapter4,
            R.id.bt_chapter5,
            R.id.bt_chapter6};

    private static final Class[] TARGET = new Class[] {
            LiveAppCompactActivity.class,
            LiveActivity.class,
            LiveDataActivity.class,
            LiveDataExtendActivity.class,
            ViewModelShareActivity.class,
            PageActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int index = 0;
        for (int id : BTN_ID) {
            final int value = index;
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    startActivity(TARGET[value]);
                }
            });
            index++;
        }
    }

    private void startActivity(Class target) {
        Intent intent = new Intent(this, target);
        startActivity(intent);
    }
}
