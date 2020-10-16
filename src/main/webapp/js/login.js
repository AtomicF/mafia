function tryToLogin() {
    const jsonData = {
        'login': document.getElementById("login_input").value,
        'password': document.getElementById("password_input").value
    };
    let request = new XMLHttpRequest();
    request.open("POST", "/api/user/login", true);
    request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE) {
            if (request.status === 200) {
                const data = request.responseText
                console.log("Success. New token: " + data);
                document.cookie = "token" + "=" + data + ";path=/";
            } else if (request.status === 400) {
                const data = JSON.parse(request.responseText);
                switch (parseInt(data["result"])) {
                    case 3: {
                        console.log("Error: no such user");
                        break;
                    }
                    case 4: {
                        console.log("Error: wrong password");
                        break;
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
    };
    request.send(JSON.stringify(jsonData));
}