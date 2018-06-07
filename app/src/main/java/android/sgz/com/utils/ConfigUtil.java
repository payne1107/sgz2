package android.sgz.com.utils;


import android.sgz.com.application.MyApplication;

/**
 * Created by Administrator on 2017/9/29 0029.
 * 网络地址存放
 */

public class ConfigUtil {
    public static long TenYears8 = 10L * 365 * 1000 * 60 * 60 * 24L * 80;
    public static long TenYears = 10L * 365 * 1000 * 60 * 60 * 24L * 3 ;
    public static String sessionId;

    /****
     * 登录接口
     */
    public static final String LOGIN_URL = MyApplication.REQUEST_URL + "login";
    public static final int LOGIN_URL_ACTION = 1;
    /****
     * 修改昵称
     */
    public static final String UPDATE_NICK_NAME_URL = MyApplication.REQUEST_URL + "personal/editName";
    public static final int UPDATE_NICK_NAME_URL_ACTION = 2;
    /***
     * 获取所有职业
     */
    public static final String QUERY_ALL_PROFESSION_URL = MyApplication.REQUEST_URL + "personal/getAllProfession";
    public static final int QUERY_ALL_PROFESSION_URL_ACTION = 3;
    /***
     * 保存用户职业
     */
    public static final String SAVE_USER_PROFESSION_URL = MyApplication.REQUEST_URL + "personal/saveProfession";
    public static final int SAVE_USER_PROFESSION_URL_ACTION = 4;
    /****
     * 获取所有职称
     */
    public static final String QUERY_ALL_PROFESSION_LEVEL_URL = MyApplication.REQUEST_URL + "personal/getAllProfessionLevel";
    public static final int QUERY_ALL_PROFESSION_LEVEL_URL_ACTION = 5;

}
