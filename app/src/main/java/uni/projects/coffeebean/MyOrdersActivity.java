package uni.projects.coffeebean;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

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

            //TODO create view for list of orders:
            //          orderInfo.setText();
        });

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
}
