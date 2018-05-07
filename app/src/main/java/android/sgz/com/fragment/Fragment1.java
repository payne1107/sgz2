package android.sgz.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.activity.SearchActivity;
import android.sgz.com.activity.WorkDayNumActivity;
import android.sgz.com.adapter.FirstFragmentAdapter;
import android.sgz.com.base.BaseFragment;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * Created by 92457 on 2018/4/16.
 */

public class Fragment1 extends BaseFragment implements View.OnClickListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private EditText etSearch;
    private AutoRelativeLayout tvTitle;
    private AutoLinearLayout layoutWorkDay;


    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment1, null);
        }
        //缓存的rootView需要判断是否已经被加过parent,如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvTitle = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_title);
        etSearch = (EditText) mRootView.findViewById(R.id.et_search);
        layoutWorkDay = (AutoLinearLayout) mRootView.findViewById(R.id.layout_work_day);
        viewPager = (ViewPager) mRootView.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) mRootView.findViewById(R.id.tabLayout);
        FirstFragmentAdapter adapter = new FirstFragmentAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setListener();
    }

    /****
     * 设置监听器
     */
    private void setListener() {
        layoutWorkDay.setOnClickListener(this);
        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    startActivity(new Intent(getActivity(), SearchActivity.class));
                    etSearch.clearFocus();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_work_day:
                startActivity(new Intent(getActivity(), WorkDayNumActivity.class));
                break;
        }
    }
}
