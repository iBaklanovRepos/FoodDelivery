<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ssau.web_app.Food_delivery_app.models.*" %>
<%@ page import="ssau.web_app.Food_delivery_app.dao.*" %>
<%@page errorPage="errorPage.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://kit.fontawesome.com/27b0ac22ed.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value = '/css/style.css'/>"/>
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
    <title>Food Delivery</title>
</head>
<body>
<jsp:useBean id="customers" scope="request" type="java.util.List<ssau.web_app.Food_delivery_app.models.Customer>"/>
<jsp:useBean id="restaurants" scope="request" type="java.util.List<ssau.web_app.Food_delivery_app.models.Restaurant>"/>
<jsp:useBean id="orders" scope="request" type="java.util.List<ssau.web_app.Food_delivery_app.models.Delivery>"/>
<jsp:useBean id="statuses" scope="request" type="java.util.List<ssau.web_app.Food_delivery_app.models.DeliveryStatus>"/>
<jsp:useBean id="couriers" scope="request" type="java.util.List<ssau.web_app.Food_delivery_app.models.Courier>"/>


<header class="showcase">
    <div class="showcase-top">
        <img src="img/logo.png" alt="" srcset="">
    </div>
</header>

<section class="tabs">
    <div class="container">
        <div id="tab-1" class="tab-item">
            <i class="fas fa-shopping-basket fa-3x"></i>
            <p class="hide-sm">Orders</p>
        </div>
        <div id="tab-2" class="tab-item">
            <i class="fas fa-user fa-3x"></i>
            <p class="hide-sm">Customers</p>
        </div>
        <div id="tab-3" class="tab-item tab-border">
            <i class="fas fa-utensils fa-3x"></i>
            <p class="hide-sm">Restaurants</p>
        </div>
        <div id="tab-4" class="tab-item">
            <i class="fas fa-hiking fa-3x"></i>
            <p class="hide-sm">Couriers</p>
        </div>
        <div id="tab-5" class="tab-item">
            <i class="fas fa-info fa-3x"></i>
            <p class="hide-sm">Statuses</p>
        </div>
    </div>

</section>


