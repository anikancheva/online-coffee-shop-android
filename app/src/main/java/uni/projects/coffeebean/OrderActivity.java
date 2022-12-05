package uni.projects.coffeebean;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

            Order order = new Order(type, size, style, checkedFlavors, special);
            //TODO -- find logged user in db and add order to their orders array
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String username = getSharedPreferences("user", MODE_PRIVATE).getString("user", null);


            startActivity(new Intent(this, MyOrdersActivity.class));
            finish();

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
            startActivity(new Intent(this, MyOrdersActivity.class));

        } else if (id == R.id.signOut) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    protected void setUpFlavorsRecyclerView() {

        List<Flavor> list = List.of(new Flavor("Vanilla"), new Flavor("Cocoa"), new Flavor("Apple"), new Flavor("Marshmallow"),
                new Flavor("Caramel"), new Flavor("Pumpkin Spice"), new Flavor("Cinnamon"), new Flavor("Strawberry"),
                new Flavor("Watermelon"), new Flavor("Dragonfruit"), new Flavor("Melon"), new Flavor("Peppermint"));

        RecyclerView rv = findViewById(R.id.flavors);
        FlavorAdapter adapter = new FlavorAdapter(list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

}































