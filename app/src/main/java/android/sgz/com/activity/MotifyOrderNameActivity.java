package android.sgz.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.StringUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/***
 * 修改工单
 */
public class MotifyOrderNameActivity extends BaseActivity implements View.OnClickListener {
    private static final int REQUEST_CHOOSE_COMPANY_CODE = 10006;
    protected static final String REQUEST_CHOOSE_COMPANY_KEY = "request_choose_company_key";
    private EditText etName;
    private TextView tvSave;
    private int projectId;
    private TextView tvChooseCompany;
    private Context mContext;
    private int merchantId;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_motify_order_name);
        mContext = MotifyOrderNameActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("修改工单名称", true, true);
        projectId = getIntent().getIntExtra("projectId", 0);
        etName = findViewById(R.id.et_name);
        tvSave = findViewById(R.id.tv_save);
        tvChooseCompany = findViewById(R.id.tv_choose_company);
        tvSave.setOnClickListener(this);
        tvChooseCompany.setOnClickListener(this);
    }

    /***
     * 修改工单名称
     */
    private void motifyOrderName() {
        String name = etName.getText().toString().trim();
        if (StringUtils.isEmpty(name) && merchantId <= 0) {
            toastMessage("请输入工单名称或者添加所属公司");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("projectid", String.valueOf(projectId));
        if (!StringUtils.isEmpty(name)) {
            params.put("name", name);
        }
        if (merchantId > 0) {
            params.put("merchantid", String.valueOf(merchantId));
        }
        httpPostRequest(ConfigUtil.MOTIFY_PROJECT_NAME_URL, params, ConfigUtil.MOTIFY_PROJECT_NAME_URL_ACTION);
    }


    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.MOTIFY_PROJECT_NAME_URL_ACTION:
                if (getRequestCode(json) == 1) {
                    toastMessage("修改成功");
                    finish();
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                motifyOrderName();
                break;
            case R.id.tv_choose_company:
                startActivityForResult(new Intent(mContext, ChooseCompanyActivity.class),REQUEST_CHOOSE_COMPANY_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CHOOSE_COMPANY_CODE:
                    merchantId = data.getIntExtra(REQUEST_CHOOSE_COMPANY_KEY, -1);
                    String merchantName = data.getStringExtra("merchantname");
                    tvChooseCompany.setText(merchantName);
                    break;
            }
        }
    }
}
