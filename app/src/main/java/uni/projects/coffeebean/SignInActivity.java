package uni.projects.coffeebean;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        findViewById(R.id.btnSignInData).setOnClickListener(view -> {

            EditText etUsrOrEmail=findViewById(R.id.inputUsrnameOrEmail);
            EditText etPass=findViewById(R.id.inputPassword);
            String usrOrEmail=etUsrOrEmail.getText().toString();
            String password=etPass.getText().toString();

        });

        findViewById(R.id.btnGoBack).setOnClickListener(view -> {
            finish();
        });
    }
}
