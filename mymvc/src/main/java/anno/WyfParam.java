package anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用于解析请求的参数
 */
@Target(ElementType.PARAMETER)
@Retention(RUNTIME)
public @interface WyfParam {
    String value();
}
