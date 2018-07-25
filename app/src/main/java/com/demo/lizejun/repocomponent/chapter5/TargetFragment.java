package com.demo.lizejun.repocomponent.chapter5;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.lizejun.repocomponent.R;

public class TargetFragment extends Fragment {

    private TextView mTvResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_target, container, false);
        mTvResult = root.findViewById(R.id.tv_result);
        return root;
    }
}
