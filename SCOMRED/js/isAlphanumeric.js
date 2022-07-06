function alphanumeric(parameter, inputText) {
    var text = inputText.value;
    var pattern = /^[0-9a-zA-Z]+$/;

    if (pattern.test(text)) {
        return true;
    } else {
        window.alert(parameter + ' - Only numbers and letters are allowed.');
        window.setTimeout(() => inputText.focus(), 0);
        return false;
    }
}