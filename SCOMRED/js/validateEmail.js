function validateEmail(emailInput) {
    var email = emailInput.value;
    var pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    if (pattern.test(email)) {
        return true;
    } else {
        alert("Invalid email address!");
        window.setTimeout(() => emailInput.focus(), 0);
        return false;
    }
}