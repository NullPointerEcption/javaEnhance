package result;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 传入json信息，返回给前台界面
 */
public class AjaxResult implements BaseResult {

    private Object data;
    private JSONRESULT jsonResult;

    public AjaxResult(Object data) {
        this.data = data;
        this.jsonResult= JSONRESULT.SUCCESS;
    }
    public AjaxResult(Object data, JSONRESULT jsonResult) {
        this.data = data;
        this.jsonResult = jsonResult;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        try {
            response.getWriter().print(new Gson().toJson(this));
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }
}
enum JSONRESULT {


    SUCCESS(200),
    ERROR(500);

    private int resultCode;
    JSONRESULT(int resultCode) {
        this.resultCode=resultCode;
    }
}
