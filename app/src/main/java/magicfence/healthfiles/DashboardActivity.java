package magicfence.healthfiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardActivity extends AppCompatActivity {
    private LinearLayout myQrLT,verifyLT,addPrescLT;
    private DatabaseReference docRef;
    private FirebaseAuth mAuth;
    String currentUserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        myQrLT = (LinearLayout) findViewById(R.id.llt_myqr);
        verifyLT = (LinearLayout) findViewById(R.id.verify_dr_llt);
        addPrescLT = (LinearLayout) findViewById(R.id.add_presc_llt);
        docRef = FirebaseDatabase.getInstance().getReference().child("Doctors");

        docRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(currentUserID))
                {
                    addPrescLT.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myQrLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qrIntent = new Intent(DashboardActivity.this, MyQRActivity.class);
                startActivity(qrIntent);
            }
        });

        verifyLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qrIntent = new Intent(DashboardActivity.this, VerifyDoctorActivity.class);
                qrIntent.putExtra("role","patient");
                startActivity(qrIntent);
            }
        });

        addPrescLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qrIntent = new Intent(DashboardActivity.this, VerifyDoctorActivity.class);
                qrIntent.putExtra("role","doctor");
                startActivity(qrIntent);
            }
        });
    }
}