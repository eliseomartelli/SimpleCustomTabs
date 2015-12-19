package io.github.eliseomartelli.simplecustomtabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CustomTabs.Warmer customtabsWarmer = CustomTabs.with(getApplicationContext()).warm();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabs.with(getApplicationContext()).setStyle(new CustomTabs.Style()
                        .setToolbarColor(R.color.colorPrimary)
                        .setShowTitle(true)
                        .setStartAnimation(android.R.anim.fade_in, android.R.anim.fade_out)
                        .setExitAnimation(android.R.anim.fade_in, android.R.anim.fade_out))
                        .openUrl("http://eliseomartelli.github.io", customtabsWarmer);
            }
        });
    }
}
