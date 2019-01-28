package com.vise.bledemo.activity;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleExpandableListAdapter;

import com.vise.baseble.ViseBle;
import com.vise.baseble.common.ConnectState;
import com.vise.baseble.common.PropertyType;
import com.vise.baseble.core.DeviceMirror;
import com.vise.baseble.model.BluetoothLeDevice;
import com.vise.baseble.model.resolver.GattAttributeResolver;
import com.vise.baseble.utils.HexUtil;
import com.vise.bledemo.R;
import com.vise.bledemo.common.BluetoothDeviceManager;
import com.vise.bledemo.common.ToastUtil;
import com.vise.bledemo.event.CallbackDataEvent;
import com.vise.bledemo.event.ConnectEvent;
import com.vise.bledemo.event.NotifyDataEvent;
import com.vise.xsnow.cache.SpCache;
import com.vise.xsnow.event.BusManager;
import com.vise.xsnow.event.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 设备数据操作相关展示界面
 */
public class DeviceControlActivity extends AppCompatActivity {

    private static final String LIST_NAME = "NAME";
    private static final String LIST_UUID = "UUID";
    public static final String WRITE_CHARACTERISTI_UUID_KEY = "write_uuid_key";
    public static final String NOTIFY_CHARACTERISTIC_UUID_KEY = "notify_uuid_key";
    public static final String WRITE_DATA_KEY = "write_data_key";

    private SimpleExpandableListAdapter simpleExpandableListAdapter;

