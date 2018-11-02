package com.luffy.pdf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
    }

    @OnClick(R.id.btn_show_pdf)
    public void onViewClicked() {
        Map<String, Object> mapParameter = new HashMap<>();
        mapParameter.put("title", "查看PDF");
        mapParameter.put("pdfUrl", "http://aojicrp.ks3-cn-beijing.ksyun.com/visa/study/backup/通过111899刘雨蓥.pdf");
        com.luffy.pdflib.helper.IntentHelper.PDF.gotoPdfShowActivity(MainActivity.this, mapParameter);
    }
}
