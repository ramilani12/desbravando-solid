package cotuba.domain;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public final class Ebook {

    private final Path arquivoDeSaida;

    private final List<Capitulo> capitulos;

    private final FormatoEbook formato;

    public Ebook(Path arquivoDeSaida, List<Capitulo> capitulos, FormatoEbook formato) {
        this.arquivoDeSaida = arquivoDeSaida;
        this.capitulos = Collections.unmodifiableList(capitulos);
        this.formato = formato;
    }

    public Path getArquivoDeSaida() {
        return arquivoDeSaida;
    }


    public List<Capitulo> getCapitulos() {
        return capitulos;
    }


    public FormatoEbook getFormato() {
        return formato;
    }


    public boolean isUltimoCapitulo(Capitulo capitulo) {
        return this.capitulos.get(this.capitulos.size() - 1).equals(capitulo);
    }
}
