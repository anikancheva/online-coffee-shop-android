package uni.projects.coffeebean;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MyOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        setUpOrdersRecyclerView();


        findViewById(R.id.btnSelectOrder).setOnClickListener(view -> {


        });

        findViewById(R.id.btnCreateNewOrder).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), OrderActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.myAccount) {
            startActivity(new Intent(this, ProfileActivity.class));

        } else if (id == R.id.signOut) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpOrdersRecyclerView() {

        String user = getSharedPreferences("user", MODE_PRIVATE).getString("username", null);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<Map<String, Object>> orders = new ArrayList<>();

        db.collection("orders").get().addOnCompleteListener(task -> {
            task.getResult().forEach(o -> {
                if (o.get("user").toString().equals(user)) {
                    Map<String, Object> current = new HashMap<>();
                    current.put("type", o.get("type").toString());
                    current.put("size", o.get("size").toString());
                    current.put("style", o.get("style").toString());
                    current.put("flavors", o.get("flavors"));
                    current.put("special", o.get("special").toString());
                    orders.add(current);
                }
            });

            OrderAdapter adapter = new OrderAdapter(orders);
            RecyclerView rv = findViewById(R.id.allOrders);
            rv.setAdapter(adapter);
            rv.setLayoutManager(new LinearLayoutManager(this));
        });
    }

    private void setUpTimeDialog() {

        SimpleDateFormat format = new SimpleDateFormat("hh:mm", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();

        int min = calendar.get(Calendar.MINUTE);
        if (min < 15) {
            calendar.set(Calendar.MINUTE, 15);
        } else if (min < 30) {
            calendar.set(Calendar.MINUTE, 30);
        } else if (min < 45) {
            calendar.set(Calendar.MINUTE, 45);
        }

        String[] times = new String[3];
        for (int i = 0; i < 3; i++) {
            calendar.add(Calendar.MINUTE, 15);
            times[i] = format.format(calendar.getTime());
        }

        AlertDialog.Builder pickUpTime = new AlertDialog.Builder(this);
        pickUpTime.setTitle("Choose a pick-up time");
        pickUpTime.setSingleChoiceItems(times, 0, (dialogInterface, i) -> {

        });
        pickUpTime.setPositiveButton("Confirm", (dialogInterface, i) -> {
            Toast.makeText(this, "Successfull order!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MyOrdersActivity.class);
            startActivity(intent);
            finish();

        });
        pickUpTime.setNegativeButton("Cancel", (dialogInterface, i) -> {

        });
        pickUpTime.show();
    }
}
