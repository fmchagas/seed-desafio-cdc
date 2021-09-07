package br.com.fmchagas.desafiocdc.autor;

public class ResponseRequest {
    private String nome;
    private String descricao;

    public ResponseRequest(Autor autor) {
        nome = autor.getNome();
        descricao = autor.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    } 
}
