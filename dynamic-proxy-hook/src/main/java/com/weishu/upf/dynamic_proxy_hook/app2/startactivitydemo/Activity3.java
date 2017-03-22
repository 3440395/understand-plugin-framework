package com.weishu.upf.dynamic_proxy_hook.app2.startactivitydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by xuekai on 2017/3/9.
 */

public class Activity3 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button textView = new Button(this);
        textView.setText("3333333");
        setContentView(textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activity3.this, "我是33333", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
