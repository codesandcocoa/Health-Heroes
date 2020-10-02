package magicfence.healthfiles;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AddPrescActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    String patient_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_presc);

        mAuth = FirebaseAuth.getInstance();
        patient_id = getIntent().getStringExtra("uid");

        Toast.makeText(this, "Patient ID: " + patient_id, Toast.LENGTH_SHORT).show();

    }
}