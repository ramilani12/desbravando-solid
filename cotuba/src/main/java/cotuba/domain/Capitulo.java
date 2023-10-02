package cotuba.domain;

import java.io.Serializable;

public final class Capitulo implements Serializable {

    private final String titulo;

    private final String conteudoHTML;

    public Capitulo(String titulo, String conteudoHTML) {
        this.titulo = titulo;
        this.conteudoHTML = conteudoHTML;
    }

    public String getTitulo() {
        return titulo;
    }


    public String getConteudoHTML() {
        return conteudoHTML;
    }


}
