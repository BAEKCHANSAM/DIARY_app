package com.example.ins_copy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final  String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;
    TextView TextClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ActionBar actionBar = getSupportActionBar();actionBar.hide();//위에 액션바를 없애주는 코드

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.loginButton).setOnClickListener(onClickListener );
        findViewById(R.id.TextClick).setOnClickListener(onClickListener);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.loginButton:
                     signUp();
                    break;

                case R.id.TextClick:
                    Intent intent = new Intent(getApplicationContext(),Sign_Up.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    private void signUp() {

        String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();

        if(email.length() > 0 && password.length() > 0){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("로그인에 성공했습니다.");
                                Intent intent = new Intent(getApplicationContext(),MainPage.class);
                                startActivity(intent);
                            } else {
                                if(task.getException() != null){
                                    startToast(task.getException().toString());
                                }
                            }
                        }
                    });
        }else{
            startToast("이메일 또는 비밀번호를 입력해 주세요.");

        }
    }
    private  void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

