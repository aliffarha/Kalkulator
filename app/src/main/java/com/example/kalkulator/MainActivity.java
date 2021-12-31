package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.mbms.StreamingServiceInfo;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editusername;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editusername = findViewById(R.id.editusername);
        pref = this.getSharedPreferences(getString(R.string.shared_key), Context.MODE_PRIVATE);
    checklogin();
    }

    public void checklogin(){
        String username = pref.getString(getString(R.string.username_key),"");
        if(username.length() > 3){
            toMain();

        }
    }
    public void toMain(){
        Intent it = new Intent(this, mykalkulator.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(it);
    }



    public void Pindah(View view) {

        String username = editusername.getText().toString();
        if(username.length() > 3) {
            pref.edit().putString(getString(R.string.username_key), username).apply();
            toMain();
        }else
        Toast.makeText(this,"Username harus lebih dari 3 huruf",Toast.LENGTH_LONG).show();
    }
}