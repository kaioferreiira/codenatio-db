package pack4;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class AnnotationProcessor implements Calculavel {

    private List<Somar> listSomar = new ArrayList<>();
    private List<Subtrair> listSubtrair = new ArrayList<>();
    
    public AnnotationProcessor(Class clazz) {
        processaAnotacoes(clazz);
    }

    private void processaAnotacoes(Class clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            
            for (int i = 0; i < annotations.length; i++) {
                if (annotations[i] instanceof Somar && field.getType().equals(BigDecimal.class)) {
                    listSomar.add((Somar) annotations[i]);
                }
                if (annotations[i] instanceof Subtrair && field.getType().equals(BigDecimal.class)) {
                    listSubtrair.add((Subtrair) annotations[i]);
                }
            }
        }
    }

    @Override
    public BigDecimal somar() {
        BigDecimal retorno = new BigDecimal(0);
        for (Somar somar : listSomar) {
            retorno = retorno.add(new BigDecimal(somar.value()));
        }
        return retorno;
    }

    @Override
    public BigDecimal subtrair() {
        BigDecimal retorno = new BigDecimal(0);
        for (Subtrair subs : listSubtrair) {
            retorno = retorno.subtract(new BigDecimal(subs.value()));
        }
        return retorno;
    }

    @Override
    public BigDecimal totalizar() {
        BigDecimal retorno = new BigDecimal(0);
        for (Somar somar : listSomar) {
            retorno = retorno.add(new BigDecimal(somar.value()));
        }
        
        for (Subtrair subs : listSubtrair) {
            retorno = retorno.subtract(new BigDecimal(subs.value()));
        }
        return retorno;
    }
}