<section class="table-content">
    <div class="container">
        <div id="tab-1-content" class="tab-content-item order">
            <div>

                <table class="table">
                    <thead>

                    <tr>
                        <th>Id</th>
                        <th>Customer</th>
                        <th>Restaurant</th>
                        <th>Courier</th>
                        <th>Status</th>
                        <th>Details</th>
                    </tr>
                    </thead>
                    <tbody id="order-table">
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td><c:out value="${order.delivery_id}"/></td>
                            <td><c:out value="${order.customer.customer_name}"/></td>
                            <td><c:out value="${order.restaurant.restaurant_name}"/></td>
                            <td><c:out value="${order.courier.courier_name}"/></td>
                            <td><c:out value="${order.delivery_status.delivery_status_name}"/></td>
                            <td><c:out value="${order.delivery_details}"/></td>
                            <td>
                                <button class="btn btn-edit modal-activate" data-toggle="modal"
                                        data-target="#exampleModalCenter" type="button" name="action"
                                        value="edit-customer">
                                    <a href="edit?table=order&id=<c:out value='${order.delivery_id}'/>"></a>
                                    <i class="fas fa-edit"></i>
                                </button>
                            </td>
                            <td>
                                <button class="btn btn-dlt" type="button" name="action" value="delete">
                                    <i class="fas fa-trash-alt"></i>
                                </button>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
        <div id="tab-2-content" class="tab-content-item customer">
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Address</th>

                    </tr>
                    </thead>


                    <tbody id="customer-table">
                    <c:forEach var="customer" items="${customers}">
                        <tr>
                            <td><c:out value="${customer.customer_id}"/></td>
                            <td><c:out value="${customer.customer_name}"/></td>
                            <td><c:out value="${customer.customer_address}"/></td>
                            <td>
                                <button class="btn btn-edit modal-activate" data-toggle="modal"
                                        data-target="#exampleModalCenter" type="button" name="action"
                                        value="edit-customer">
                                    <a href="edit?table=customer&id=<c:out value='${customer.customer_id}'/>"></a>
                                    <i class="fas fa-edit"></i>
                                </button>
                            </td>
                            <td>
                                <button class="btn btn-dlt" type="button" name="action" value="delete">
                                    <i class="fas fa-trash-alt"></i>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div id="tab-3-content" class="tab-content-item restaurant show">
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Description</th>
                    </tr>
                    </thead>
                    <tbody id="restaurant-table">

                    <c:forEach var="restaurant" items="${restaurants}">
                        <tr>
                            <td><c:out value="${restaurant.restaurant_id}"/></td>
                            <td><c:out value="${restaurant.restaurant_name}"/></td>
                            <td><c:out value="${restaurant.restaurant_location}"/></td>
                            <td><c:out value="${restaurant.restaurant_description}"/></td>
                            <td>
                                <button class="btn btn-edit modal-activate" data-toggle="modal"
                                        data-target="#exampleModalCenter" type="button" name="action"
                                        value="edit-customer">
                                    <i class="fas fa-edit"></i>
                                </button>
                            </td>
                            <td>
                                <button class="btn btn-dlt" type="button" name="action" value="delete">

                                    <i class="fas fa-trash-alt"></i>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div id="tab-4-content" class="tab-content-item courier">
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Location</th>
                        <th>Description</th>
                    </tr>
                    </thead>
                    <tbody id="courier-table">
                    <c:forEach var="courier" items="${couriers}">
                        <tr>
                            <td><c:out value="${courier.courier_id}"/></td>
                            <td><c:out value="${courier.courier_name}"/></td>
                            <td><c:out value="${courier.courier_location}"/></td>
                            <td><c:out value="${courier.courier_details}"/></td>
                            <td>
                                <button class="btn btn-edit modal-activate" data-toggle="modal"
                                        data-target="#exampleModalCenter" type="button" name="action"
                                        value="edit-customer">
                                    <a href="edit?table=courier&id=<c:out value='${courier.courier_id}'/>"></a>
                                    <i class="fas fa-edit"></i>
                                </button>
                            </td>
                            <td>
                                <button class="btn btn-dlt" type="button" name="action" value="delete">
                                    <i class="fas fa-trash-alt"></i>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div id="tab-5-content" class="tab-content-item status">
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                    </tr>
                    </thead>

                    <tbody id="status-table">
                    <c:forEach var="status" items="${statuses}">
                        <tr>
                            <td><c:out value="${status.delivery_status_id}"/></td>
                            <td><c:out value="${status.delivery_status_name}"/></td>
                            <td><c:out value="${status.delivery_status_description}"/></td>
                            <td>
                                <button class="btn btn-edit modal-activate" data-toggle="modal"
                                        data-target="#exampleModalCenter" type="button" name="action"
                                        value="edit-customer">
                                    <a href="edit?table=status&id=<c:out value='${status.delivery_status_id}'/>"></a>
                                    <i class="fas fa-edit"></i>
                                </button>
                            </td>
                            <td>
                                <button class="btn btn-dlt" type="button" name="action" value="delete">
                                    <i class="fas fa-trash-alt"></i>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
            </div>
        </div>

        <div id="bottom-buttons">
