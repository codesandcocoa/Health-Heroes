package magicfence.healthfiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PrescriptionsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference prescRef;
    FirebaseAuth mAuth;
    String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescriptions);
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();

        prescRef = FirebaseDatabase.getInstance().getReference().child("Patients").child(currentUserID)
                .child("Prescriptions");

        recyclerView = (RecyclerView) findViewById(R.id.prescriptions_recycler_view);

        FirebaseRecyclerOptions<Prescriptions> options =
                new FirebaseRecyclerOptions.Builder<Prescriptions>()
                        .setQuery(prescRef, Prescriptions.class)
                        .build();

        FirebaseRecyclerAdapter<Prescriptions,PrescriptionsViewHolder> firebaseRecyclerAdapter
                = new FirebaseRecyclerAdapter<Prescriptions,PrescriptionsViewHolder>(options)
        {

            @NonNull
            @Override
            public PrescriptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.prescription_view_layout,parent,false);
                return new PrescriptionsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull PrescriptionsViewHolder holder, int position, @NonNull Prescriptions model) {
                holder.setDr_name(model.getDr_name());
                String dt = getRef(position).getKey().toString();
                holder.setDate(dt);

            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(firebaseRecyclerAdapter);


    }
}

class PrescriptionsViewHolder extends RecyclerView.ViewHolder
{
    View mView;
    public PrescriptionsViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
    }
    public void setDr_name(String dr_name) {

        TextView tview = mView.findViewById(R.id.presc_view_dname);
        tview.setText(dr_name);
    }
    public void setDate(String date)
    {
        TextView tview = mView.findViewById(R.id.presc_view_date);
        tview.setText(date);
    }
}