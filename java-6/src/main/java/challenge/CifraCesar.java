package challenge;


import challenge.util.ValidaAlfabetoUtil;

public class CifraCesar {

    private int cifra;
    private char[] alfabetoCriptografado;

    public CifraCesar(int cifra){
        this.cifra = cifra;
        this.alfabetoCriptografado = geraAlfabetoCriptografado(ValidaAlfabetoUtil.getAlfabeto());
    }

    public char[] geraAlfabetoCriptografado(char[] alfabeto){
        int tamanhoAlfabeto = alfabeto.length;
        char[] alfabetoCriptografado = new char[tamanhoAlfabeto];
        int posicaoNoAlfabeto = 0;

        for (int i = 0; i < tamanhoAlfabeto; i++){
            posicaoNoAlfabeto = (ValidaAlfabetoUtil.posicaoLetraAlfabeto(alfabeto[i], alfabeto) + this.cifra) % tamanhoAlfabeto;
            alfabetoCriptografado[i] = alfabeto[posicaoNoAlfabeto];
        }

        return alfabetoCriptografado;
    }

    public String criptografa(String entrada, boolean criptografar){
        char[] letrasDaPalavra = entrada.toCharArray();

        StringBuilder palavraCifrada = new StringBuilder();

        for (int i = 0; i < letrasDaPalavra.length; i++){
            char letraDaPalavra = letrasDaPalavra[i];

            if (ValidaAlfabetoUtil.naoEhUmaLetraMasEhValido(letraDaPalavra)) {
                palavraCifrada.append(letraDaPalavra);
            } else if (ValidaAlfabetoUtil.ehUmaLetra(letraDaPalavra)) {

                int posicaoNoAlfabeto = 0;
                char letra = ' ';

                if (criptografar) {
                    posicaoNoAlfabeto = ValidaAlfabetoUtil.posicaoLetraAlfabeto(letraDaPalavra, ValidaAlfabetoUtil.getAlfabeto());
                    letra = this.alfabetoCriptografado[posicaoNoAlfabeto];
                } else {
                    posicaoNoAlfabeto = ValidaAlfabetoUtil.posicaoLetraAlfabeto(letraDaPalavra, this.alfabetoCriptografado);
                    letra = ValidaAlfabetoUtil.getAlfabeto()[posicaoNoAlfabeto];
                }


                if (ValidaAlfabetoUtil.isLetraMinuscula(letraDaPalavra)) {
                    letra = Character.toLowerCase(letra);
                }

                palavraCifrada.append(letra);
            } else {
                palavraCifrada.append('_');
            }
        }

        return null;
    }

}