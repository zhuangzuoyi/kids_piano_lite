package com.vise.bledemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.vise.baseble.common.BluetoothServiceType;
import com.vise.baseble.model.BluetoothLeDevice;
import com.vise.baseble.model.adrecord.AdRecord;
import com.vise.baseble.utils.AdRecordUtil;
import com.vise.baseble.utils.HexUtil;
import com.vise.bledemo.R;
import com.vise.bledemo.adapter.MergeAdapter;
import com.vise.bledemo.common.BluetoothDeviceManager;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 设备详细信息展示界面
 */
public class DeviceDetailActivity extends AppCompatActivity {

    public static final String EXTRA_DEVICE = "extra_device";
    private BluetoothLeDevice mDevice;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
        init();
    }

    private void init() {
        mDevice = getIntent().getParcelableExtra(EXTRA_DEVICE);
        findViewById(R.id.p_control).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDevice == null) return ;
                Intent intent = new Intent(DeviceDetailActivity.this, PlayControlActivity.class);
                intent.putExtra(DeviceDetailActivity.EXTRA_DEVICE, mDevice);
                startActivity(intent);

            }
        });

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDevice == null) return ;
                Intent intent = new Intent(DeviceDetailActivity.this, DeviceControlActivity.class);
                intent.putExtra(DeviceDetailActivity.EXTRA_DEVICE, mDevice);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_connect:
                if (mDevice == null) return false;
                Intent intent = new Intent(DeviceDetailActivity.this, DeviceControlActivity.class);
                intent.putExtra(DeviceDetailActivity.EXTRA_DEVICE, mDevice);
                startActivity(intent);
                break;
        }
        return true;
    }

}
