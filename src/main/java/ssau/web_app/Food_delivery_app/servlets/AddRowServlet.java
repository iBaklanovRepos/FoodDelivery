package ssau.web_app.Food_delivery_app.servlets;

import ssau.web_app.Food_delivery_app.dao.*;
import ssau.web_app.Food_delivery_app.models.*;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Add Row Servlet", urlPatterns = "/add")
public class AddRowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(req.getRequestURL());
        String table = req.getParameter("table");
        StringBuilder builder = new StringBuilder();
        int id;

        switch (table){
            case "customer":{
                String name = req.getParameter("name");
                String address = req.getParameter("address");
                id = CustomerDAO.insertCustomer(new Customer(name, address));
                builder.append("<id>").append(id).append("</id>");
                builder.append("<name>").append(name).append("</name>");
                builder.append("<address>").append(address).append("</address>");
                break;
            }
            case "order":{
                Customer customer = CustomerDAO.selectCustomerByName(req.getParameter("customer"));
                Restaurant restaurant = RestaurantDAO.selectRestaurantByName(req.getParameter("restaurant"));
                Courier courier = CourierDAO.selectCourierByName(req.getParameter("courier"));
                DeliveryStatus status = DeliveryStatusDAO.selectDeliveryStatusByName(req.getParameter("status"));
                String details = req.getParameter("details");
                id = DeliveryDAO.insertDelivery(new Delivery(customer, courier, status, restaurant, details));
                builder.append("<id>").append(id).append("</id>");
                builder.append("<courier>").append(courier.getCourier_name()).append("</courier>");
                builder.append("<restaurant>").append(restaurant.getRestaurant_name()).append("</restaurant>");
                builder.append("<customer>").append(customer.getCustomer_name()).append("</customer>");
                builder.append("<status>").append(status.getDelivery_status_name()).append("</status>");
                builder.append("<details>").append(details).append("</details>");
                System.out.println(details);
                break;
            }
            case "restaurant": {
                String name = req.getParameter("name");
                String address = req.getParameter("address");
                String description = req.getParameter("description");
                id = RestaurantDAO.insertRestaurant(new Restaurant(name, address, description));
                builder.append("<id>").append(id).append("</id>");
                builder.append("<name>").append(name).append("</name>");
                builder.append("<address>").append(address).append("</address>");
                builder.append("<description>").append(description).append("</description>");
                break;
            }
            case "courier":{
                String name = req.getParameter("name");
                String location = req.getParameter("location");
                String description = req.getParameter("description");
                id = CourierDAO.insertCourier(new Courier(name, location, description));
                builder.append("<id>").append(id).append("</id>");
                builder.append("<name>").append(name).append("</name>");
                builder.append("<location>").append(location).append("</location>");
                builder.append("<description>").append(description).append("</description>");

                break;
            }
            case "status":{
                String name = req.getParameter("name");
                String description = req.getParameter("description");
                id = DeliveryStatusDAO.insertDeliveryStatus(new DeliveryStatus(name, description));
                builder.append("<id>").append(id).append("</id>");
                builder.append("<name>").append(name).append("</name>");
                builder.append("<description>").append(description).append("</description>");
                break;
            }
            default:{
                throw new ServletException();
            }
        }
        resp.setContentType("text/xml");
        resp.setHeader("Cache-Control", "no-cache");
        String outputXML = "<?xml version=\"1.0\" ?><" + table + ">" + builder.toString() + "</" + table + ">";
        System.out.println(outputXML);
        PrintWriter out = resp.getWriter();
        out.println(outputXML);
        out.close();
    }
}
