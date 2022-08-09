package ru.lapshina;

import ru.lapshina.product.Product;
import ru.lapshina.product.ProductStorage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/product/*")
public class MyHttpServlet extends HttpServlet {
    private ProductStorage productStorage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.productStorage = new ProductStorage();
        productStorage.insert(new Product("Огурец", 6));
        productStorage.insert(new Product("Картофель", 5));
        productStorage.insert(new Product("Лук", 3));
        productStorage.insert(new Product("Морковь", 4));
        productStorage.insert(new Product("Яблоко", 6));
        productStorage.insert(new Product("Ананас", 10));
        productStorage.insert(new Product("Цветная капуста", 10));
        productStorage.insert(new Product("Брокколи", 8));
        productStorage.insert(new Product("Апельсин", 6));
        productStorage.insert(new Product("Киви", 11));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<th>id</th>");
        writer.println("<th>title</th>");
        writer.println("<th>cost</th>");
        writer.println("</tr>");
        if (req.getPathInfo() == null) {
            for (Product product : productStorage.findAll()) {
                writer.println("<tr>");
                writer.println(String.format("<td>%d</td>", product.getId()));
                writer.println(String.format("<td><a href = \"%s/product/%d\">%s</a></td>", req.getContextPath(), product.getId(), product.getTitle()));
                writer.println(String.format("<td>%d</td>", product.getCost()));
                writer.println("</tr>");
            }

        } else {
            for (Product product : productStorage.findAll()) {
                if (req.getPathInfo().equals(String.format("/%d", product.getId()))) {
                    writer.println("<tr>");
                    writer.println(String.format("<td>%d</td>", product.getId()));
                    writer.println(String.format("<td>%s</td>", product.getTitle()));
                    writer.println(String.format("<td>%d</td>", product.getCost()));
                    writer.println("</tr>");
                }
            }
        }


        writer.println("</table>");

    }
}
