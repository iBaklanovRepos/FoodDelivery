package ssau.web_app.Food_delivery_app.servlets;

import ssau.web_app.Food_delivery_app.dao.*;
import ssau.web_app.Food_delivery_app.models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Edit Row Servlet", urlPatterns = "/edit")
public class EditRowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("we are editing");
        System.out.println(req.getQueryString());
        String table = req.getParameter("table");
        StringBuilder builder = new StringBuilder();
        int id = Integer.parseInt(req.getParameter("id"));
        builder.append("<id>").append(id).append("</id>");

        switch (table) {
            case "customer": {
                String name = req.getParameter("name");
                String address = req.getParameter("address");
                Customer customer = CustomerDAO.selectCustomerById(id);
                customer.setCustomer_address(address);
                customer.setCustomer_name(name);
                CustomerDAO.updateCustomer(customer);
                builder.append("<name>").append(name).append("</name>");
                builder.append("<address>").append(address).append("</address>");
                break;
            }
            case "order": {
                Customer customer = CustomerDAO.selectCustomerByName(req.getParameter("customer"));
                Restaurant restaurant = RestaurantDAO.selectRestaurantByName(req.getParameter("restaurant"));
                Courier courier = CourierDAO.selectCourierByName(req.getParameter("courier"));
                DeliveryStatus status = DeliveryStatusDAO.selectDeliveryStatusByName(req.getParameter("status"));
                String details = req.getParameter("details");
                Delivery delivery = DeliveryDAO.selectDeliveryById(id);
                delivery.setCustomer(customer);
                delivery.setCourier(courier);
                delivery.setRestaurant(restaurant);
                delivery.setDelivery_status(status);
                delivery.setDelivery_details(details);
                DeliveryDAO.updateDelivery(delivery);
                builder.append("<courier>").append(courier.getCourier_name()).append("</courier>");
                builder.append("<restaurant>").append(restaurant.getRestaurant_name()).append("</restaurant>");
                builder.append("<customer>").append(customer.getCustomer_name()).append("</customer>");
                builder.append("<status>").append(status.getDelivery_status_name()).append("</status>");
                builder.append("<details>").append(details).append("</details>");
                break;
            }
            case "restaurant": {
                String name = req.getParameter("name");
                String address = req.getParameter("address");
                String description = req.getParameter("description");
                Restaurant restaurant = RestaurantDAO.selectRestaurantById(id);
                restaurant.setRestaurant_name(name);
                restaurant.setRestaurant_location(address);
                restaurant.setRestaurant_description(description);
                RestaurantDAO.updateRestaurant(restaurant);
                builder.append("<name>").append(name).append("</name>");
                builder.append("<address>").append(address).append("</address>");
                builder.append("<description>").append(description).append("</description>");
                break;
            }
            case "courier": {
                String name = req.getParameter("name");
                String location = req.getParameter("location");
                String description = req.getParameter("description");
                Courier courier = CourierDAO.selectCourierById(id);
                courier.setCourier_name(name);
                courier.setCourier_location(location);
                courier.setCourier_details(description);
                CourierDAO.updateCourier(courier);
                builder.append("<name>").append(name).append("</name>");
                builder.append("<location>").append(location).append("</location>");
                builder.append("<description>").append(description).append("</description>");

                break;
            }
            case "status": {
                String name = req.getParameter("name");
                String description = req.getParameter("description");
                DeliveryStatus status = DeliveryStatusDAO.selectDeliveryStatusById(id);
                status.setDelivery_status_name(name);
                status.setDelivery_status_description(description);
                builder.append("<name>").append(name).append("</name>");
                builder.append("<description>").append(description).append("</description>");
                break;
            }
            default: {
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
