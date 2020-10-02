package magicfence.healthfiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    private LinearLayout myQrLT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        myQrLT = (LinearLayout) findViewById(R.id.llt_myqr);

        myQrLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qrIntent = new Intent(DashboardActivity.this, MyQRActivity.class);
                startActivity(qrIntent);
            }
        });
    }
}