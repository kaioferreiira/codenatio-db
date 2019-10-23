package challenge;

public class TestCifraCesar {


    public static void main(String[] args) {


        CifraCesar cifraCesar = new CifraCesar(3);

        String mensagem = "d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr";

        String decription = cifraCesar.criptografa(mensagem, false);

        System.out.println(decription);

    }
}
