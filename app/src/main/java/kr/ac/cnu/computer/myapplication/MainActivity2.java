package kr.ac.cnu.computer.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.*;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "MainActivity2";
    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference usersRef = rootRef.child("users");

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = findViewById(R.id.listView);
        Button button2 = (Button) findViewById(R.id.button2);
        EditText editText1 = (EditText)findViewById(R.id.editTextUserId);
        EditText editText2 = (EditText)findViewById(R.id.editTextName);
        EditText editText3 = (EditText)findViewById(R.id.editTextEmail);
        Button send_button = (Button) findViewById(R.id.send_button);
        Button get_button = (Button) findViewById(R.id.get_Button);

        get_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        send_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                writeNewUser(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString());
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();


                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_item, list);
        listView.setAdapter(adapter);

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    String txt = "Email :" + user.getEmail() + " Name :" + user.getUsername();
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, error.getMessage()); //Don't ignore errors!
            }
        });
    }
    public void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        rootRef.child("users").child(userId).setValue(user);
    }


}