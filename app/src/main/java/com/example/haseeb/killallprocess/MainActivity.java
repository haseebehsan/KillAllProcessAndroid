package com.example.haseeb.killallprocess;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ApplicationInfo> packages;
                PackageManager pm;
                pm = getPackageManager();
                //get a list of installed apps.
                packages = pm.getInstalledApplications(0);

                ActivityManager mActivityManager = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
                String myPackage = getApplicationContext().getPackageName();
                for (ApplicationInfo packageInfo : packages) {
                    if((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM)==1)continue;
                    if(packageInfo.packageName.equals(myPackage)) continue;
                    mActivityManager.killBackgroundProcesses(packageInfo.packageName);
                }
                }

        });
    }
}
