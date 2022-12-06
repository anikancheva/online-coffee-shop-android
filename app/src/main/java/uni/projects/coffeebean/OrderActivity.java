package uni.projects.coffeebean;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setUpFlavorsRecyclerView();


        findViewById(R.id.btnOrder).setOnClickListener(view -> {

            SwitchCompat scDecaf = findViewById(R.id.decaf);

            RadioGroup rgSize = findViewById(R.id.coffeeSize);
            RadioButton rbSize = findViewById(rgSize.getCheckedRadioButtonId());

            RadioGroup rgStyle = findViewById(R.id.coffeeStyle);
            RadioButton rbStyle = findViewById(rgStyle.getCheckedRadioButtonId());

            CheckBox cbSugar = findViewById(R.id.sugar);
            CheckBox cbCream = findViewById(R.id.cream);
            EditText etSpecial = findViewById(R.id.spclReq);

            String type = scDecaf.isChecked() ? "decaf" : "regular";
            String size = rbSize.getText().toString();
            String style = rbStyle.getText().toString();
            List<String> checkedFlavors = FlavorAdapter.CHECKED_FLAVORS;
            if (cbSugar.isChecked()) {
                checkedFlavors.add("Sugar");
            }

            if (cbCream.isChecked()) {
                checkedFlavors.add("Cream");
            }
            String special = etSpecial.getText().toString();


            String user = getSharedPreferences("user", MODE_PRIVATE).getString("username", null);

            Map<String, Object> order = new HashMap<>();
            order.put("user", user);
            order.put("type", type);
            order.put("size", size);
            order.put("style", style);
            order.put("flavors", checkedFlavors);
            order.put("special", special);

            FirebaseFirestore db=FirebaseFirestore.getInstance();
            db.collection("orders").add(order).addOnCompleteListener(task -> {
                task.addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Successfull order!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MyOrderActivity.class);
                    startActivity(intent);
                    finish();
                });
            });



        });


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

        } else if (id == R.id.myOrders) {
            startActivity(new Intent(this, MyOrderActivity.class));

        } else if (id == R.id.signOut) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    protected void setUpFlavorsRecyclerView() {

        List<String> list = List.of("Vanilla", "Cocoa", "Apple", "Marshmallow", "Caramel", "Pumpkin Spice", "Cinnamon", "Strawberry",
                "Watermelon", "Dragonfruit", "Melon", "Peppermint");

        RecyclerView rv = findViewById(R.id.flavors);
        FlavorAdapter adapter = new FlavorAdapter(list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

}































