package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;
import cotuba.md.RenderizadorMDParaHTML;
import cotuba.plugin.Plugin;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Cotuba {

    private final RenderizadorMDParaHTML renderizador;

    private final List<GeradorEbook> geradoresEbook;

    public Cotuba(RenderizadorMDParaHTML renderizador, List<GeradorEbook> geradoresEbook) {
        this.renderizador = renderizador;
        this.geradoresEbook = geradoresEbook;
    }

    public void executa(ParametrosCotuba parametros) {

        FormatoEbook formato = parametros.getFormato();

        var diretorioDosMD = parametros.getDiretorioDosMD();
        var arquivoDeSaida = parametros.getArquivoDeSaida();

        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

        var ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);

       GeradorEbook geradorEbook = geradoresEbook.stream().filter(gerador -> gerador.accept(formato)).findAny()
               .orElseThrow(() -> new IllegalArgumentException("Formato ebook inválido: "+ formato));


       geradorEbook.gera(ebook);

        Plugin.gerou(ebook);

    }
}
