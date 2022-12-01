package uni.projects.coffeebean;

import android.os.Bundle;
import android.view.Menu;

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

        findViewById(R.id.btnOrder).setOnClickListener(view -> {

        });

        List<Flavor> list=List.of(new Flavor("Vanilla"), new Flavor("Cocoa"), new Flavor("Apple"), new Flavor("Marshmallow"), new Flavor("Caramel"), new Flavor("Pumpkin Spice"),
                new Flavor("Cinnamon"), new Flavor("Strawberry"), new Flavor("Watermelon"), new Flavor("Dragonfruit"), new Flavor("Melon"), new Flavor("Peach"));

        RecyclerView rv=findViewById(R.id.flavors);
        FlavorAdapter adapter=new FlavorAdapter(list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
