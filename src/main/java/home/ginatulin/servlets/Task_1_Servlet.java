package home.ginatulin.servlets;

import home.ginatulin.models.Product;
import home.ginatulin.utils.Randomaizer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Task_1", urlPatterns = "/Task_1")
public class Task_1_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int count;
        try {
            count = Integer.parseInt(req.getParameter("num"));
        } catch (Exception e) {
            count = 10;
        }
        sendProductTitle(count, resp);
    }

    private void sendProductTitle(int count, HttpServletResponse resp) throws IOException {
        StringBuilder response = new StringBuilder("<html><body>");
        Product product;
        for (int i = 0; i < count; i++) {
            product = Randomaizer.getRandomProduct();
            response.append("<h1>").append(product.getTitle()).append("</h1><br>");
            response.append("   ").append(product.getId()).append("<br>");
            response.append("   ").append(product.getCost()).append("<br>");
        }
        response.append("</body></html>");
        resp.getWriter().printf(response.toString());
    }

}
