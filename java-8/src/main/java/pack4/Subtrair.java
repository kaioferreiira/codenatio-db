package pack4;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
public @interface Subtrair {

    String value();

}
