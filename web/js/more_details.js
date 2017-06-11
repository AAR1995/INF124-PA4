/**
 * Created by Alfonso on 4/23/2017.
 */

/* When the user clicks on the button,
 toggle between hiding and showing the dropdown content */

function setupEventHandlers(){
    var form  = document.getElementsByTagName('form')[0];

    var email = document.getElementById('email');
    var phone = document.getElementById('phonenum');
    var card = document.getElementById('cardinfo');
    var error = document.querySelector('.error');


    email.addEventListener("keyup", function (event) {

        if (email.validity.valid) {
            // In case there is an error message visible, if the field
            // is valid, we remove the error message.
            error.innerHTML = ""; // Reset the content of the message
            error.className = "error"; // Reset the visual state of the message
        }
    }, false);

    phone.addEventListener("keyup", function (event) {

        if (validate_phonenumber(phone.value)) {
            // In case there is an error message visible, if the field
            // is valid, we remove the error message.
            error.innerHTML = ""; // Reset the content of the message
            error.className = "error"; // Reset the visual state of the message
        }
    }, false);

    card.addEventListener("keyup", function (event) {

        if (validate_card(card)) {
            // In case there is an error message visible, if the field
            // is valid, we remove the error message.
            error.innerHTML = ""; // Reset the content of the message
            error.className = "error"; // Reset the visual state of the message
        }
    }, false);

    form.addEventListener("submit", function (event) {

        if (!email.validity.valid) {

            error.innerHTML = "Please enter a valid email";
            error.className = "error active";
            // And we prevent the form from being sent by canceling the event
            event.preventDefault();
        }
    }, false);
}
