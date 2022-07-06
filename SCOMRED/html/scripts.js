function stringLengthCheck(parameter, text, minLength, maxLength) {
    var mnLen = minLength;
    var mxLen = maxLength;
    var textLength = text.value.length;

    if (textLength < minLength || textLength > mxLen) {
        window.alert(parameter + " should be " + mnLen + " to " + mxLen + " characters.");
        window.setTimeout(() => text.focus(), 0);
        return false;
    } else {
        return true;
    }
}

function valueRangeCheck(parameter, number, minValue, maxValue) {
    var mnVal = minValue;
    var mxVal = maxValue;
    var value = number.value;

    if (value < mnVal || value > mxVal) {
        window.alert(parameter + " should be between" + mnVal + " to " + mxVal + " .");
        window.setTimeout(() => text.focus(), 0);
        return false;
    } else {
        return true;
    }
}

function dateIsAfterToday(date) {
    var sDate = new Date(date.value);
    var now = new Date();

    if (isNaN(sDate)) {
        window.alert("Start date is a mandatory field.");
        window.setTimeout(() => date.focus(), 0);
        return false;
    } else if (sDate < now) {
        window.alert("Start date must be greater than today.");
        window.setTimeout(() => date.focus(), 0);
        return false;
    } else {
        return true;
    }
}

function dateTwoIsAfterDateOne(dateOne, dateTwo) {
    var dOne = new Date(dateOne.value);
    var dTwo = new Date(dateTwo.value);

    if (isNaN(dTwo)) {
        window.alert("End date is a mandatory field.");
        window.setTimeout(() => date.focus(), 0);
        return false;
    } else if (dTwo <= dOne) {
        window.alert("End date must be greater than start date.");
        window.setTimeout(() => dateTwo.focus(), 0);
        return false;
    } else {
        return true;
    }
}


function endSprintIsAfterStartSprint(startSprint, endSprint) {
    var sSprint = startSprint.value;
    var eSprint = endSprint.value;

 if (eSprint < sSprint) {
        window.alert("End sprint must be greater than start sprint.");
        window.setTimeout(() => eSprint.focus(), 0);
        return false;
    } else 
        return true;
  
}


function validateUserName(parameter, inputText) {
    var text = inputText.value;
    var pattern = /^[0-9a-zA-Z ]+$/;

    if (pattern.test(text)) {
        return true;
    } else {
        window.alert(parameter + ' - Only letters and numbers are allowed.');
        window.setTimeout(() => inputText.focus(), 0);
        return false;
    }
}


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

function validateCode(codeInput) {
    var code = codeInput.value;
    var pattern = /^[a-zA-Z0-9]{5}$/;

    if (pattern.test(code)) {
        return true;
    } else {
        alert("Invalid code!");
        window.setTimeout(() => codeInput.focus(), 0);
        return false;
    }
}

function validatePass(passwordInput) {
    var password = passwordInput.value;
    var pattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$/;
    if (pattern.test(password)) {
        return true;
    } else {
        alert("Invalid password. Password must be greater than 8 characters,must contain a special character and have lowercase and uppercase letters.");
        window.setTimeout(() => passwordInput.focus(), 0);
        return false;
    }
}


function Submit(description, businessSector, startDate, endDate, budget, customer, sprintDuration) {
    stringLengthCheck("Description", description, 1, 50);
    stringLengthCheck("Business sector", businessSector, 1, 50);
    dateIsAfterToday(startDate);
    dateTwoIsAfterDateOne(startDate, endDate);
    valueRangeCheck("Budget", budget, 0, 9999999);
    alphanumeric("Customer", customer);
    valueRangeCheck("Sprint duration", sprintDuration, 1, 10);
    //validateEmail(customer);

}


function SubmitUS07(user, project, startDate, endDate, costPerHour, percentageOfAllocation, startSprint, endSprint) {
    validateEmail(user);
    validateCode(project);
    dateIsAfterToday(startDate);
    dateTwoIsAfterDateOne(startDate, endDate);
    valueRangeCheck("Cost Per Hour", costPerHour, 0, 9999);
    valueRangeCheck("Percentage Of Allocation", percentageOfAllocation, 0, 100);
    valueRangeCheck("Sprint duration", startSprint, 1, 10);
    valueRangeCheck("Sprint duration", endSprint, 1, 999);
}


function comparePasswords(firstPass, secondPass) {

    var firstPassValue = firstPass.value;
    var secondPassValue = secondPass.value;

    if (firstPassValue == secondPassValue) {
        return true;
    } else {
        window.alert("Passwords are different!")
        window.setTimeout(() => confirmPasswordInput.focus(), 0);
        return false;
    }

}
function validatePasswordRegexAndCompare (newPassword, confirmNewPassword){

    if (validatePass(newPassword)) {
        if (comparePasswords(newPassword,confirmNewPassword)){
            sendRequest();
        }
    }else {
        window.alert(("Passwords invalid"))
    }

    /*let rePassword = false;
    rePassword = validatePass(newPassword)

    if (rePassword){
         return comparePasswords(newPassword, confirmNewPassword);
         }
    else {
        window.alert("Passwords invalid")
    }*/

}
function generateCode() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
      var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
      return v.toString(16);
    });
  }

  console.log(generateCode());

