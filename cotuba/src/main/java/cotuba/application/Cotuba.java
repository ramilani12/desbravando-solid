package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUB;
import cotuba.md.RenderizadorMDParaHTMLComCommonMark;
import cotuba.pdf.GeradorPDF;

import java.util.List;

public class Cotuba {

    RenderizadorMDParaHTML renderizador;

    GeradorEbook gerador;

    public void executa(ParametrosCotuba parametros) {

        renderizador = new RenderizadorMDParaHTMLComCommonMark();

        var formato = parametros.getFormato();
        var diretorioDosMD = parametros.getDiretorioDosMD();
        var arquivoDeSaida = parametros.getArquivoDeSaida();

        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

        var ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);

        if ("pdf".equals(formato)) {
            gerador = new GeradorPDF();
        } else if ("epub".equals(formato)) {
            gerador = new GeradorEPUB();
        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }

        gerador.gera(ebook);

    }
}
