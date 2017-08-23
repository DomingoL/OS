package com.example.domingolezcano.volley;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DomingoLezcano on 28/6/17.
 */
public class Conexion {
    private static String postDatos(String urlUsuario, String parametrosusuario){
        URL url;
        HttpURLConnection connection = null;

        try {
            url = new URL(urlUsuario);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametrosusuario.getBytes().length));
            connection.setRequestProperty("Content-Lenguaje", "pt-ES");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes(parametrosusuario);
            dataOutputStream.flush();
            dataOutputStream.close();

            InputStream inputStream = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String linea;
            StringBuffer respuesta = new StringBuffer();
                while ((linea = bufferedReader.readLine()) != null){
                    respuesta.append(linea);
                    respuesta.append('\r');

                }
            bufferedReader.close();

            return respuesta.toString();

        } catch (Exception error){
            return  null;

        } finally {
            if(connection != null){
                connection.disconnect();
            }
        }


    }
}
