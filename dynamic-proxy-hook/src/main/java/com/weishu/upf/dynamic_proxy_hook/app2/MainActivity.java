package com.weishu.upf.dynamic_proxy_hook.app2;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.weishu.upf.dynamic_proxy_hook.app2.hook.HookHelper;
import com.weishu.upf.dynamic_proxy_hook.app2.startactivitydemo.Activity1;
import com.weishu.upf.dynamic_proxy_hook.app2.startactivitydemo.Activity2;
import com.weishu.upf.dynamic_proxy_hook.app2.startactivitydemo.InstrumentProxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author weishu
 * @date 16/1/28
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: 16/1/28 支持Activity直接跳转请在这里Hook
        // 家庭作业,留给读者完成.

        Button tv = new Button(this);
        tv.setText("测试界面");

        setContentView(tv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setData(Uri.parse("http://www.baidu.com"));
//
//                // 注意这里使用的ApplicationContext 启动的Activity
//                // 因为Activity对象的startActivity使用的并不是ContextImpl的mInstrumentation
//                // 而是自己的mInstrumentation, 如果你需要这样, 可以自己Hook
//                // 比较简单, 直接替换这个Activity的此字段即可.
//                getApplicationContext().startActivity(intent);

                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
    }
//
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(newBase);
//        try {
//            // 在这里进行Hook
//            HookHelper.attachContext();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {

            Class<?> activityThread = Class.forName("android.app.ActivityThread");

            try {
                Method currentActivityThreadMethod = activityThread.getDeclaredMethod("currentActivityThread");
                currentActivityThreadMethod.setAccessible(true);

                Object currentActivityThread = currentActivityThreadMethod.invoke(null);


                Field mInstrumentationField = activityThread.getDeclaredField("mInstrumentation");
                mInstrumentationField.setAccessible(true);
                Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);

                InstrumentProxy instrumentProxy = new InstrumentProxy(mInstrumentation);

                mInstrumentationField.set(currentActivityThread,instrumentProxy);



            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
