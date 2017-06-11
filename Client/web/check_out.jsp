<%@ page import="java.util.Map" %>
<%@ page import="java.util.Vector" %><%--
  Created by IntelliJ IDEA.
  User: Alfonso
  Date: 5/29/2017
  Time: 1:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" session="true" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="author" content="Alfonso Almazan Ruelas">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/more_details.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <title>Check Out</title>
</head>
<body>
    <jsp:include page="nav_bar.html"/>

    <div class="row">
        <table class="col-8">
            <tr>
                <th class="col-1">Item Id.</th>
                <th class="col-2">Product</th>
                <th class="col-1">Quantity</th>
                <th class="col-2">Name</th>
                <th class="col-1">Price</th>
                <th class="col-1">Remove</th>
            </tr>

        <%
        Vector<Map<String, String>> cartList = (Vector<Map<String, String>>) request.getAttribute("cartList");
        for (int i = 0; i < cartList.size(); i++) {
        %>
            <tr>
                <td class="col-1"><%= cartList.get(i).get("shoeId") %></td>
                <td class="col-2">
                    <img class='checkout_grid_image' src=<%= cartList.get(i).get("url") %> alt="ShoeImage"/>
                </td>
                <td class="col-1"> <span>1</span> </td>
                <td class="col-2"><%= cartList.get(i).get("name")%> </td>
                <td class="col-1">$<%= cartList.get(i).get("cost")%></td>
                <td class="col-1"><a href="DeleteItemFromCart?shoeId=<%=cartList.get(i).get("id")%>">Delete</a></td>
            </tr>
        <% } %>
        </table>

        <div class="col-4">
            <h1 id="symbol">Total Price: $${totalCost}</h1>
            <form name="form" method="post" action="SaveOrder">
                <label for="fname">First Name</label>
                <input type="text" id="fname" name="firstname" placeholder="First name ...">

                <label for="lname">Last Name</label>
                <input type="text" id="lname" name="lastname" placeholder="Your last name..">

                <label for="phonenum">Phone Number</label>
                <input type="number" id="phonenum" name="phonenumber" placeholder="Contact number">

                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Your email ...">

                <label for="address">Shipping Address</label>
                <input type="text" id="address" name="address" placeholder="Address">
                <input type="text" id="city" name="city" placeholder="City">
                <input type="text" id="state" name="state" placeholder="State">
                <input type="number" id="zipcode" name="zip_code" placeholder="Zip Code">

                <label for="shippingMethod">Shipping Method</label>
                <select id="shippingMethod" name="shippingMethod">
                    <option value="ground" selected>6-Days Ground</option>
                    <option value="two">Two day Shipping</option>
                    <option value="overnight">Overnight</option>
                </select>

                <label for="cardinfo">Payment</label>
                <input type="number" id="cardinfo" name="cardinfo" placeholder="Card information ...">

                <input type="hidden" name="totalCost" value=${totalCost} />

                <input type="submit" value="Buy Now!">
                <span class="error" aria-live="polite"></span>
            </form>
        </div>
    </div>

    <jsp:include page="footer.html" />

</body>
</html>
