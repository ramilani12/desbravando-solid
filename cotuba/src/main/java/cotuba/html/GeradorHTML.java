package cotuba.html;

import cotuba.application.GeradorEbook;
import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;

public class GeradorHTML implements GeradorEbook {
    @Override
    public void gera(Ebook ebook) {

        Path arquivoDeSaida = ebook.getArquivoDeSaida();

        try{

            Path diretorioDOHTML = Files.createDirectory(arquivoDeSaida);
            int i = 1;

            for (Capitulo capitulo : ebook.getCapitulos()) {

                String nomeDoArquivoHTMLDoCapitulo = obtemNomeDoArquivoHTML(i , capitulo);

                Path arquivoHTMLDoCaptiulo = diretorioDOHTML.resolve(nomeDoArquivoHTMLDoCapitulo);

                String html = """
                        <!DOCTYPE html>
                        <html lang="pt-BR">
                        <head>
                        <meta charset="UTF-8">
                        <title>%s</title>
                        </head>
                        <body>
                            %s
                        </body>
                        </html>
                        """.formatted(capitulo.getTitulo(), capitulo.getConteudoHTML());

                Files.writeString(arquivoHTMLDoCaptiulo , html , StandardCharsets.UTF_8);

                i++;
            }

        } catch (IOException ex) {
            throw new IllegalStateException("Erro ao criar o HTML: " + arquivoDeSaida.toAbsolutePath(), ex);
        }

    }

    private String obtemNomeDoArquivoHTML(int i, Capitulo capitulo) {

        String nomeArquivoHTMLCapitulo = i + "-" +
                removeAcentos(capitulo.getTitulo().toLowerCase()).replace("[ˆ\\w]" , "") + ".html";

        return nomeArquivoHTMLCapitulo;

    }

    private String removeAcentos(String texto) {

        return Normalizer.normalize(texto , Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
