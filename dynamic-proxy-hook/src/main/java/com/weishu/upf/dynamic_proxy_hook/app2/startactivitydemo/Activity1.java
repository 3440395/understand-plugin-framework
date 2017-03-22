package com.weishu.upf.dynamic_proxy_hook.app2.startactivitydemo;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.R.attr.text;

/**
 * Created by xuekai on 2017/3/9.
 */

public class Activity1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
//            mInstrumentation


            Field mInstrumentationField = Activity.class.getDeclaredField("mInstrumentation");
            mInstrumentationField.setAccessible(true);
            Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(this);

            InstrumentProxy instrumentProxy = new InstrumentProxy(mInstrumentation);

            mInstrumentationField.set(this, instrumentProxy);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        final Button textView = new Button(this);
        textView.setText("111111111111");
        setContentView(textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Activity1.this, Activity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {
//            mInstrumentation


                Field mInstrumentationField = Activity.class.getDeclaredField("mInstrumentation");
                mInstrumentationField.setAccessible(true);
                Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(this);

                InstrumentProxy instrumentProxy = new InstrumentProxy(mInstrumentation);

                mInstrumentationField.set(this, instrumentProxy);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
