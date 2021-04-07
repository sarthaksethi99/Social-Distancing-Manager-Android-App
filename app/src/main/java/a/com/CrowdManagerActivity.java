package a.com;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class CrowdManagerActivity extends AppCompatActivity {


    ListView listView_bluetooth;
    TextView statusTextView;
    ArrayList<String> bluetoothDevices = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    BluetoothAdapter bluetoothAdapter;
    EditText editText_area;
//    int numberofdevices=0;

    Button button_start;

//    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        // How to take action when given an intent
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            Log.i("Action", action);
//
//            // This means we have finished our search
//            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
//
//                statusTextView.setText("Finished!");
//
//            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) { // Found a bluetooth device
//
//                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                String name = device.getName();
//                String address = device.getAddress();
//
//                bluetoothDevices.add(name + "   :    "+address);
//                arrayAdapter.notifyDataSetChanged();
//
//            }
//        };
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crowd_manager);
        editText_area=findViewById(R.id.editTextArea);
        TextView text=findViewById(R.id.area);
        listView_bluetooth=findViewById(R.id.listViewBluetooth);
        String abc=editText_area.getText().toString();
        button_start= findViewById(R.id.buttonStart);
//        arrayAdapter = new ArrayAdapter<>(CrowdManagerActivity.this,android.R.layout.simple_expandable_list_item_1, bluetoothDevices);
//
//        listView_bluetooth.setAdapter(arrayAdapter);
//
//        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); // Allows us to work w/ bluetooth
//
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
//        intentFilter.addAction(BluetoothDevice.ACTION_FOUND); // Tells us when we find a device
//        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
//        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
//
//        registerReceiver(broadcastReceiver, intentFilter);

        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_start.getText().toString().equals("Stop"))
                {

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);

                }
                String abc=editText_area.getText().toString();
                int max;
                try {
                    max= Integer.parseInt(abc)/4;
                }
                catch (NumberFormatException e)
                {
                    max=1;
                }
                button_start.setText("Stop");
                button_start.setBackgroundColor(Color.GREEN);
                button_start.setTextColor(Color.RED);

//
//                statusTextView.setText("Searching...");
//
//
//
//                bluetoothDevices.clear(); // Clear for repeat searches
//
//                int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
//                ActivityCompat.requestPermissions(CrowdManagerActivity.this,
//                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
//                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
//                bluetoothAdapter.startDiscovery(); // Requires manifest permission


            }
        });

    }

}
