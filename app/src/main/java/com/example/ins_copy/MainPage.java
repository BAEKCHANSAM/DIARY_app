package com.example.ins_copy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            Intent intent = new Intent(getApplicationContext(),Sign_Up.class);
            startActivity(intent);
        }
        findViewById(R.id.logoutButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.logoutButton:
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                break;
            }
        }
    };
}
