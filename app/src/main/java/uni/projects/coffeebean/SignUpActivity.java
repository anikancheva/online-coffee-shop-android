package uni.projects.coffeebean;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class SignUpActivity extends AppCompatActivity {

    TextView errorTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById(R.id.btnSignUpData).setOnClickListener(view -> {

            EditText etUsr = findViewById(R.id.inputUsr);
            EditText etEmail = findViewById(R.id.inputEmail);
            EditText etPass = findViewById(R.id.inputPass);
            EditText etConfPass = findViewById(R.id.inputConfPass);
            errorTxt = findViewById(R.id.txtErrorSignUp);

            String usrName = etUsr.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPass.getText().toString().trim();
            String confPassword = etConfPass.getText().toString().trim();


            if (validateInput(usrName, email, password, confPassword)) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                User userToReg = new User(usrName, email, password);

                db.collection("users").get().addOnCompleteListener(task -> {
                    boolean usrExists = false;
                    QuerySnapshot result = task.getResult();
                    for (QueryDocumentSnapshot user : result) {
                        if (user.get("username").equals(usrName)) {
                            usrExists = true;
                            break;
                        }
                    }

                    if (usrExists) {
                        errorTxt.setVisibility(View.VISIBLE);
                        errorTxt.setText("Username already exists");
                        etPass.setText("");
                        etConfPass.setText("");
                    } else {
                        findViewById(R.id.pbSignUp).setVisibility(View.VISIBLE);
                        db.collection("users").add(userToReg)
                                .addOnSuccessListener(documentReference -> {
                                    SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                                    sp.edit().putString("user", usrName).apply();
                                    startActivity(new Intent(this, OrderActivity.class));
                                    finish();
                                })
                                .addOnFailureListener(e -> Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show());
                    }

                });
            }

        });

        findViewById(R.id.btnGoBack).setOnClickListener(view -> finish());

    }

    protected boolean validateInput(String usr, String email, String password, String confPassword) {

        if (usr.isEmpty() || email.isEmpty() || password.isEmpty() || confPassword.isEmpty()) {
            errorTxt.setVisibility(View.VISIBLE);
            errorTxt.setText("Fields can't be empty");
            return false;
        } else if (!password.equals(confPassword)) {
            errorTxt.setVisibility(View.VISIBLE);
            errorTxt.setText("Passwords don't match");
            return false;
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errorTxt.setVisibility(View.VISIBLE);
            errorTxt.setText("Invalid email");
            return false;
        }
        return true;
    }
}
