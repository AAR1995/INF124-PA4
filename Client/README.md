# shoe_up
URL: http://andromeda-8.ics.uci.edu:5008/ShoeUpServlet_war/

Layout: On every page there is a navigation bar on the top. When clicked on the home item, you will return to the 
*index.jsp* page. If you hover over the men item on the navigation bar you will get a drop down menu giving you
two more options; Casual or Dress. Selecting casual will display a new page with a grid of 6 casual shoes. Selecting
dress wil display a new page with a grid of 6 dress shoes. On both of these pages the navigation bar will remain on
top. Clicking on any of the shoes in the grid will take you to a new page giving you more details about the shoe.
By clicking on the 'About Us' item you will see pictures of the "founders" and a short description about 
the company. At the bottom of every page displaying a grid of products I display
the last 5 viewed products. Finally, clicking on the 'Cart' item will take you to a new page displaying all items in the cart.

1. Include the output of two servlets to create the homepage for your e-commerce site: the first servlet should handle the displaying of the list of products obtained from a backend database, and the second servlet should use session tracking to display the last 5 products that the user has visited (viewed the product details page). In case this number is less than 5, show whatever amount of information you have stored in the session. Use servlet "include" feature to implement this requirement.
    * CreateProductGridServlet creates the list of all products obtained
     from the backend after it receives a get request. The request contians
     a 'typeOfShoes' attribute indicating the type of shoe to grab from the
     database. CreateProductGridServlet gets called from three other servlets,
      CreateGeneralProductPage, CreateDressShoesPage, and CreateCasualShoesPage
      using the include feature. All three of those servlets get called when clicking
       on either the 'Men' navigation bar item or its drop down menu items,
       'Casual' and 'Dress'. The History servlet creates grid of recently 
      viewed products. If no products have been viewed the recently viewed section displays "No History to Display".
      In ProductDetails servlet doGet method saves the currently viewed product to the session.

2. Using servlets create a "product details" page. This page should take a product identifier as a parameter and show the product details after getting the relevant information from the database. This page should NOT have an order form, only a button to "Add to Cart". Use servlet "session" to store the products in a shopping cart.
    * I created a ProductDetails servlet which takes the shoe id parameter in its url. 
    When 'Add To Cart' gets clicked a post request to AddToCart servlet gets called.
    In the doPost method I save the shoeId in the current session under the 'cart_items'
    parameter. This saves the product to the cart. As a result I redirect to the ProductDetails 
    servlet as a get request. In the url to redirect to product details I included 'inCart' 
    parameter indicating the product was successfully added to the cart.

3. Using servlets create a "check out" page, which allows the user to place an order. The page should show all the products in the shopping cart and the total price. This page should have a form which will allow the user to do the following:
    
    Enter shipping information: name, shipping address, phone number, credit card number, etc.
    Submit the order for storage in the backend database
    On successful submission, forward to the order details page. Use servlet "forward" feature to implement this requirement. 

    * AddToCart doGet does this by forwarding the request and response to checkout.jsp.
    In checkout.jsp we only display the form and all products in the cart if there are items in the cart.
    We display these items in a table containing the product id, image, name, cost, and a href to
    delete the item from the cart. If there are no items in the cart, we only display text indicating 
    there are no items in the cart. After filling out the form and clicking
    the 'Pay Now' button, you are redirected to a order_details.jsp file.
    When clicking 'Pay Now' a post request is made to the SaveOrder servlet,
    saving the information to the database and forwards the request to order_details.jsp.