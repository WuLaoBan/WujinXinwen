package com.example.rain.huoyuandong1506d20170814;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rain.huoyuandong1506d20170814.xlistview.XListView;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * data 2017/8/14.
 * author:霍远东(Rain)
 * function:
 */
public class Fragment1 extends Fragment {
    private XListView xListView;
    private View view;
    private List<MyBean.ResultBean.DataBean> list;
    private String path;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment1, container, false);
        x.view().inject(getActivity());
        xListView = (XListView) view.findViewById(R.id.xlistview);
        RequestParams params = new RequestParams(path);
        x.http().get(params, new Callback.CacheCallback<String>() {

            @Override
            public void onSuccess(String result) {
                list = new ArrayList<MyBean.ResultBean.DataBean>();
                Gson gson = new Gson();
                MyBean myBean = gson.fromJson(result, MyBean.class);
                list = myBean.result.data;
                MyBaseAdapter myBaseAdapter = new MyBaseAdapter(getActivity(), list);
                xListView.setAdapter(myBaseAdapter);
                final List<String> list2 = new ArrayList<String>();
                for (int i = 5; i < 10; i++) {
                    list2.add(list.get(i).thumbnail_pic_s);
                }
                View view1 = View.inflate(getActivity(), R.layout.banner, null);
                Banner banner = (Banner) view1.findViewById(R.id.banner);
                banner.setImageLoader(new GlideImageloader());
                banner.setImages(list2);
                banner.start();
                xListView.addHeaderView(view1);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
        return view;
    }

    public void onRefresh() {

    }

    public void onLoadMore() {

    }

    public void setpath(String str) {
        this.path = str;
    }
}