<%--            <input type="button"  value="Add new restaurant">--%>
            <button class="btn modal-activate" id="btn-add">Add new restaurant</button>
        </div>


        <%--        MODAL WINDOW--%>
        <div id="modal-window" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <h2>fgg</h2>
                    <span id="close-modal">&times;</span>
                </div>
                <div class="modal-body">
                    <div id="order-modal-body" class="show-modal">
                        <div class="input-field">
                            <label>Select customer</label>
                                <select class="order-modal-input" id="order-customer-picker" name="order_customer">
                                    <%for (Customer customer : CustomerDAO.selectCustomers()) {%>
                                    <option value="<%=customer.getCustomer_name()%>"><%=customer.getCustomer_name()%>
                                    </option>
                                    <%}%>
                                </select>
                        </div>

                        <div class="input-field">
                            <label>Select restaurant</label>
                                <select class="order-modal-input" id="order-restaurant-picker">
                                    <%for (Restaurant restaurant : RestaurantDAO.selectRestaurants()) {%>
                                    <option value="<%=restaurant.getRestaurant_name()%>"><%=restaurant.getRestaurant_name()%>
                                    </option>
                                    <%}%>
                                </select>

                        </div>

                        <div class="input-field">
                            <label>Select courier</label>
                                <select class="order-modal-input" id="order-courier-picker">
                                    <%for (Courier courier : CourierDAO.selectCouriers()) {%>
                                    <option value="<%=courier.getCourier_name()%>"><%=courier.getCourier_name()%>
                                    </option>
                                    <%}%>
                                </select>

                        </div>

                        <div class="input-field">
                            <label>Select status</label>
                                <select class="order-modal-input" id="order-status-picker">
                                    <%for (DeliveryStatus status : DeliveryStatusDAO.selectDeliveryStatuses()) {%>
                                    <option value="<%=status.getDelivery_status_name()%>"><%=status.getDelivery_status_name()%>
                                    </option>
                                    <%}%>
                                </select>

                        </div>
                        <div class="input-field">
<%--                            <a>Add details</a>--%>
                            <input class="order-modal-input" id="order-details-input" type="text" pattern="{}" placeholder="Details" maxlength="50">
                        </div>
                    </div>


                    <div id="customer-modal-body">
                        <div class="input-field">
<%--                            <a>Name</a>--%>
                            <input class="customer-modal-input" id="customer-name-input" type="text" pattern="[A-Za-z ]{1,40}" placeholder="Name" required>
                        </div>

                        <div class="input-field">
<%--                            <a>Address</a>--%>
                            <input class="customer-modal-input" id="customer-address-input" type="text" pattern="[A-Za-z0-9 ]{1,40}" placeholder="Address" required>
                        </div>
                    </div>

                    <div id="restaurant-modal-body">
                        <div class="input-field">
<%--                            <a>Name</a>--%>
                            <input class="restaurant-modal-input" id="restaurant-name-input" type="text" placeholder="Name" pattern="[A-Za-z0-9 ]{1,40}" required>
                        </div>

                        <div class="input-field">
<%--                            <a>Address</a>--%>
                            <input class="restaurant-modal-input" id="restaurant-address-input" type="text" placeholder="Address" pattern="[A-Za-z0-9 ]{1,40}" required>
                        </div>

                        <div class="input-field">
<%--                            <a>Description</a>--%>
                            <input class="restaurant-modal-input" id="restaurant-description-input" type="text" placeholder="Description">
                        </div>
                    </div>

                    <div id="courier-modal-body">
                        <div class="input-field">
<%--                            <a>Name</a>--%>
                            <input class="courier-modal-input" id="courier-name-input" type="text" pattern="[A-Za-z ]{1,40}" placeholder="Name" required>
                        </div>

                        <div class="input-field">
<%--                            <a>Location</a>--%>
                            <input class="courier-modal-input" id="courier-address-input" type="text" pattern="[A-Za-z0-9 ]{1,40}" placeholder="Location" required>
                        </div>

                        <div class="input-field">
<%--                            <a>Description</a>--%>
                            <input class="courier-modal-input" id="courier-description-input" type="text" placeholder="Description">
                        </div>
                    </div>

                    <div id="status-modal-body">
                        <div class="input-field">
<%--                            <a>Name</a>--%>
                            <input class="status-modal-input" id="status-name-input" type="text" pattern="[A-Za-z ]{1,40}" placeholder="Name" required>
                        </div>

                        <div class="input-field">
<%--                            <a>Description</a>--%>
                            <input class="status-modal-input" id="status-description-input" type="text" placeholder="Description">
                        </div>
                    </div>

                </div>

                <div class="modal-footer">
                    <div class="modal-footer-btn">
                        <button id="modal-accept" class="btn">Accept</button>
                        <%--                            <button id="modal-decline" class="btn">Close</button>--%>
                    </div>
                </div>

            </div>
        </div>

    </div>
</section>
<script src="script/main.js"></script>

</body>
</html>