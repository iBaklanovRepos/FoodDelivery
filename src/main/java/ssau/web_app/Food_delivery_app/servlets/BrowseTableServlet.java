package ssau.web_app.Food_delivery_app.servlets;

import ssau.web_app.Food_delivery_app.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Browse Table Servlet", urlPatterns = "", loadOnStartup = 1)
public class BrowseTableServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("customers", CustomerDAO.selectCustomers());
        req.setAttribute("orders", DeliveryDAO.selectDeliveries());
        req.setAttribute("statuses", DeliveryStatusDAO.selectDeliveryStatuses());
        req.setAttribute("couriers", CourierDAO.selectCouriers());
        req.setAttribute("restaurants", RestaurantDAO.selectRestaurants());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
