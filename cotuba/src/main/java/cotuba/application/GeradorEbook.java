package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;
import cotuba.epub.GeradorEPUB;
import cotuba.pdf.GeradorPDF;

import java.util.HashMap;
import java.util.Map;

public interface GeradorEbook {

    void gera(Ebook ebook);

    Map<String, GeradorEbook> GERADORES = new HashMap<String, GeradorEbook>() {{
        put("pdf", new GeradorPDF());
        put("epub", new GeradorEPUB());
    }};

    static GeradorEbook cria(FormatoEbook formato) {

        GeradorEbook gerador = GERADORES.get(formato.name().toLowerCase());

        if (gerador == null) {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }

        return gerador;
    }
}
