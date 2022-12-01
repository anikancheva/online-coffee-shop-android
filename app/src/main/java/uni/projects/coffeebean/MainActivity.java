package uni.projects.coffeebean;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TODO fix activity navigation for signin
        findViewById(R.id.btnSignIn).setOnClickListener(view -> startActivity(new Intent(this, OrderActivity.class)));

        findViewById(R.id.btnSignUp).setOnClickListener(view -> startActivity(new Intent(this, SignUpActivity.class)));


    }
}