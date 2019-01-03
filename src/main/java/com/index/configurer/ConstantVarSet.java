package com.index.configurer;

/**
 * @Auther: Index
 * @Date: 2018/12/5 11:46
 * @Description: 系统常量配置
 */
public class ConstantVarSet {
    // 缺省视频封面集番号
    public static final String VIDEO_COVER_PICTURE_NUMCODE = "cover";

    // 缺省超级管理员账户
    public static final String SUPER_ADMIN_USER_ID = "superadmin";

    // 视频系统
    // 视频状态常量--与ConstantVarSet.js对应
    public static final Integer VIDEO_NORMAL_STATUS = 0;
    public static final Integer VIDEO_DELETED_STATUS = 1;
    public static final Integer VIDEO_EXAMING_STATUS = 4;
    public static final Integer VIDEO_CODECS_STATUS = 5;
    public static final Integer VIDEO_CODECS_SUCCESS_STATUS = 501;
    public static final Integer VIDEO_CODECS_FAILURE_STATUS = 503;
}
