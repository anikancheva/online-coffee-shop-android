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

public class SignInActivity extends AppCompatActivity {

    TextView errorTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        findViewById(R.id.btnSignInData).setOnClickListener(view -> {

            EditText etUsr = findViewById(R.id.inputUsr);
            EditText etPass = findViewById(R.id.inputPassword);
            errorTxt = findViewById(R.id.txtErrorSignIn);
            String usr = etUsr.getText().toString();
            String password = etPass.getText().toString();
            SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);

            if (validateInput(usr, password)) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("users").get().addOnCompleteListener(task -> {
                    boolean validUsr = false;
                    QuerySnapshot result = task.getResult();
                    for (QueryDocumentSnapshot user : result) {
                        if (user.get("username").equals(usr)) {
                            if (user.get("password").equals(password)) {
                                validUsr = true;
                                sp.edit().putString("username", usr).apply();
                                sp.edit().putString("email", user.get("email").toString()).apply();
                            }
                            break;
                        }
                    }

                    if (!validUsr) {
                        errorTxt.setVisibility(View.VISIBLE);
                        errorTxt.setText("Username or password don't match");
                        etPass.setText("");
                    } else {
                        findViewById(R.id.pbSignIn).setVisibility(View.VISIBLE);

                        startActivity(new Intent(this, OrderActivity.class));
                        finish();
                    }

                });
            }

        });

        findViewById(R.id.btnGoBack).setOnClickListener(view -> {
            finish();
        });
    }

    protected boolean validateInput(String usr, String password) {

        if (usr.isEmpty() || password.isEmpty()) {
            errorTxt.setVisibility(View.VISIBLE);
            errorTxt.setText("Fields can't be empty");
            return false;
        }
        return true;
    }
}
