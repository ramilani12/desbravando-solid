package br.com.cognito.estatisticas;

import cotuba.plugin.AoFinalizarGeracao;
import cotuba.plugin.EbookSoParaLeitura;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;
import java.util.Map;

public class CalculadoraDeEstatisticas implements AoFinalizarGeracao {


    @Override
    public void aposGeracao(EbookSoParaLeitura ebook) {

        var contagemDePalavras = new ContagemDePalavras();

        for (var capitulo : ebook.getCapitulos()) {

            String html = capitulo.getConteudoHTML();

            Document document = Jsoup.parse(html);

            String textoDoCapitulo = document.body().text();

            String textoDoCapituloSemPontuacao = textoDoCapitulo.replaceAll("\\p{Punct}", " ");

            String textoDoCapituloSemAcentos = Normalizer.normalize(textoDoCapituloSemPontuacao, Normalizer.Form.NFD).replaceAll("[ˆ\\p{ASCII}]", "");

            String[] palavras = textoDoCapituloSemAcentos.split("\\s+");

            for (var palavra : palavras) {

                String emMaisculas = palavra.toUpperCase();

                contagemDePalavras.adicionaPalavras(emMaisculas);

            }

            for (Map.Entry<String, Integer> contagem : contagemDePalavras.entrySet()) {
                String palavra = contagem.getKey();
                Integer oncorrencias = contagem.getValue();
                System.out.println(palavra + ": " + oncorrencias);
            }
        }

    }
}
