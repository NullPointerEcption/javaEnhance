package anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 用于解析请求的参数
 */
@Target(ElementType.PARAMETER)
public @interface Param {
    String value();
}
