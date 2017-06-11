<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="author" content="Alfonso Almazan Ruelas">
    <title>More Details Page</title>
    <link href="css/main.css" rel="stylesheet">
    <link href="css/more_details.css" rel="stylesheet">

</head>

<body>
    <jsp:include page="nav_bar.html" />

    <div class="row">
        <div class="col-8">
            <img class="single_grid_image" id="shoe_image" src=<%= request.getAttribute("url")%> alt="Shoes">
        </div>

        <div class="col-4">
            <h1> <%= request.getAttribute("name") %></h1>
            <h2>Description</h2>
            <p id="description"><%= request.getAttribute("description") %></p>
            <h2>$ <%= request.getAttribute("cost") %></h2>
            <form method="post" action="AddToCart" >
                <input type="submit" value="Add To Cart" />
                <input type="hidden" name="shoeId" value=<%= request.getAttribute("shoeId") %> />
            </form>
            <h1>${itemInCart}</h1>
        </div>
    </div>

    <jsp:include page="footer.html" />

</body>
</html>