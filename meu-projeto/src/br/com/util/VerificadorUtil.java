package br.com.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class VerificadorUtil {
    public VerificadorUtil() {
    }

    public static boolean estaNulo(Object objeto) {
        return objeto == null;
    }

    public static boolean estaVazio(Object objeto) {
        return objeto instanceof Collection ? ((Collection) objeto).isEmpty() : StringUtils.isEmpty(objeto.toString());
    }

    public static boolean naoEstaNulo(Object objeto) {
        return !estaNulo(objeto);
    }

    public static boolean naoEstaVazio(Object objeto) {
        return !estaVazio(objeto);
    }

    public static boolean estaNuloOuVazio(Object valor) {
        return estaNulo(valor) || estaVazio(valor);
    }

    public static boolean naoEstaNuloOuVazio(Object objeto) {
        return naoEstaNulo(objeto) && naoEstaVazio(objeto);
    }
}
