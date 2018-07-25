package com.demo.lizejun.repocomponent.chapter4;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.demo.lizejun.repocomponent.R;

public class LiveDataExtendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_extend);
        NetLiveData.getInstance(this).observe(this, new Observer<Boolean>() {

            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                Toast.makeText(LiveDataExtendActivity.this, (aBoolean ? "网络连接" : "网络未连接"), Toast.LENGTH_LONG).show();
            }

        });
    }
}
