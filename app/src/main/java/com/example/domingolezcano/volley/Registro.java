package com.example.domingolezcano.volley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    public static final String REGISTRO_URL = "http://android.enterprisesolutions.com.py/volleyRegister.php";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";

    private EditText mUsuario;
    private EditText mEmail;
    private EditText mPassword;

    private Button mRegistrarse;
    private Button mIniciarSesion;
    private Button mMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mUsuario = (EditText) findViewById(R.id.textUsuario);
        mEmail = (EditText) findViewById(R.id.textEmail);
        mPassword = (EditText) findViewById(R.id.textPassword);


        mRegistrarse = (Button) findViewById(R.id.botonRegistrarse);
        mIniciarSesion = (Button) findViewById(R.id.botonIniciarSesion);
        mMenu = (Button) findViewById(R.id.botonMenuu);

        mRegistrarse.setOnClickListener(this);
        mIniciarSesion.setOnClickListener(this);
        mMenu.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.botonIniciarSesion:
                Intent i = new Intent(this, Login.class);
                startActivity(i);
            case R.id.botonRegistrarse:
                registrarse();
                break;
            case R.id.botonMenuu:
                Intent ie = new Intent(this, menu.class);
                startActivity(ie);
                break;
        }

    }

    private void registrarse() {
        final String usuario = mUsuario.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        final String email = mEmail.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(
                //Parametro 1
                Request.Method.POST,
                //Parametro 2
                REGISTRO_URL,
                //Parametro 3
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Repuesta", "repuesta positiva: " + response);
                        Toast.makeText(Registro.this, "Registro: " + response, Toast.LENGTH_SHORT).show();

                        //Limpiamos los Editext's
                        mUsuario.setText("");
                        mEmail.setText("");
                        mPassword.setText("");
                    }
                },
                //Parametro 4
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Repuesta", "repuesta error: ");
                        Toast.makeText(Registro.this, "Error al registrarse:", Toast.LENGTH_SHORT).show();
                    }
                }

        ) //directo se pone una llave despues de la funcion para sobreescribir el metodo de abajo,que un metodo de la funcion de arriba
        {
            protected Map<String, String> getParams() throws AuthFailureError{
                //parametros a enviar al servidor
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put(KEY_USERNAME, usuario);
                parametros.put(KEY_EMAIL, email);
                parametros.put(KEY_PASSWORD, password);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
