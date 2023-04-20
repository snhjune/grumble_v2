package com.example.foodapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
    //initialize local variables
    EditText inputEmail, inputPassword, inputConfirmPassword,inputFullName;
    Button btnRegister;
    //email pattern; this will be used on registration function
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    //initialize the firebase auth library
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //localize the xml to Registration activity by assigned ID
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        inputEmail = findViewById(R.id.inputEmail);
        inputFullName = findViewById(R.id.inputFullName);
        inputPassword = findViewById(R.id.inputPassword);
        progressDialog = new ProgressDialog(this);
        btnRegister = findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                performAuth();
            }
        });
    }

    /**
     * The function validates user's input of email and possword.
     * if both email and password input have no issue, email and password will add to our database
     */
    private void performAuth(){
        //local variables
        String fullName = inputFullName.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();
        //Validate email pattern
        if(!email.matches(emailPattern)){
            inputEmail.setError("Enter Correct Email");

        } else if(fullName.isEmpty()) {
              inputFullName.setError("Full name is required");
              inputFullName.requestFocus();
        }
        //Validate passwords
        else if(password.isEmpty()||password.length()<4){
            inputPassword.setError("Enter Proper password");
        } else if (!password.equals(confirmPassword)){
            inputConfirmPassword.setError("Password is not matched");
        } else {
            //Display log for registration process
            progressDialog.setMessage("Please wait while registration process...");
            progressDialog.setTitle("");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //display log if registration is successful
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(RegistrationActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    /**
     * if registeration complete, access the app to main page of the app.
     */
    private void sendUserToNextActivity() {
        Intent intent= new Intent(RegistrationActivity.this, HomeFragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    /**
     * once user complete the registration, switch it to login
     * @param view
     */
    public void login(View view) {
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }
}