package com.demo.lizejun.repocomponent.chapter3;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.lizejun.repocomponent.R;

import java.util.List;

/**
 * LiveData 学习 Demo。
 */
public class LiveDataActivity extends AppCompatActivity {

    private Button mBtnRefresh;
    private TextView mTvResult;
    private DataViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        mTvResult = findViewById(R.id.tv_result);
        //1.创建 ViewModel。
        mViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        Log.d("DataViewModel", "createViewModel, model_address=" + mViewModel);
        //2.添加观察者。
        mViewModel.getWatcher().observeForever(new Observer<List<String>>() {

            @Override
            public void onChanged(@Nullable List<String> strings) {
                Log.d("DataViewModel", "onChanged");
                String tvDisplay = "";
                for (String result : strings) {
                    tvDisplay += (result + "\n");
                }
                //4.数据发生了改变后会回调到这里。
                mTvResult.setText(tvDisplay);
            }
        });
        mBtnRefresh = findViewById(R.id.btn_refresh);
        mBtnRefresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //3.触发加载。
                Log.d("DataViewModel", "mViewModel.load()");
                mViewModel.load();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("DataViewModel", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("DataViewModel", "onPause()");
    }
}
