package com.mycentre.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.global.ui.activity.TitleActivity;
import com.mycentre.R;
import com.mycentre.databinding.ASystemSettingsBinding;

/**
 * 系统设置
 * Created by Water on 2018/5/8.
 */

public class A_System_Settings extends TitleActivity {

    private ASystemSettingsBinding binding;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, A_System_Settings.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleText("系统设置");
        binding = setView(R.layout.a_system_settings);
        initListener();
    }

    private void initListener() {
        binding.setClick(v -> {
            int i = v.getId();
            if (i == R.id.rl_divisional_management) {
                // 部门管理
                A_Divisional_Management.startActivity(this);
            } else if (i == R.id.rl_official_authority) {
                // TODO: 2018/5/8 职务权限
                A_Duty_Jurisdiction.startActivity(this);
            } else if (i == R.id.rl_personnel_management) {
                //  人员管理
                A_Personnel_Management.startActivity(this);
            } else if (i == R.id.rl_attendance_set) {
                // 考勤设置
                Intent intent = null;
                try {
                    intent = new Intent(this, Class.forName("com.unistrong.working.ui.activity.A_Attendance_Setting"));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                startActivity(intent);

            } else if (i == R.id.rl_finance_classify) {
                // TODO: 2018/5/8 财务分类

            } else if (i == R.id.rl_finance_approval) {
                // TODO: 2018/5/8 财务审批

            }
        });

    }
}
