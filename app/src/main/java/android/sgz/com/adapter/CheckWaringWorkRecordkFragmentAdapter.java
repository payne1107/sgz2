package android.sgz.com.adapter;

import android.content.Context;
import android.sgz.com.R;
import android.sgz.com.bean.WaringApplyListBean;
import android.sgz.com.utils.StringUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by WD on 2018/6/27.
 */

public class CheckWaringWorkRecordkFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<WaringApplyListBean.DataBean.ListBean> mList;

    public CheckWaringWorkRecordkFragmentAdapter(Context context, List<WaringApplyListBean.DataBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }
    /***
     * 更新数据
     * @param data
     */
    public void setData(List<WaringApplyListBean.DataBean.ListBean> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mList.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder ;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_check_waring_work_record, null);
            view.setTag(holder);
            holder.tvStartTime = view.findViewById(R.id.tv_start_work_time);
            holder.tvEndTime = view.findViewById(R.id.tv_end_work_time);
            holder.tvProjectName = view.findViewById(R.id.tv_project_name);
            holder.tvAddress = view.findViewById(R.id.tv_address);
            holder.tvUserName = view.findViewById(R.id.tv_username);
            holder.tvRemark = view.findViewById(R.id.tv_remark);
            holder.tvStatus = view.findViewById(R.id.tv_status);
            holder.tvType = view.findViewById(R.id.tv_type);
            holder.tvDate = view.findViewById(R.id.tv_date);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        WaringApplyListBean.DataBean.ListBean bean = mList.get(i);
        if (bean != null) {
            WaringApplyListBean.DataBean.ListBean.ProjectBean projectBean = bean.getProject();
            WaringApplyListBean.DataBean.ListBean.UserBean userBean = bean.getUser();
            int type = bean.getType();// 1上班  2下班
            String applyTime = bean.getApplytime();
            holder.tvType.setText(type == 1 ? "补卡类型：上班卡" : "补卡类型：下班卡");
            if (userBean != null) {
                String usernName = userBean.getRealname();
                holder.tvUserName.setText(StringUtils.isEmpty(usernName) ? "" : usernName);
            }
            String remark = bean.getRemark();
            holder.tvRemark.setText(StringUtils.isEmpty(remark) ? "" : remark);
            if (projectBean != null) {
                String projectName = projectBean.getName();
                String address = projectBean.getAddress();
                String endWorkTime = projectBean.getEndworktime();
                String startWorkTime = projectBean.getStartworktime();
                holder.tvProjectName.setText(StringUtils.isEmpty(projectName) ? "" : projectName);
                holder.tvAddress.setText(StringUtils.isEmpty(address) ? "" : address);
                holder.tvStartTime.setText(StringUtils.isEmpty(startWorkTime) ? "" : "起始时间："+startWorkTime);
                holder.tvEndTime.setText(StringUtils.isEmpty(endWorkTime) ? "" : "结束时间："+ endWorkTime);
                holder.tvDate.setText("补卡日期：" + applyTime);
            }
            int status = bean.getStatus();
            if (status == 1) {
                holder.tvStatus.setText("审核通过");
            } else if (status == 0) {
                holder.tvStatus.setText("不通过");
            } else {
                holder.tvStatus.setText("审核中");
            }
        }
        return view;
    }

    class ViewHolder{
        TextView tvStartTime;
        TextView tvEndTime;
        TextView tvProjectName;
        TextView tvUserName;
        TextView tvAddress;
        TextView tvRemark;
        TextView tvStatus;
        public TextView tvType;
        public TextView tvDate;
    }
}
