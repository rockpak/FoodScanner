package com.example.foodscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText cName, cEmail, cPass,cPhone, cRepeatPass;
    private Button submit, goback;
    private FirebaseDatabase database;
    private DatabaseReference mRef;
    private static final String TAG = "NewUserActivity";
    private User user;
    private static final Pattern PHONE_PATTERN = Pattern.compile("(0)?[8][0-9]{8}");

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();
        goback = (Button) findViewById(R.id.goBack);

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // button will take to login Activity
                Intent i2 = new Intent(Registration.this, MainActivity.class);
                startActivity(i2);

            }
        });
        submit = (Button) findViewById(R.id.new_Reg);
        cName = (EditText) findViewById(R.id.eName);
        cPhone = (EditText) findViewById(R.id.Phone);
        cEmail = (EditText) findViewById(R.id.Email);
        cPass = (EditText) findViewById(R.id.ePassword);
        cRepeatPass = (EditText) findViewById(R.id.rPassword);


        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("User");

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
            finish();
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Storing user info into variables
                String name = cName.getText().toString();
                String phone = cPhone.getText().toString();
                String email = cEmail.getText().toString();
                String password = cPass.getText().toString();

                user = new User(email, name, phone, password);
                registerUser(email, password);
            }
        });

        cEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (validateEmail(cEmail.getText().toString()))
                {
                    submit.setEnabled(true);
                }
                else {
                    submit.setEnabled(false);
                    cEmail.setError("Invalid email address.");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        cPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (validateMobile(cPhone.getText().toString()))
                {
                    submit.setEnabled(true);
                }
                else {
                    submit.setEnabled(false);
                    cPhone.setError("Not a valid Irish number.");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        cPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (validatePass(cPass.getText().toString()))
                {
                    submit.setEnabled(true);
                }
                else {
                    submit.setEnabled(false);
                    cPass.setError("password should be minimum 8 charectures, mixture of upper and lower case letters, digits and special characters.");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        /*
        cRepeatPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (validatePass2(cRepeatPass.getText().toString()))
                {
                    submit.setEnabled(true);
                }
                else {
                    submit.setEnabled(false);
                    cRepeatPass.setError("Re entered password does not match, please try again.");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

         */

    }
    private boolean validateName(){
        String nameInput = cName.getText().toString().trim();

        if(nameInput.isEmpty()){     // if name field is empty, show error
            cName.setError("Name is required");
            return false;
        }
        else if(nameInput.length()<3) {  // if name length is less than 3
            cName.setError("Name is too short");
            return false;
        }
        else if(nameInput.length()>20){  // if name length is too long
            cName.setError("Name is too long");
            return false;
        }
        else{
            cName.setError(null);
            return true;
        }
    }

    private boolean validatePhone(){    // checkig validate phone(only irish phone)
        String phoneInput = cPhone.getText().toString().trim();

        if(phoneInput.isEmpty()){  // if phone field is empty
            cPhone.setError("Phone is required");
            return false;
        }
        else if(phoneInput.length()!=10){
            cPhone.setError("Phone is not valid");
            return false;
        }
        else if(!PHONE_PATTERN.matcher(phoneInput).matches()){
            cPhone.setError("Please enter an Irish valid phone number");
            return false;
        }
        else{
            cPhone.setError(null);
            return true;
        }
    }


    private boolean validateEmail(){     //checking validate email
        String emailInput = cEmail.getText().toString().trim();

        if(emailInput.isEmpty()){    // if email field is empty
            cEmail.setError("E-mail is required");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            cEmail.setError("Please enter a valid e-mail address");
            return false;
        }
        else{
            cEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        /*
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
        */
        String passInput = cPass.getText().toString().trim();

        if(passInput.isEmpty()){  // if password field is empty
            cPass.setError("Password is required");
            return false;
        }

        else if(passInput.length()<8){  // if password length is too less than 8
            cPass.setError("Password should contain at least one uppercase, one lowercase, one numeric and more than 8 digits");
            return false;
        }



        else {
            cPass.setError(null);
            return true;
        }

    }

    private boolean validateRepeatPass(){   // checking the repeat password
        String repeatPassInput = cRepeatPass.getText().toString().trim();
        String pass = cPass.getText().toString().trim();

        if(repeatPassInput.isEmpty()){
            cRepeatPass.setError("Confirm password is required");
            return false;
        }
        else if(!repeatPassInput.equals(pass)){
            cRepeatPass.setError("Passwords do not match");
            return false;
        }
        else{
            cRepeatPass.setError(null);
            return true;
        }
    }
    private void registerUser(String email, String password){
        //Check if info provided is valid
        if(!validateName() | !validatePhone() | !validateEmail() | !validatePassword() | !validateRepeatPass()){
            return;
        }

        //Authenticating user
        else{
            //creating user
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //Sign in successful, update UI with the signed-in user's info
                        Log.d(TAG, "Registration successful");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    }
                    else{
                        //If fails, display message to the user
                        Log.w(TAG, "Authentication failed", task.getException());
                        Toast.makeText(Registration.this, "Authentication failed", Toast.LENGTH_LONG).show();

                    }
                }

            });

        }
    }


    private void updateUI(FirebaseUser currentUser){
        String uid = currentUser.getUid();
        mRef.child(uid).setValue(user);
        startActivity(new Intent(getApplicationContext(),Dashboard.class));  // if user registered, start home activity
    }


    boolean validateEmail (String inputE){
        Pattern p = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]{3,}+");
        Matcher m = p.matcher(inputE);
        return m.matches();
    }
    boolean validateMobile (String input){
        Pattern p = Pattern.compile("(0)?[8][35679][0-9]{7}");
        Matcher m = p.matcher(input);
        return m.matches();
    }
    boolean validatePass (String inputP){
        Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$€£%\"^+=<>-])(?=\\S+$).{8,}$");
        Matcher m = p.matcher(inputP);
        return m.matches();
    }
    /*
    boolean validatePass2 (String inputP2){
        Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$€£%\"^+=<>-])(?=\\S+$).{8,}$");
        Matcher m = p.matcher(inputP2);
        return m.matches();
    }

     */


}

