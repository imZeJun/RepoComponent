package com.demo.lizejun.repocomponent.chapter1;

import android.arch.lifecycle.LifecycleObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.demo.lizejun.repocomponent.R;

/**
 * 使用 AppCompact 实现，它的父类 SupportActivity 实现了 LifecycleOwner 接口，
 * 在其内部持有了 LifecycleRegistry 对象。
 *
 * @author lizejun
 */
public class LiveAppCompactActivity extends AppCompatActivity {

    private Button mBtnBind;
    private Button mBtnUnBind;
    private LifecycleObserver mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_appcompact);
        mBtnBind = findViewById(R.id.bt_bind);
        mBtnBind.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mObserver == null) {
                    mObserver = new LiveObserver();
                    //getLifecycle() 是 SupportActivity 提供的方法。
                    getLifecycle().addObserver(mObserver);
                }
            }
        });
        mBtnUnBind = findViewById(R.id.bt_unbind);
        mBtnUnBind.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mObserver != null) {
                    getLifecycle().removeObserver(mObserver);
                    mObserver = null;
                }
            }
        });

    }
}
