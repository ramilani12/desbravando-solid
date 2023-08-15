package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;
import cotuba.md.RenderizadorMDParaHTML;

import java.util.List;

public class Cotuba {

    GeradorEbook gerador;

    public void executa(ParametrosCotuba parametros) {

        var renderizador = new RenderizadorMDParaHTML();

        FormatoEbook formato = parametros.getFormato();

        var diretorioDosMD = parametros.getDiretorioDosMD();
        var arquivoDeSaida = parametros.getArquivoDeSaida();

        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

        var ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);

        var gerador = GeradorEbook.cria(formato);

        gerador.gera(ebook);

    }
}
