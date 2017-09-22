package br.com.cmdweb.supermercadopromocoes;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                exibePromocoes();
            }
        }, 2000);
    }

    private void exibePromocoes(){
        Intent intent = new Intent(Splash_screen.this,
                MainActivity.class);
        startActivity(intent);
        finish();
    }
}
