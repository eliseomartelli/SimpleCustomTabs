package io.github.eliseomartelli.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.github.eliseomartelli.simplecustomtabs.CustomTabs;

public class MainActivity extends AppCompatActivity {

    CustomTabs.Warmer warmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        warmer = CustomTabs.with(this).warm();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabs.with(getApplicationContext())
                        .setStyle(new CustomTabs.Style(getApplicationContext())
                                .setShowTitle(true)
                                .setCloseButton(android.R.drawable.button_onoff_indicator_off)
                                .setStartAnimation(android.R.anim.fade_in, android.R.anim.fade_out)
                                .setExitAnimation(android.R.anim.fade_in, android.R.anim.fade_out)
                                .setToolbarColor(R.color.colorPrimary))
                        .setFallBackActivity(FallbackActivity.class)
                        .openUrl("http://eliseomartelli.github.io", MainActivity.this);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        warmer.unwarm();
    }
}
