package br.com.cmdweb.supermercadopromocoes;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gabriel.malaquias on 30/08/2017.
 */

public class ImageAdapter  extends ArrayAdapter<Promocao> {

    Context context;
    int layoutResource;
    List<Promocao> dados;

    public ImageAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Promocao> dados) {
        super(context, resource, dados);

        this.context = context;
        this.layoutResource = resource;
        this.dados = dados;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(layoutResource, parent, false);
        }

        ImageView foto = (ImageView) view.findViewById(R.id.img_promocao);

        Promocao promocao = dados.get(position);

        foto.setImageBitmap(promocao.getImagem());

        return view;
    }
}
