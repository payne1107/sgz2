package android.sgz.com.fragment;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.MineWaringWorkRecordkFragmentAdapter;
import android.sgz.com.adapter.MineWorkOrderFragmentAdapter;
import android.sgz.com.base.BaseFragment;
import android.sgz.com.bean.WaringApplyListBean;
import android.sgz.com.utils.ConfigUtil;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/6/27.
 * 我的补卡卡列标
 */

public class MineWaringWorkRecordkFragment extends BaseFragment {
    private PullToRefreshListView listView;
    private int pageSize;
    private int pageNo = 1;
    private boolean swipeLoadMore =false;
    private List<WaringApplyListBean.DataBean.ListBean> mList = new ArrayList<>();
    private MineWaringWorkRecordkFragmentAdapter adapter;
    private int type = 1;


    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_mine_waring_work_record, null);
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
        queryMineWaringWorkRecordList(pageNo,type);
        listView = (PullToRefreshListView) mRootView.findViewById(R.id.listView);
        // 设置模式BOTH: 既能上拉也能下拉，
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new MineWaringWorkRecordkFragmentAdapter(getActivity(),mList);
        listView.setAdapter(adapter);

        setListener();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryMineWaringWorkRecordList(pageNo,type);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                if (pageNo <= pageSize) {
                    swipeLoadMore = true;
                    queryMineWaringWorkRecordList(pageNo,type);
                } else {
                    delayedToast();
                }
            }
        });
    }

    private void delayedToast() {
        Toast.makeText(getActivity(),"没有更多数据啦",Toast.LENGTH_LONG).show();
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.onRefreshComplete();
                swipeLoadMore = false;
            }
        },1000);
    }
    /**
     *  查询我的补卡列表
     * @param pageNo
     * @param type  = 1 补卡申请列表 = 2 需要审批列表
     */
    private void queryMineWaringWorkRecordList(int pageNo,int type) {
        startIOSDialogLoading(getActivity(),"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(pageNo));
        params.put("type", String.valueOf(type));
        httpPostRequest(ConfigUtil.QUERY_MAKE_CARD_APPLY_LIST_URL, params, ConfigUtil.QUERY_MAKE_CARD_APPLY_LIST_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_MAKE_CARD_APPLY_LIST_URL_ACTION:
                handleQuerMakeCardApplyList(json);
                break;
        }
    }

    /****
     * 处理我的申请补卡列表
     * @param json
     */
    private void handleQuerMakeCardApplyList(String json) {
        Log.d("Dong", "json ---------------->>>>" + json);
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        WaringApplyListBean bean = JSON.parseObject(json, WaringApplyListBean.class);
        if (bean != null) {
            WaringApplyListBean.DataBean data = bean.getData();
            if (data != null) {
                pageSize = data.getCoutpage();
                if (swipeLoadMore) {
                    swipeLoadMore = false;
                    mList.addAll(mList.size(), data.getList());
                    adapter.setData(mList);
                } else {
                    mList = data.getList();
                    if (mList != null && mList.size() > 0) {
                        adapter.setData(mList);
                        listView.setVisibility(View.VISIBLE);
                    } else {
                        setEmptyView(listView);
                    }
                }
            }
        }
    }
}
