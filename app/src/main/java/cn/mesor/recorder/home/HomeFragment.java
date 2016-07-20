package cn.mesor.recorder.home;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.mesor.recorder.R;
import cn.mesor.recorder.utils.Constants;

/**
 * Created by Limeng on 2016/7/20.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.contentList)
    RecyclerView contentList;

    SwipeRefreshLayout rootView;

    ListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int permission = PermissionChecker.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission != PermissionChecker.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
        }
        View rootView = inflater.inflate(R.layout.fragment_home, null);
        this.rootView = (SwipeRefreshLayout) rootView;
        ButterKnife.bind(rootView);
        contentList = (RecyclerView) rootView.findViewById(R.id.contentList);
        contentList.setItemAnimator(new DefaultItemAnimator());
        initData();
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                int permission = PermissionChecker.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permission == PermissionChecker.PERMISSION_GRANTED && adapter != null)
                    adapter.notifyDataSetChanged();
                break;

        }
    }

    int count = 0;

    private void initData() {
        contentList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false));
        List<String> list = new ArrayList<>();

        while (count++ < 5) {
            list.add(Constants.pic_url);
        }
        adapter = new ListAdapter(this);
        contentList.setAdapter(adapter);
        adapter.setContent(list);
        rootView.setOnRefreshListener(this);
        rootView.setProgressBackgroundColorSchemeResource(android.R.color.white);
        rootView.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                for (int index = 0; index < count * 2; index++) {
                    list.add(Constants.pic_url);
                }
                count *= 2;
                adapter.setContent(list);
                rootView.setRefreshing(false);
            }
        }, 2000);

    }
}
