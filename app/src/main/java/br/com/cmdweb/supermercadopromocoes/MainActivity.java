package br.com.cmdweb.supermercadopromocoes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressbar;

    List<Promocao> promocaoList;

    private LruCache<Integer, Bitmap> imgCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressbar = (ProgressBar) findViewById(R.id.progressBar);

        progressbar.setVisibility(View.INVISIBLE);

        promocaoList = new ArrayList();

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;

        imgCache = new LruCache<>(cacheSize);

        if (isOnline())
            buscarDados("http://cmdweb.com.br/promocoes.json");
        else
            Toast.makeText(this, "Rede não disponivel", Toast.LENGTH_LONG).show();

    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting())
            return true;
        return false;
    }


    protected void atualizarView() {

        ImageAdapter adapter = new ImageAdapter(this, R.layout.imagempromocao, promocaoList);
        ListView listViewProduto = (ListView) findViewById(R.id.listView);

        listViewProduto.setAdapter(adapter);
    }

    private void buscarDados(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    private class MyTask extends AsyncTask<String, String, List<Promocao>> {

        @Override
        protected void onPreExecute() {
            progressbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Promocao> doInBackground(String... params) {
            String conteudo = HttpManager.getDados(params[0]);
            promocaoList = PromocaoJsonParser.parse(conteudo);

            return promocaoList;
        }

        @Override
        protected void onPostExecute(List<Promocao> s) {
            atualizarView();
            progressbar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //atualizarView(values[0]);
        }
    }
}