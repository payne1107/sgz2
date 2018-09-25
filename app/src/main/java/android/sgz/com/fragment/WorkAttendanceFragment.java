package android.sgz.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.activity.ApplyMakeRecordActivity;
import android.sgz.com.base.BaseFragment;
import android.sgz.com.bean.DefaultProjectOrderBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.DateUtils;
import android.sgz.com.utils.StringUtils;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.autolayout.AutoLinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/4/29.
 */

public class WorkAttendanceFragment extends BaseFragment implements View.OnClickListener {
    private View mRootView;
    private TextView tvProjectName;
    private TextView tvDate;
    private TextView tvStartWorkTime;
    private TextView tvEndWorkTime;
    private TextView tvStartWorkRecord;
    private TextView tvStartWorkStatus;
    private TextView tvStartWorkAddress;
    private TextView tvEndRecordAddress;
    private TextView tvEndRecordTime;
    private TextView tvEndWorkStatus;
    private int projectId;
    private AutoLinearLayout layoutNoneOrder;
    private ScrollView layouotHaveOrder;
    private TextView tvCurrentDesc;
    private String strDate;

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_work_attendance, null);
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
        EventBus.getDefault().register(WorkAttendanceFragment.this);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        queryDefaultOrder();
    }

    private void initView() {
        tvProjectName = mRootView.findViewById(R.id.tv_project_name);
        tvDate = mRootView.findViewById(R.id.tv_date);
        tvStartWorkTime = mRootView.findViewById(R.id.tv_start_work_time);
        tvEndWorkTime = mRootView.findViewById(R.id.tv_end_work_time);
        tvStartWorkRecord = mRootView.findViewById(R.id.tv_start_work_record);
        tvStartWorkStatus = mRootView.findViewById(R.id.tv_start_work_status);
        tvStartWorkAddress = mRootView.findViewById(R.id.tv_start_work_address);
        tvEndRecordAddress = mRootView.findViewById(R.id.tv_end_record_address);
        tvEndRecordTime = mRootView.findViewById(R.id.tv_end_record_time);
        tvEndWorkStatus = mRootView.findViewById(R.id.tv_end_work_status);
        layoutNoneOrder = mRootView.findViewById(R.id.layout_none_order);//没有工单显示页面
        layouotHaveOrder = mRootView.findViewById(R.id.layout_have_order);
        tvCurrentDesc = mRootView.findViewById(R.id.tv_current_des);

        setLisener();
    }

    private void setLisener() {
        tvStartWorkStatus.setOnClickListener(this);
        tvEndWorkStatus.setOnClickListener(this);
    }

    /***
     * 获取默认工单
     */
    public void queryDefaultOrder() {
        startIOSDialogLoading(getActivity(), "加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("randow", "123");
        httpPostRequest(ConfigUtil.QUERY_DEFAULT_PROJECT_URL, params, ConfigUtil.QUERY_DEFAULT_PROJECT_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        handleQueryDefaultOrder(json);
    }

    /****
     * 获取默认工单
     * @param json
     */
    private void handleQueryDefaultOrder(String json) {
        Log.d("Dong", "默认工单---->" + json);
        DefaultProjectOrderBean bean = JSON.parseObject(json, DefaultProjectOrderBean.class);
        if (bean != null) {
            DefaultProjectOrderBean.DataBean data = bean.getData();
            if (data != null) {
                DefaultProjectOrderBean.DataBean.ProjectBean projectBean = data.getProject();
                strDate = data.getDate();
                if (projectBean != null) {
                    int ifend = projectBean.getIfend();
                    if (ifend == 1) {
                        //工单已经结束
                        tvCurrentDesc.setText("该工单已经结束");
                        layoutNoneOrder.setVisibility(View.VISIBLE);
                        layouotHaveOrder.setVisibility(View.GONE);
                    } else {
                        if (projectBean != null) {
                            String projectName = projectBean.getName();
                            String startTime = projectBean.getStarttime();
                            String startWorkTime = projectBean.getStartworktime();
                            String endWorkTime = projectBean.getEndworktime();
                            projectId = projectBean.getId();
                            tvProjectName.setText("" + projectName);
                            tvDate.setText("" + startTime);
                            tvStartWorkTime.setText("上班时间 " + startWorkTime);
                            tvEndWorkTime.setText("下班时间" + endWorkTime);
                        }
                        DefaultProjectOrderBean.DataBean.WorkRecordBean workRecordBean = data.getWorkRecord();
                        if (workRecordBean != null) {
                            String startRecordAddress = workRecordBean.getStartrecordaddress();
                            String endRecordAddress = workRecordBean.getEndrecordaddress();
                            String startRecordTime = workRecordBean.getStartrecordtime();
                            String endRecordTime = workRecordBean.getEndrecordtime();
                            int startSatus = workRecordBean.getStartstatus();
                            int endStatus = workRecordBean.getEndstatus();

                            tvStartWorkRecord.setText(StringUtils.isEmpty(startRecordTime) ? "打卡时间" : "打卡时间" + startRecordTime);
                            tvStartWorkAddress.setText(StringUtils.isEmpty(startRecordAddress) ? "" : "" + startRecordAddress);
                            tvEndRecordAddress.setText(StringUtils.isEmpty(endRecordAddress) ? "" : "" + endRecordAddress);
                            tvEndRecordTime.setText(StringUtils.isEmpty(endRecordTime) ? "下班时间" : "下班时间" + endRecordTime);
                            if (isAdded()) {
                                if (startSatus == 1) {
                                    tvStartWorkStatus.setText("正常");
                                    tvStartWorkStatus.setTextColor(getResources().getColor(R.color.color_62d));
                                } else if (startSatus == 2) {
                                    tvStartWorkStatus.setText("迟到");
                                    tvStartWorkStatus.setTextColor(getResources().getColor(R.color.google_red));
                                } else if (startSatus == 3) {
                                    tvStartWorkStatus.setText("早退");
                                    tvStartWorkStatus.setTextColor(getResources().getColor(R.color.google_red));
                                } else {
                                    tvStartWorkStatus.setText("未打卡");
                                    tvStartWorkStatus.setTextColor(getResources().getColor(R.color.google_red));
                                }
                                if (endStatus == 1) {
                                    tvEndWorkStatus.setText("正常");
                                    tvEndWorkStatus.setTextColor(getResources().getColor(R.color.color_62d));
                                } else if (endStatus == 2) {
                                    tvEndWorkStatus.setText("迟到");
                                    tvEndWorkStatus.setTextColor(getResources().getColor(R.color.google_red));
                                } else if (endStatus == 3) {
                                    tvEndWorkStatus.setText("早退");
                                    tvEndWorkStatus.setTextColor(getResources().getColor(R.color.google_red));
                                } else {
                                    tvEndWorkStatus.setText("未打卡");
                                    tvEndWorkStatus.setTextColor(getResources().getColor(R.color.google_red));
                                }
                            }
                        }
                    }
                }
            } else {
                layoutNoneOrder.setVisibility(View.VISIBLE);
                layouotHaveOrder.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(WorkAttendanceFragment.this);
    }

    /**
     * 4.事件订阅者处理事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(Integer messageEvent) {
        Log.d("Dong", "事件类型 ---》" + messageEvent);
        queryDefaultOrder();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), ApplyMakeRecordActivity.class);
        String currentYearMoth = DateUtils.getYear() + "-" + (DateUtils.getMonth() < 10 ? "0" + DateUtils.getMonth() : DateUtils.getMonth());
        switch (view.getId()) {
            case R.id.tv_start_work_status:
                //申请补上班卡
                String startStatus = tvStartWorkStatus.getText().toString().trim();
                if (startStatus.equals("未打卡")) {
                    intent.putExtra("projectId", projectId);
//                    intent.putExtra("applyTime", currentYearMoth+"-"+DateUtils.getCurrentDayOfMonth());
                    intent.putExtra("applyTime", strDate);
                    intent.putExtra("type", 1); //上班卡
                    startActivity(intent);
                }
                break;
            case R.id.tv_end_work_status:
                //申请补下班卡
                String endStatus = tvEndWorkStatus.getText().toString().trim();
                if (endStatus.equals("未打卡")) {
                    intent.putExtra("projectId", projectId);
//                    intent.putExtra("applyTime", currentYearMoth+"-"+DateUtils.getCurrentDayOfMonth());
                    intent.putExtra("applyTime", strDate);
                    intent.putExtra("type", 2);
                    startActivity(intent);
                }
                break;
        }
    }
}
