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

public class Login extends AppCompatActivity implements View.OnClickListener {

    public static final String LOGIN_URL = "http://10.0.2.2:8084/ApiPedidos/usuario/validar";

    public static final String KEY_USERNAMEL = "usuario_usuario";
    public static final String KEY_PASSWORDL = "clave_usuario";

    private EditText mUsuario;
    private EditText mPassword;
    private Button mIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsuario = (EditText) findViewById(R.id.textUsuario);
        mPassword = (EditText) findViewById(R.id.textPassword);

        mIniciarSesion = (Button) findViewById(R.id.botonIniciarSesion);

        mIniciarSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.botonIniciarSesion:
                iniciarSesion();
                break;
        }
    }

    private void iniciarSesion() {
        final String usuario = mUsuario.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(
                //Parametro 1
                Request.Method.POST,
                //Parametro 2
                LOGIN_URL,
                //Parametro 3
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            OpenProfile();
                        } else {
                            Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                //Parametro 4
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "ERROR AL REGISTRARSE:"+ error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

        ) //directo se pone una llave despues de la funcion para sobreescribir el metodo de abajo,que un metodo de la funcion de arriba
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                //parametros a enviar al servidor
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put(KEY_USERNAMEL, usuario);
                parametros.put(KEY_PASSWORDL, password);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void OpenProfile() {
        Intent i = new Intent(this, menu.class);
       // i.putExtra("NOMBRE", mUsuario.getText().toString());
        startActivity(i);
        //Limpiamos los Editext's
        mUsuario.setText("");
        mPassword.setText("");
    };
}
