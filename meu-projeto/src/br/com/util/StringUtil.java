package br.com.util;

public class StringUtil {
    public static String putMascara(String valorSemMascara, String tipoMascara) {
        String valorComMascara = "";
        int j = 0;
        for (int i = 0; i < tipoMascara.length(); i++) {
            if (tipoMascara.charAt(i) == '#') {
                valorComMascara += valorSemMascara.charAt(j);
                j++;
            } else {
                valorComMascara += tipoMascara.charAt(i);
            }
        }
        return valorComMascara;
    }

    public static String retirarMascara(String valorComMascara) {
        return valorComMascara.replaceAll("\\D*", "");
    }
}
