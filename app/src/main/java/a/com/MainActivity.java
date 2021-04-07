package a.com;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    public static TextView datas, textView_state, textView_district;
    public static String details;
    public static String district, state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "district",Toast.LENGTH_LONG);
        final EditText edit_text_pincode = findViewById(R.id.editTextPincode);
        datas=findViewById(R.id.textView3);
        textView_state=findViewById(R.id.textViewStateEdit);
        textView_district=findViewById(R.id.textViewDistrictEdit);

        ImageButton button_info = findViewById(R.id.btnInfo);
        button_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });


        final Button check = findViewById(R.id.button);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pincode = edit_text_pincode.getText().toString();

                fetchData process =new fetchData(pincode);
                process.execute();



//                Toast.makeText(MainActivity.this, datas.getText().toString(), Toast.LENGTH_SHORT).show();
//                datas.setText(details);
//                String pincode = edit_text_pincode.getText().toString();
//                String state_pincode;
//                String district_pincode;
//                if(details.charAt(14)=='u')
//                {
//
//                }
//                else {
//                    Toast.makeText(MainActivity.this, "Wrong Pincode Entered", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        Button Details_check =findViewById(R.id.check);
        Details_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mref;
                mref = FirebaseDatabase.getInstance().getReference().child("areas").child(state).child(district);


                mref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        if(dataSnapshot.exists())
                        {
                            Toast.makeText(MainActivity.this, "You are not allowed for gathering \n your area is in hotspot region",Toast.LENGTH_SHORT).show();
                            datas.setText("NOT ALLOWED FOR GATHERING");

                        }
                        else
                        {
                            Intent intent = new Intent(MainActivity.this, CrowdManagerActivity.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "You are allowed for small gathering",Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent intent = new Intent(MainActivity.this, bluetoothList.class);
                startActivity(intent);

            }
        });

    }
}
//                        if(!dataSnapshot.exists())
//                        {
//                            Toast.makeText(MainActivity.this, "You are allowed for small gathering",Toast.LENGTH_LONG);
//                        }
//                        else
//                        {
//                            Toast.makeText(MainActivity.this, "You are not allowed for gathering \n your area is in hotspot region",Toast.LENGTH_SHORT);
//                        }