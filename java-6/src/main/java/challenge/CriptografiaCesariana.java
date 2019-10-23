package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {

//        throw new UnsupportedOperationException("esse method nao esta implementado aainda");

        CifraCesar cifraCesar = new CifraCesar(3);

        try {

            cifraCesar.criptografa(texto, true);

        } catch (NullPointerException e) {
            System.out.print("Caught the NullPointerException");
        }

        return texto;

    }

    @Override
    public String descriptografar(String texto) {

        throw new UnsupportedOperationException("esse method nao esta implementado aainda");
    }
}
