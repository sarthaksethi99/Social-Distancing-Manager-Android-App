package a.com;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    DatabaseReference mref;
    ListView list_View;
    //    ArrayList<Details> detailList = new ArrayList<>();
    ArrayList<String> details = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        list_View = findViewById(R.id.listView);


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(InfoActivity.this, android.R.layout.simple_expandable_list_item_1, details);
        list_View.setAdapter(arrayAdapter);

        mref = FirebaseDatabase.getInstance().getReference().child("hotspots");
        mref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String values = dataSnapshot.getValue().toString();
                details.add(values);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                arrayAdapter.notifyDataSetChanged();
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


    }
}












//
//        DetailsAdapter detailsAdapter = new DetailsAdapter();
//        list_View.setAdapter(detailsAdapter);



//        Details one = new Details("a","b");
//        Details two = new Details("asd","b");
//        Details three = new Details("aadsf","b");
//        Details four = new Details("aadfeq","b");
//
//        detailList.add(one);
//        detailList.add(two);
//        detailList.add(three);
//        detailList.add(four);



 //   }

//    class DetailsAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return detailList.size();
//        }
//
//        @Override
//        public Details getItem(int position) {
//            return detailList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View itemView = getLayoutInflater().inflate(
//                    R.layout.list_view_layout,
//                    parent,
//                    false
//            );
//            TextView tv_state = itemView.findViewById(R.id.textViewStates);
//            TextView tv_district = itemView.findViewById(R.id.textViewDistrict);
//            Details detail = getItem(position);
//            tv_state.setText(detail.getState());
//            tv_district.setText(detail.getDistricts());
//
//
//            return itemView;
//        }
//    }

//        mref = FirebaseDatabase.getInstance().getReference().child("Delhi");
//        mref.child("1").setValue("South");
//        mref.child("2").setValue("South Delhi");
//        mref.child("3").setValue("Shahdara");
//        mref.child("4").setValue("West");
//
//        mref = FirebaseDatabase.getInstance().getReference().child("Uttar Pradesh");
//        mref.child("1").setValue("South");
//        mref.child("2").setValue("South Delhi");
//        mref.child("3").setValue("Shahdara");
//        mref.child("4").setValue("West");

//}
