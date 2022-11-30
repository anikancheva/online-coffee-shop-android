package uni.projects.coffeebean;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        findViewById(R.id.btnOrder).setOnClickListener(view -> {

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
