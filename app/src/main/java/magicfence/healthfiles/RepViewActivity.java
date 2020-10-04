package magicfence.healthfiles;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RepViewActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    String currentUserID,rtitle,rdate,rkey,link,desc,dr_name;
    TextView dNameTv,rDateTV,rDescTV;
    ImageView qrView;
    Button DeleteButton;
    DatabaseReference repRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rep_view);
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        repRef = FirebaseDatabase.getInstance().getReference().child("Patients").child(currentUserID).child("Reports");

        currentUserID = mAuth.getCurrentUser().getUid();
        rtitle = getIntent().getStringExtra("title");
        rdate = getIntent().getStringExtra("date");
        rkey = rtitle + rdate;

        dNameTv = (TextView) findViewById(R.id.rep_dname);
        rDateTV = (TextView) findViewById(R.id.rep_pdate);
        rDescTV = (TextView) findViewById(R.id.rep_desc);
        qrView = (ImageView) findViewById(R.id.qr_view_rep);
        DeleteButton = (Button) findViewById(R.id.rep_del_btn);

        rDateTV.setText(rdate);

        repRef.child(rkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    desc = snapshot.child("desc").getValue().toString();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}