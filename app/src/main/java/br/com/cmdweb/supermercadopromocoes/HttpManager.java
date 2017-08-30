package br.com.cmdweb.supermercadopromocoes;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by gabriel.malaquias on 30/08/2017.
 */


public class HttpManager {

    public static String getDados(String uri){
        BufferedReader reader = null;

        try{
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;

            while((line = reader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            Log.i("JSON", stringBuilder.toString());
            return stringBuilder.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
