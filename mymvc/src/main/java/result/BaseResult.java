package result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 所有的Controller的返回结果
 */
public interface BaseResult {
    void execute(HttpServletRequest request, HttpServletResponse response) ;
}
