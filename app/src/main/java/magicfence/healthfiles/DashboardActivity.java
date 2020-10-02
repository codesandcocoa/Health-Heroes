package magicfence.healthfiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    private LinearLayout myQrLT,verifyLT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        myQrLT = (LinearLayout) findViewById(R.id.llt_myqr);
        verifyLT = (LinearLayout) findViewById(R.id.verify_dr_llt);

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
                startActivity(qrIntent);
            }
        });
    }
}