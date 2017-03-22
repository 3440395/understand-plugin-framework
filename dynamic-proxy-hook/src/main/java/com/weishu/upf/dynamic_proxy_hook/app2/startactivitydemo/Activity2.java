package com.weishu.upf.dynamic_proxy_hook.app2.startactivitydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by xuekai on 2017/3/9.
 */

public class Activity2 extends Activity { @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Button textView = new Button(this);
    textView.setText("222222");
    setContentView(textView);
    textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Activity2.this,Activity3.class));
        }
    });
}
}
