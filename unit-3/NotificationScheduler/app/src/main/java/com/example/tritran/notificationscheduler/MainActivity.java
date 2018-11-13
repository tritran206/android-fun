package com.example.tritran.notificationscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {
    private JobScheduler mScheduler;
    private static final int JOB_ID = 0;
    private Switch mDeviceIdleSwitch;
    private Switch mDeviceChargingSwitch;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDeviceIdleSwitch = findViewById(R.id.idleSwitch);
        mDeviceChargingSwitch = findViewById(R.id.chargingSwitch);
        mSeekBar = findViewById(R.id.seekBar);
        final TextView seekBarProgress = findViewById(R.id.seekBarProgress);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > 0) {
                    seekBarProgress.setText(progress + "s");
                } else {
                    seekBarProgress.setText(R.string.not_set);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void scheduleJob(View view) {
        //check if seekbar was changed
        int seekBarInt =  mSeekBar.getProgress();
        boolean seekBarSet = seekBarInt > 0;


        //initialize JobScheduler
        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        //get chose from radio button
        RadioGroup networkOptions = findViewById(R.id.networkOptions);
        int selectedNetworkID = networkOptions.getCheckedRadioButtonId();
        int selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;

        switch (selectedNetworkID) {
            case R.id.noNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
                break;
            case R.id.anyNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;
                break;
            case R.id.wifiNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED;
                break;
        }

        //create job
        ComponentName serviceName = new ComponentName(getPackageName(), NotificationJobService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName);
        builder.setRequiredNetworkType(selectedNetworkOption);
        builder.setRequiresDeviceIdle(mDeviceIdleSwitch.isChecked());
        builder.setRequiresCharging(mDeviceIdleSwitch.isChecked());
        if(seekBarSet) {
            builder.setOverrideDeadline(seekBarInt * 1000);
        }

        //check to see if constraint requirement is met
        boolean constraintSet = selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE
                || mDeviceChargingSwitch.isChecked() || mDeviceIdleSwitch.isChecked()
                || seekBarSet;

        if(constraintSet) {
            //Schedule the job and notify the user
            JobInfo myJobInfo = builder.build();
            mScheduler.schedule(myJobInfo);
            Toast.makeText(this, R.string.job_scheduled_will_run, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, R.string.set_at_least_one,
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void cancelJob(View view) {
        if (mScheduler!=null){
            mScheduler.cancelAll();
            mScheduler = null;
            Toast.makeText(this, R.string.jobs_cancelled, Toast.LENGTH_SHORT).show();
        }
    }
}