    private SpCache mSpCache;
    //设备信息
    private BluetoothLeDevice mDevice;
    //输出数据展示
    private StringBuilder mOutputInfo = new StringBuilder();
    private List<BluetoothGattService> mGattServices = new ArrayList<>();
    //设备特征值集合
    private List<List<BluetoothGattCharacteristic>> mGattCharacteristics = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_control);
        BusManager.getBus().register(this);
        init();
    }

    private void init() {
        mDevice = getIntent().getParcelableExtra(DeviceDetailActivity.EXTRA_DEVICE);
        if (mDevice != null) {

        }
        mSpCache = new SpCache(this);

        findViewById(R.id.one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] buf = new byte[4];
                buf[0] = 's';
                buf[1] = '1';
                buf[2] = '1';
                buf[3] = '0';
                BluetoothDeviceManager.getInstance().write(mDevice,buf);
            }
        });
        findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                byte[] buf = new byte[4];
                buf[0] = 's';
                buf[1] = '1';
                buf[2] = '2';
                buf[3] = '0';
                BluetoothDeviceManager.getInstance().write(mDevice,buf);
            }
        });
        findViewById(R.id.three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] buf = new byte[4];
                buf[0] = 's';
                buf[1] = '1';
                buf[2] = '3';
                buf[3] = '0';
                BluetoothDeviceManager.getInstance().write(mDevice,buf);
            }
        });
        findViewById(R.id.four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] buf = new byte[4];
                buf[0] = 's';
                buf[1] = '1';
                buf[2] = '4';
                buf[3] = '0';
                BluetoothDeviceManager.getInstance().write(mDevice,buf);
            }
        });
        findViewById(R.id.five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] buf = new byte[4];
                buf[0] = 's';
                buf[1] = '1';
                buf[2] = '5';
                buf[3] = '0';
                BluetoothDeviceManager.getInstance().write(mDevice,buf);
            }
        });
        findViewById(R.id.six).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] buf = new byte[4];
                buf[0] = 's';
                buf[1] = '1';
                buf[2] = '6';
                buf[3] = '0';
                BluetoothDeviceManager.getInstance().write(mDevice,buf);
            }
        });
        findViewById(R.id.seven).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                byte[] buf = new byte[4];
                buf[0] = 's';
                buf[1] = '1';
                buf[2] = '7';
                buf[3] = '0';
                BluetoothDeviceManager.getInstance().write(mDevice,buf);
            }
        });
        findViewById(R.id.eight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                byte[] buf = new byte[4];
                buf[0] = 's';
                buf[1] = '1';
                buf[2] = '8';
                buf[3] = '0';
                BluetoothDeviceManager.getInstance().write(mDevice,buf);
            }
        });
    }

    @Subscribe
    public void showConnectedDevice(ConnectEvent event) {
        if (event != null) {
            if (event.isSuccess()) {
                ToastUtil.showToast(DeviceControlActivity.this, "Connect Success!");
//                mConnectionState.setText("true");
                invalidateOptionsMenu();
                if (event.getDeviceMirror() != null && event.getDeviceMirror().getBluetoothGatt() != null) {

                    simpleExpandableListAdapter = displayGattServices(event.getDeviceMirror().getBluetoothGatt().getServices());
                }
            } else {
                if (event.isDisconnected()) {
                    ToastUtil.showToast(DeviceControlActivity.this, "Disconnect!");
                } else {
                    ToastUtil.showToast(DeviceControlActivity.this, "Connect Failure!");
                }
//                mConnectionState.setText("false");
                invalidateOptionsMenu();
                clearUI();
            }
        }
    }

    @Subscribe
    public void showDeviceCallbackData(CallbackDataEvent event) {
        if (event != null) {
            if (event.isSuccess()) {
                if (event.getBluetoothGattChannel() != null && event.getBluetoothGattChannel().getCharacteristic() != null
                        && event.getBluetoothGattChannel().getPropertyType() == PropertyType.PROPERTY_READ) {
                }
            } else {
            }
        }
    }

    @Subscribe
    public void showDeviceNotifyData(NotifyDataEvent event) {
        if (event != null && event.getData() != null && event.getBluetoothLeDevice() != null
                && event.getBluetoothLeDevice().getAddress().equals(mDevice.getAddress())) {
            mOutputInfo.append(HexUtil.encodeHexStr(event.getData())).append("\n");
        }
    }

    @Override
    protected void onResume() {
        invalidateOptionsMenu();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.connect, menu);
        if (BluetoothDeviceManager.getInstance().isConnected(mDevice)) {
            menu.findItem(R.id.menu_connect).setVisible(false);
            menu.findItem(R.id.menu_disconnect).setVisible(true);
            DeviceMirror deviceMirror = ViseBle.getInstance().getDeviceMirror(mDevice);
            if (deviceMirror != null) {
                List<BluetoothGattService> gattService_temp = deviceMirror.getBluetoothGatt().getServices();
                simpleExpandableListAdapter = displayGattServices(gattService_temp);
                for(BluetoothGattService serve: gattService_temp)
                {
                    Log.d("Lyar",serve.getUuid().toString());
                }
                for(List<BluetoothGattCharacteristic> con:mGattCharacteristics){
                    for(BluetoothGattCharacteristic ch:con)
                    {
                        Log.d("Lyar",ch.getUuid().toString());
                    }
                }
                BluetoothDeviceManager.getInstance().bindChannel(mDevice, PropertyType.PROPERTY_WRITE, UUID.fromString("000000ff-0000-1000-8000-00805f9b34fb"), UUID.fromString("0000ff01-0000-1000-8000-00805f9b34fb"), null);
            }
            showDefaultInfo();
        } else {
            menu.findItem(R.id.menu_connect).setVisible(true);
            menu.findItem(R.id.menu_disconnect).setVisible(false);
            clearUI();
        }
        if (ViseBle.getInstance().getConnectState(mDevice) == ConnectState.CONNECT_PROCESS) {
            menu.findItem(R.id.menu_refresh).setActionView(R.layout.actionbar_progress_indeterminate);
        } else {
            menu.findItem(R.id.menu_refresh).setActionView(null);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_connect://连接设备
                if (!BluetoothDeviceManager.getInstance().isConnected(mDevice)) {
                    BluetoothDeviceManager.getInstance().connect(mDevice);
                    invalidateOptionsMenu();
                }
                break;
            case R.id.menu_disconnect://断开设备
                if (BluetoothDeviceManager.getInstance().isConnected(mDevice)) {
                    BluetoothDeviceManager.getInstance().disconnect(mDevice);
                    invalidateOptionsMenu();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        BusManager.getBus().unregister(this);
        super.onDestroy();
    }

    /**
     * 根据GATT服务显示该服务下的所有特征值
     *
     * @param gattServices GATT服务
     * @return
     */
    private SimpleExpandableListAdapter displayGattServices(final List<BluetoothGattService> gattServices) {
        if (gattServices == null) return null;
        String uuid;
        final String unknownServiceString = getResources().getString(R.string.unknown_service);
        final String unknownCharaString = getResources().getString(R.string.unknown_characteristic);
        final List<Map<String, String>> gattServiceData = new ArrayList<>();
        final List<List<Map<String, String>>> gattCharacteristicData = new ArrayList<>();

        mGattServices = new ArrayList<>();
        mGattCharacteristics = new ArrayList<>();

        // Loops through available GATT Services.
        for (final BluetoothGattService gattService : gattServices) {
            final Map<String, String> currentServiceData = new HashMap<>();
            uuid = gattService.getUuid().toString();
            currentServiceData.put(LIST_NAME, GattAttributeResolver.getAttributeName(uuid, unknownServiceString));
            currentServiceData.put(LIST_UUID, uuid);
            gattServiceData.add(currentServiceData);

            final List<Map<String, String>> gattCharacteristicGroupData = new ArrayList<>();
            final List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
            final List<BluetoothGattCharacteristic> charas = new ArrayList<>();

            // Loops through available Characteristics.
            for (final BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                charas.add(gattCharacteristic);
                final Map<String, String> currentCharaData = new HashMap<>();
                uuid = gattCharacteristic.getUuid().toString();
                currentCharaData.put(LIST_NAME, GattAttributeResolver.getAttributeName(uuid, unknownCharaString));
                currentCharaData.put(LIST_UUID, uuid);
                gattCharacteristicGroupData.add(currentCharaData);
            }

            mGattServices.add(gattService);
            mGattCharacteristics.add(charas);
            gattCharacteristicData.add(gattCharacteristicGroupData);
        }

        final SimpleExpandableListAdapter gattServiceAdapter = new SimpleExpandableListAdapter(this, gattServiceData, android.R.layout
                .simple_expandable_list_item_2, new String[]{LIST_NAME, LIST_UUID}, new int[]{android.R.id.text1, android.R.id.text2},
                gattCharacteristicData, android.R.layout.simple_expandable_list_item_2, new String[]{LIST_NAME, LIST_UUID}, new
                int[]{android.R.id.text1, android.R.id.text2});
        return gattServiceAdapter;
    }



    private void showDefaultInfo() {
        mOutputInfo = new StringBuilder();
    }

    private void clearUI() {
        mOutputInfo = new StringBuilder();
        simpleExpandableListAdapter = null;
        mSpCache.remove(WRITE_CHARACTERISTI_UUID_KEY + mDevice.getAddress());
        mSpCache.remove(NOTIFY_CHARACTERISTIC_UUID_KEY + mDevice.getAddress());
        mSpCache.remove(WRITE_DATA_KEY + mDevice.getAddress());
    }
}
