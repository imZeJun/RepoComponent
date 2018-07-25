package com.demo.lizejun.repocomponent.chapter5;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.demo.lizejun.repocomponent.R;

public class SourceFragment extends Fragment {

    private Button mBtnChange;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_source, container, false);
        mBtnChange = root.findViewById(R.id.bt_change);
        return root;
    }
}
