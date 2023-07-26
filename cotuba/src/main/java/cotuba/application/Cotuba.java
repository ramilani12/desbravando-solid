package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Cotuba {

    private final GeradorEPUB geradorPDF;
    private final GeradorEPUB geradorEPUB;
    private final RenderizadorMDParaHTML renderizador;

    public Cotuba(GeradorEPUB geradorPDF, GeradorEPUB geradorEPUB, RenderizadorMDParaHTML renderizador) {
        this.geradorPDF = geradorPDF;
        this.geradorEPUB = geradorEPUB;
        this.renderizador = renderizador;
    }

    public void executa(ParametrosCotuba parametros) {

        var formato = parametros.getFormato();
        var diretorioDosMD = parametros.getDiretorioDosMD();
        var arquivoDeSaida = parametros.getArquivoDeSaida();

        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

        var ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);

        if ("pdf".equals(formato)) {
            geradorPDF.gera(ebook);

        } else if ("epub".equals(formato)) {
            geradorEPUB.gera(ebook);

        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }
    }
}
