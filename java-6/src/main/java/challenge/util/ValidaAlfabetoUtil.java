package challenge.util;

public abstract class ValidaAlfabetoUtil {

    private static char[] alfabeto = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static char[] getAlfabeto(){
        return alfabeto;
    }

    public static int posicaoLetraAlfabeto(char letra, char[] alfabetoInformado){
        int posicaoLetra = 0;

        for (int i = 0; i < alfabetoInformado.length; i++){
            if (Character.toUpperCase(letra) == alfabetoInformado[i] ||
                    Character.toLowerCase(letra) == Character.toLowerCase(alfabetoInformado[i])){
                posicaoLetra = i;
                break;
            }
        }
        return posicaoLetra;
    }

    public static boolean isEspacoEmBranco(char letraDaPalavra){
        return Character.isSpaceChar(letraDaPalavra);
    }

    public static boolean isLetraMinuscula(char letraDaPalavra){
        return Character.isLowerCase(letraDaPalavra);
    }

    public static boolean isSinalPontuacao(char letraDaPalavra){
        String letra = "" + letraDaPalavra;
        return letra.matches("\\p{Punct}");
    }

    public static boolean isNumero(char letraDaPalavra){
        return Character.isDigit(letraDaPalavra);
    }

    public static boolean isLetraValida(char letraDaPalavra){
        String letra = "" + letraDaPalavra;
        return letra.matches("[A-Za-z]");
    }

    public static boolean naoEhUmaLetraMasEhValido(char letraDaPalavra){
        return ValidaAlfabetoUtil.isEspacoEmBranco(letraDaPalavra) ||
                ValidaAlfabetoUtil.isSinalPontuacao(letraDaPalavra) || ValidaAlfabetoUtil.isNumero(letraDaPalavra);
    }

    public static boolean ehUmaLetra(char letraDaPalavra){
        return ValidaAlfabetoUtil.isLetraValida(letraDaPalavra);
    }
}