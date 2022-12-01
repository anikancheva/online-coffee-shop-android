package uni.projects.coffeebean;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById(R.id.btnSignUpData).setOnClickListener(view -> {

            EditText etUsr=findViewById(R.id.inputUsrname);
            EditText etEmail=findViewById(R.id.inputEmail);
            EditText etPass=findViewById(R.id.inputPass);
            EditText etConfPass=findViewById(R.id.inputConfPass);


            String usr=etUsr.getText().toString();
            String email=etEmail.getText().toString();
            String password=etPass.getText().toString();
            String confPassword=etConfPass.getText().toString();



        });

        findViewById(R.id.btnGoBack).setOnClickListener(view -> {
            finish();
        });

    }
}
