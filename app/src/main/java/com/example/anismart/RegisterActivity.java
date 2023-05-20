package com.example.anismart;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity {

    EditText EmailInput;
    EditText passwordInput;
    EditText usernameInput;
    private Button register_button;
    private static int SIGN_IN_CODE = 1;
    private static final String TAG = "EmailPassword";
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EmailInput = findViewById(R.id.email_register);
        passwordInput = findViewById(R.id.password_register);
        usernameInput = findViewById(R.id.username);
        register_button = (Button) findViewById(R.id.register_end_button);
        loadingBar = new ProgressDialog(this);



        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = EmailInput.getText().toString();
                String password = passwordInput.getText().toString();
                String username = usernameInput.getText().toString();
                if(password.length()<6){Toast.makeText(RegisterActivity.this, "Password may have 6 or more symbols.", Toast.LENGTH_SHORT).show();}
                else{ createAccount(Email, password, username);}

            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    private void createAccount(String email, String password, String username) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        loadingBar.setTitle("Создание аккаунта");
        loadingBar.setMessage("Пожалуйста подождите...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            loadingBar.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();

// Creating DataBase for new user

                            final DatabaseReference RootRef;
                            RootRef = FirebaseDatabase.getInstance().getReference();

                            RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(!(snapshot.child("Users").child(mAuth.getCurrentUser().getUid()).exists()))
                                    {
                                        HashMap<String, Object> userDataMap = new HashMap<>();
                                        userDataMap.put("username", username);
                                        userDataMap.put("email", email);

                                        RootRef.child("Users").child(mAuth.getCurrentUser().getUid()).updateChildren(userDataMap)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){
                                                            Toast.makeText(RegisterActivity.this, "База данных обновилась!", Toast.LENGTH_SHORT ).show();
                                                        } else {
                                                            Toast.makeText(RegisterActivity.this, "Не удалось обновить базу данных!", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    }
                                    else
                                    {
                                        Toast.makeText(RegisterActivity.this, "Пользователь с таким именем уже существует", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(i);
                                    }
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            loadingBar.dismiss();
                            Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                        }


                    }
                });

        Intent i = new Intent(RegisterActivity.this,MainActivity.class);
        i.putExtra("mail",EmailInput.getText().toString());
        i.putExtra("password",passwordInput.getText().toString());
        startActivity(i);

    }

    private boolean validateForm() {
        boolean valid = true;

        String email = EmailInput.getText().toString();
        if (TextUtils.isEmpty(email)) {
            EmailInput.setError("Required.");
            valid = false;
        } else {
            EmailInput.setError(null);
        }

        String password = passwordInput.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordInput.setError("Required.");
            valid = false;
        } else {
            passwordInput.setError(null);
        }

        return valid;
    }






}