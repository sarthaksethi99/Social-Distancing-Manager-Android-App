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
import android.widget.Toast;

import java.util.ArrayList;

public class bluetoothList extends AppCompatActivity {

    ListView listView_bluetooth;
    TextView statusTextView;
    Button searchButton;
    ArrayList<String> bluetoothDevices = new ArrayList<>();
    EditText editText_area;
    ArrayAdapter arrayAdapter;
    BluetoothAdapter bluetoothAdapter;
    TextView textView_nearby_devices;
    Button check_Gathering;
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        // How to take action when given an intent
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i("Action", action);

            // This means we have finished our search
            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {

                statusTextView.setText("Finished!");
                searchButton.setEnabled(true);
                textView_nearby_devices.setText(bluetoothDevices.size() + " Persons nearby");

            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) { // Found a bluetooth device

                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = device.getName();
                String address = device.getAddress();

                bluetoothDevices.add(name + "   :    "+address);
                arrayAdapter.notifyDataSetChanged();

            }
        };
    };

    public void searchClicked(View view) {

        statusTextView.setText("Searching...");
        searchButton.setEnabled(false);


        bluetoothDevices.clear(); // Clear for repeat searches

        int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
        bluetoothAdapter.startDiscovery(); // Requires manifest permission

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_list);
        check_Gathering=findViewById(R.id.buttonCheckSafety);
        editText_area = findViewById(R.id.editTextArea);
        textView_nearby_devices=findViewById(R.id.textView_nearby_devices);
        listView_bluetooth = findViewById(R.id.listViewBluetooth);
        statusTextView = findViewById(R.id.statusTextView);
        searchButton = findViewById(R.id.buttonStart);

        arrayAdapter = new ArrayAdapter<>(bluetoothList.this,android.R.layout.simple_expandable_list_item_1, bluetoothDevices);

        listView_bluetooth.setAdapter(arrayAdapter);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); // Allows us to work w/ bluetooth

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND); // Tells us when we find a device
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(broadcastReceiver, intentFilter);

        check_Gathering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number_devices = textView_nearby_devices.getText().toString();
                String abc=editText_area.getText().toString();
                int max;
                try {
                    max= Integer.parseInt(abc)/4;
                }
                catch (NumberFormatException e)
                {
                    max=1;
                }
                String xyz=number_devices.substring(0,1);
                int num;
                try{
                    num=Integer.parseInt(xyz);
                }
                catch (NumberFormatException e)
                {
                    num=0;
                }
                if(num>max)
                {
                    check_Gathering.setBackgroundColor(Color.RED);
                    Toast.makeText(bluetoothList.this, "Not a safe zone",Toast.LENGTH_LONG).show();
                }
                else
                {
                    check_Gathering.setBackgroundColor((Color.GREEN));
                    Toast.makeText(bluetoothList.this, "You are in safe zone",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
