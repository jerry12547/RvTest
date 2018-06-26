package com.jerry.slideshow;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SlideActivity extends AppCompatActivity {

    private ViewPager vpMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        vpMain = findViewById(R.id.activity_slide_vp);

    }
}
