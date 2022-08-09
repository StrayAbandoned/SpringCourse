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


@WebServlet(urlPatterns = "/products/*")
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
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("product", productStorage.findAll());
            req.getServletContext().getRequestDispatcher("/products.jsp").forward(req, resp);
            return;
        }
        for (Product product: productStorage.findAll()){
            if (req.getPathInfo().equals(String.format("/%d",product.getId()))){
                req.setAttribute("id", product.getTitle());
                req.getServletContext().getRequestDispatcher("/product_form.jsp").forward(req, resp);
            }
        }

    }
}
