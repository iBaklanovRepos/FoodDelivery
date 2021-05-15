package ssau.web_app.Food_delivery_app.servlets;

import ssau.web_app.Food_delivery_app.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;


@WebServlet(name = "Delete Row Servlet", urlPatterns = "/delete")
public class DeleteRowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String table = req.getParameter("table");
        int id = Integer.parseInt(req.getParameter("id"));
        switch (table){
            case "customer":{
                CustomerDAO.deleteCustomer(CustomerDAO.selectCustomerById(id));
                break;
            }
            case "order":{
                DeliveryDAO.deleteDelivery(DeliveryDAO.selectDeliveryById(id));
                break;
            }
            case "restaurant": {
                RestaurantDAO.deleteRestaurant(RestaurantDAO.selectRestaurantById(id));
                break;
            }
            case "courier":{
                CourierDAO.deleteCourier(CourierDAO.selectCourierById(id));
                break;
            }
            case "status":{
                DeliveryStatusDAO.deleteDeliveryStatus(DeliveryStatusDAO.selectDeliveryStatusById(id));
                break;
            }
            default:{
                throw new ServletException();
            }
        }
//        resp.sendRedirect("");
//        req.getRequestDispatcher("").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
