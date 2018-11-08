package com.example.kritik.systemsim;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private String info = null;

    private BroadcastReceiver ReceiveWifi = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if(intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION))
            {
                String bssid = intent.getStringExtra("BSSID");
                String ssid = intent.getStringExtra("ssid");
                String mac = intent.getStringExtra("mac");

                info = intent.getExtras().toString();

//                if (info != null)
//                    Log.d("infooooo", info);
//
//                if (bssid != null)
//                    Log.d("Kun-BSSID", bssid);
//                if (ssid != null)
//                    Log.d("Kun-SSID", ssid);
//                if (mac != null)
//                    Log.d("Kun-mac", mac);

                Intent i = new Intent("android.intent.action.SmsReceiver").putExtra("wifi_info", info);
                context.sendBroadcast(i);


//            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
//            Log.d("NETWORKAKSKDKFSDFSDF", Integer.toString(wifiState));
            }
            // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        }
    };


//    private BroadcastReceiver receiver = new NetworkStateReceiver();
    private IntentFilter filter = new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        this.registerReceiver(ReceiveWifi, filter);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        this.unregisterReceiver(ReceiveWifi);
    }

    public void SendDefaultBroadcast(View view)
    {
        TextView info_box = (TextView)findViewById(R.id.tb_info_box);
        info_box.setText(info);
    }

}
