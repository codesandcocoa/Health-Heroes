package magicfence.healthfiles;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddPrescActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    String patient_id;
    DatabaseReference patientRef;
    TextView PatientNameTV,DateTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_presc);

        mAuth = FirebaseAuth.getInstance();
        patient_id = getIntent().getStringExtra("uid");
        patientRef = FirebaseDatabase.getInstance().getReference().child("Patients").child(patient_id);

        DateTV = (TextView) findViewById(R.id.add_presc_pdate);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        final String formattedDate = df.format(c);

        PatientNameTV = (TextView) findViewById(R.id.add_presc_pname);
        patientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    String name = snapshot.child("fullname").getValue().toString();
                    PatientNameTV.setText(name);
                    DateTV.setText(formattedDate);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Toast.makeText(this, "Patient ID: " + patient_id, Toast.LENGTH_SHORT).show();

    }
}