<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>About Us</title>
    <meta name="author" content="Alfonso Almazan Ruelas">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/about_us.css" rel="stylesheet">

</head>
    <body>
    <jsp:include page="nav_bar.html" />

        <div class="row">
            <header class="header">About Us</header>
            <div class="col-4">
                <img src="img/founder1.jpg">
                <img src="img/founder2.jpg">
            </div>
            <div class="col-8">
                <p>
                    We only bring the most exclusive products to our customers. With so many types of shoes in the market
                    we decided to hand pick a few for you. We stand behind our products 100% by giving you, the customer,
                    90 days money back guarantee on all shoes. If you are not satisfy with our products for any reason as
                    stupid as "I'm not feeling these shoes anymore" go on ahead and fill our contact us form. If you have
                    any questions or concerns feel free to contact us by filling the form below. :)
                </p>

            </div>
        </div>
        <div class="row">
            <header class="header">Contact Us</header>
            <div class="col-12">
                <form action="mailto:almazanr@uci.edu?subject=subject&message=message" method="post">
                    <label for="fname">First Name</label>
                    <input type="text" id="fname" name="firstname" placeholder="Your name..">

                    <label for="lname">Last Name</label>
                    <input type="text" id="lname" name="lastname" placeholder="Your last name..">

                    <label for="subject">Subject</label>
                    <textarea id="subject" name="subject" placeholder="Tells us something ..." style="height:200px"></textarea>

                    <input type="submit" value="Submit">
                </form>
            </div>
        </div>

        <jsp:include page="footer.html" />
    </body>
</html>