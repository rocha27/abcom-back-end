package br.gov.mapa.abcom.infra.util;

import java.util.Collection;
import java.util.Map;

/**
 * FiltroClasse com métodos utilitários para trabalhar com Objetos
 */
public class ObjetoUtil {

    private ObjetoUtil() {
    }

    /**
     * Verifica se o objeto informado é nulo
     */
    public static boolean isNull(Object obj) {
        return isAnyNull(obj);
    }

    /**
     * Verifica se o objeto informado NÃO é nulo
     */
    public static boolean isNonNull(Object obj) {
        return isAnyNonNull(obj);
    }

    /**
     * Verifica se algum dos parametros informados é nulo ou vazio
     */
    public static boolean isAnyNull(Object... objs) {
        boolean isAnyNull = false;
        if (objs != null) {
            for (Object obj : objs) {
                if (isEmpty(obj)) {
                    isAnyNull = true;
                    break;
                }
            }
        }
        return isAnyNull;
    }

    /**
     * Verifica se algum dos parametros informados NÃO é nulo ou vazio
     */
    public static boolean isAnyNonNull(Object... objs) {
        boolean isAnyNonNull = false;
        if (objs != null) {
            for (Object obj : objs) {
                if (!isEmpty(obj)) {
                    isAnyNonNull = true;
                    break;
                }
            }
        }
        return isAnyNonNull;
    }

    /**
     * Verifica se todos os parametros informados são NÃO nulos ou vazio
     */
    public static boolean isAllNonNull(Object... objs) {
        boolean isAllNonNull = objs != null;
        if (objs != null) {
            for (Object obj : objs) {
                if (isEmpty(obj)) {
                    isAllNonNull = false;
                    break;
                }
            }
        }
        return isAllNonNull;
    }

    private static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof CharSequence) {
            return (((CharSequence) obj).toString().trim().isEmpty());
        } else if (obj instanceof Number) {
            return (((Number) obj).longValue() == 0);
        } else if (obj instanceof Collection<?>) {
            return (((Collection<?>) obj).isEmpty());
        } else if (obj instanceof Map) {
            return (((Map<?, ?>) obj).isEmpty());
        } else if (obj instanceof Object[]) {
            return (((Object[]) obj).length == 0);
        }
        return false;
    }

}
