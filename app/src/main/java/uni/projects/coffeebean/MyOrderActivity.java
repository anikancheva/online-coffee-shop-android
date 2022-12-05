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

public class MyOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        TextView orderInfo = findViewById(R.id.txtOrderInfo);
        Intent intent=getIntent();

       String type= intent.getStringExtra("type");
        String size=  intent.getStringExtra("size");
        String style=  intent.getStringExtra("style");
        String flavors=  intent.getStringExtra("flavors");
        String special=  intent.getStringExtra("special");

        orderInfo.setText(type+"\n"+size+"\n"+style+"\n"+flavors+"\n"+special);
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
