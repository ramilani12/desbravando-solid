package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

import java.util.List;

public class Cotuba {

    public void executa(ParametrosCotuba parametros) {

        var formato = parametros.getFormato();
        var diretorioDosMD = parametros.getDiretorioDosMD();
        var arquivoDeSaida = parametros.getArquivoDeSaida();

        var renderizador = RenderizadorMDParaHTML.cria();

        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

        var ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);

        if ("pdf".equals(formato)) {

            var geradorPDF = GeradorPDF.cria();
            geradorPDF.gera(ebook);

        } else if ("epub".equals(formato)) {

            var geradorEPUB = GeradorEPUB.cria();
            geradorEPUB.gera(ebook);

        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }
    }
}
