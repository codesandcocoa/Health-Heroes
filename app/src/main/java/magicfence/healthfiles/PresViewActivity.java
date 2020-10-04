package magicfence.healthfiles;

import android.graphics.Bitmap;
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

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class PresViewActivity extends AppCompatActivity {

    TextView DnameTv, DateTv, PresTv;
    Button DeleteButton;
    ImageView qrView;
    FirebaseAuth mAuth;
    String pdate,dname,currentUserID,press,d_id;
    DatabaseReference presRef;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pres_view);
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        presRef = FirebaseDatabase.getInstance().getReference().child("Patients").child(currentUserID).child("Prescriptions");

        DnameTv = (TextView) findViewById(R.id.press_dname);
        DateTv = (TextView) findViewById(R.id.press_pdate);
        PresTv = (TextView) findViewById(R.id.press_presc);
        DeleteButton = (Button) findViewById(R.id.presc_del_btn);
        qrView = (ImageView) findViewById(R.id.qr_view_pres);

        pdate = getIntent().getStringExtra("key");
        dname = getIntent().getStringExtra("dname");

        DnameTv.setText("Dr." + dname);
        DateTv.setText(pdate);
        presRef.child(pdate).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    press = snapshot.child("prescriptions").getValue().toString();
                    d_id = snapshot.child("doctor_id").getValue().toString();
                    PresTv.setText(press);
                    QRGEncoder qrgEncoder = new QRGEncoder(d_id, null, QRGContents.Type.TEXT, 100);
                    bitmap = qrgEncoder.getBitmap();
                    qrView.setImageBitmap(bitmap);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}