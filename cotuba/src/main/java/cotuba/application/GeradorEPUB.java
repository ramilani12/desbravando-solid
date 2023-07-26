package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUBEpublib;

public interface GeradorEPUB {

    void gera(Ebook ebook);

    static GeradorEPUB cria() {
        return new GeradorEPUBEpublib();
    }
}
