import com.caucho.hessian.server.HessianServlet;
import interfaces.ICalculatable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calc")
public class CalcServlet extends HessianServlet implements ICalculatable {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        System.out.println("do Gettting......");
        resp.getWriter().print("hello");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    public int add(int i1, int i2) {
        return i1 + i2;
    }

    public int multi(int i1, int i2) {
        return i1 * i2;
    }
}
