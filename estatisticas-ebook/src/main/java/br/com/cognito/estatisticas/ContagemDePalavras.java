package br.com.cognito.estatisticas;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ContagemDePalavras  {

    private Map<String , Integer> map = new TreeMap<>();

    void adicionaPalavras (String palavra) {

        Integer contagem = map.get(palavra);

        if (contagem != null) {
            contagem ++;
        } else {
            contagem = 1;
        }

        map.put(palavra , contagem);

    }

    Set<Map.Entry<String , Integer>> entrySet(){
        return map.entrySet();
    }
}
