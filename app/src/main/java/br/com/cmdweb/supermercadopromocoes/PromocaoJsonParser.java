package br.com.cmdweb.supermercadopromocoes;

import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabriel.malaquias on 30/08/2017.
 */

public class PromocaoJsonParser {
    public static List<Promocao> parse(String context){
        try{
            JSONArray jsonArray = new JSONArray(context);
            List<Promocao> list = new ArrayList<>();

            for (int i = 0; i<jsonArray.length();i++){
                Log.i("PARSER", "parser do objeto: " + i);
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Promocao promocao = new Promocao();

                promocao.setId(jsonObject.getInt("id"));
                promocao.setDescricao(jsonObject.getString("descricao"));
                promocao.setValidade("Validade: " + jsonObject.getString("validade"));
                promocao.setPreco("por " + jsonObject.getString("preco"));
                promocao.setPrecoAntigo("de " + jsonObject.getString("precoAntigo"));

                try{
                    String imageUrl = jsonObject.getString("imagem");
                    InputStream inputStream = (InputStream) new URL(imageUrl).getContent();
                    promocao.setImagem(BitmapFactory.decodeStream(inputStream));
                    Log.i("IMAGEM", "Download da imagem: " + imageUrl);
                    inputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

                list.add(promocao);
            }

            return list;

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
