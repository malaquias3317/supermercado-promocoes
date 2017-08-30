package br.com.cmdweb.supermercadopromocoes;

import android.graphics.Bitmap;

/**
 * Created by gabriel.malaquias on 30/08/2017.
 */

public class Promocao {

    private int id;
    private String nome;
    private String descricao;
    private String preco;
    private String validade;
    private Bitmap imagem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }
}
