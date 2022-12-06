package uni.projects.coffeebean;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView nameField = findViewById(R.id.usrnameField);
        TextView emailField = findViewById(R.id.emailField);

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String username = sp.getString("username", null);
        String email = sp.getString("email", null);
        nameField.setText(username);
        emailField.setText(email);

        // delete account
        findViewById(R.id.btnDeleteAcc).setOnClickListener(view -> {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("users").get().addOnCompleteListener(task -> {
                task.getResult().forEach(u->{
                    if(u.get("username").toString().equals(username)){
                        u.getReference().delete();
                        Toast.makeText(this, "Bye, bye! :(", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    }
                });
            });
        });

        //change email
        findViewById(R.id.btnChangeEmail).setOnClickListener(view -> {

            EditText etNewEmail = findViewById(R.id.newEmail);
            etNewEmail.setVisibility(View.VISIBLE);
            Button confirm = findViewById(R.id.confirmEmail);
            confirm.setVisibility(View.VISIBLE);

            confirm.setOnClickListener(view1 -> {
               String newEmail= etNewEmail.getText().toString().trim();
               if(!newEmail.isEmpty() && newEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
                   FirebaseFirestore db = FirebaseFirestore.getInstance();
                   db.collection("users").get().addOnCompleteListener(task -> {
                       task.getResult().forEach(u->{
                           if(u.get("username").toString().equals(username)){
                               u.getReference().update("email", newEmail);
                               Toast.makeText(this, "You successfully updated your email :)", Toast.LENGTH_SHORT).show();
                              sp.edit().putString("email", newEmail).apply();
                               startActivity(new Intent(this, ProfileActivity.class));
                               finish();

                           }
                       });
                   });
               }else {
                  findViewById(R.id.txtErrorNewEmail).setVisibility(View.VISIBLE);
               }

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

        if (id == R.id.myOrders) {
            startActivity(new Intent(this, MyOrdersActivity.class));

        } else if (id == R.id.signOut) {

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }
}
