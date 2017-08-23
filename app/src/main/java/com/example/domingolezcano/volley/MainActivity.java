package com.example.domingolezcano.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mtextBienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtextBienvenida = (TextView) findViewById(R.id.textBienvenida);

        Bundle extras = getIntent().getExtras();
        if (extras == null){
            return;
        }
        String NOMBRE = extras.getString("NOMBRE");

        mtextBienvenida.setText("BIENVENIDO: " + NOMBRE +"!!!");

    }
}
