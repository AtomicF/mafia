function sendUserInformation() {
    const jsonData = {
        'login': document.getElementById("login_input").value,
        'password': document.getElementById("password_input").value,
        'passwordConfirmation': document.getElementById("repeated_password_input").value
    };

    console.log(jsonData);

    let request = new XMLHttpRequest();
    request.open("POST", "/api/user/register", true);
    request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');

    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE) {
            if (request.status === 200) {
                const data = request.responseText
                console.log("Successful registration and login. New token: " + data);
                document.cookie = "token" + "=" + data + ";path=/";
            } else if (request.status === 400) {
                const data = JSON.parse(request.responseText);
                switch (parseInt(data["result"])) {
                    case 1: {
                        console.log("Error: password and confirmation mismatch");
                        break;
                    }
                    case 2: {
                        console.log("Error: login is taken");
                        break;
                    }
                    case 3:
                    case 4: {
                        console.log("Error: internal logic failure. Registration was successful, but login  of the same user failed.")
                    }
                }
            } else if (request.status === 500) {
                const data = JSON.parse(request.responseText);
                switch (parseInt(data["result"])) {
                    case 1: {
                        console.log("Error: Internal logic error");
                        break;
                    }
                    case 2: {
                        console.log("Error: Database error");
                        break;
                    }
                }
            } else {
                console.log("Error: " + request.status);
            }
        }
    }
    request.send(JSON.stringify(jsonData));


}