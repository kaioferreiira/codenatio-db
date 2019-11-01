package pack4;

public class Main {

    public static void main(String[] args) {
        AnnotationProcessor an = new AnnotationProcessor(Calculadora.class);

        System.out.println("sdfsdfsd " + an.subtrair());
        System.out.println("sdfsdfsd " + an.somar());
        System.out.println("sdfsdfsd " + an.totalizar());

        AnnotationProcessor an2 = new AnnotationProcessor(ClasseSemNada.class);
        System.out.println("sdfsdfsd " + an2.totalizar());

        AnnotationProcessor an3 = new AnnotationProcessor(ClasseZoada.class);
        System.out.println("sdfsdfsd " + an3.totalizar());
    }

}
