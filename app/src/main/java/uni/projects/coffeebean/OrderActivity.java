package uni.projects.coffeebean;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setUpFlavorsRecyclerView();


        findViewById(R.id.btnOrder).setOnClickListener(view -> {
            Toast.makeText(this, "Order clicked", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.myOrders) {
            Toast.makeText(this, "My orders clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.signOut) {
            Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show();

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































