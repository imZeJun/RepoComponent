package com.demo.lizejun.repocomponent.chapter6;

import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.arch.paging.TiledDataSource;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.lizejun.repocomponent.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author lizejun
 **/
public class PageActivity extends AppCompatActivity {

    private PagedList<String> mPageList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_list);
        create();
    }

    private void create() {
        final PagedList.Config mPagedListConfig = new PagedList.Config.Builder()
                .setPageSize(10)
                .setPrefetchDistance(5)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(20)
                .build();

        mPageList = new PagedList.Builder<Integer, String>()
                .setConfig(mPagedListConfig)
                .setDataSource(new PageDataSource())
                .setMainThreadExecutor(new MainExecutor())
                .setBackgroundThreadExecutor(new BackExecutor())
                .build();

        RecyclerView rv = findViewById(R.id.rv_content);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastPos = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                mPageList.loadAround(lastPos);
            }

        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        PageAdapter pageAdapter = new PageAdapter(new PageDataDiffCallback(), mPageList);
        pageAdapter.setList(mPageList);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(pageAdapter);
    }

    private static class MainExecutor implements Executor {

        private Handler mHandler;

        MainExecutor() {
            mHandler = new Handler(Looper.getMainLooper());
        }

        @Override
        public void execute(@NonNull Runnable command) {
            mHandler.post(command);
        }
    }

    private static class BackExecutor implements Executor {

        private Handler mHandler;

        BackExecutor() {
            HandlerThread handlerThread = new HandlerThread("backExecutor");
            handlerThread.start();
            mHandler = new Handler(handlerThread.getLooper());
        }

        @Override
        public void execute(@NonNull Runnable command) {
            mHandler.post(command);
        }
    }


    private class PageDataSource extends TiledDataSource<String> {

        /**
         * 总共可以提供的数量。
         */
        @Override
        public int countItems() {
            return 100;
        }

        /**
         * 加载范围内的数据。
         */
        @Override
        public List<String> loadRange(int startPosition, int count) {
            return loadRangeData(startPosition, count);
        }
    }

    private List<String> loadRangeData(int startPosition, int count) {
        List<String> list = new ArrayList<>();
        for (int i = startPosition; i < count; i++) {
            list.add("data position= " + i);
        }
        return list;
    }

    private class PageDataDiffCallback extends DiffCallback<String> {

        /**
         * 用于判断两个 Item 是否相同。
         */
        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return TextUtils.equals(oldItem, newItem);
        }

        /**
         * 用于判断同一个 Item 的内容是否发生了变化。
         */
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return TextUtils.equals(oldItem, newItem);
        }
    }

    private class PageAdapter extends PagedListAdapter<String, PageAdapter.PageViewHolder> {

        private PagedList<String> mPageList;

        PageAdapter(PageDataDiffCallback callback, PagedList<String> pagedList) {
            super(callback);
            mPageList = pagedList;
        }

        @Override
        public PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_page_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(PageViewHolder holder, int position) {
            String data = mPageList.get(position);
            holder.mTextView.setText(data);
        }

        class PageViewHolder extends RecyclerView.ViewHolder {

            private TextView mTextView;

            PageViewHolder(View itemView) {
                super(itemView);
                mTextView = itemView.findViewById(R.id.tv_title);
            }

        }
    }
}
