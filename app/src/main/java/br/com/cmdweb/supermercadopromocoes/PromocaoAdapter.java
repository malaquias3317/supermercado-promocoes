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

public class PromocaoAdapter extends ArrayAdapter<Promocao> {

    Context context;
    int layoutResource;
    List<Promocao> dados;

    public PromocaoAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Promocao> dados) {
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

        ImageView foto = (ImageView) view.findViewById(R.id.img_produto);
        Promocao promocao = dados.get(position);
        foto.setImageBitmap(promocao.getImagem());

        TextView txtDescricao = (TextView)view.findViewById(R.id.txt_descricao);
        txtDescricao.setText(promocao.getDescricao());

        TextView txtPrecoAntigo = (TextView) view.findViewById(R.id.txt_precoAntigo);
        txtPrecoAntigo.setText(promocao.getPrecoAntigo());

        TextView txtPreco = (TextView) view.findViewById(R.id.txt_preco);
        txtPreco.setText(promocao.getPreco());

        TextView txtValidade = (TextView) view.findViewById(R.id.txt_validade);
        txtValidade.setText(promocao.getValidade());

        return view;
    }
}
