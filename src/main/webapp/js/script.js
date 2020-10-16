let civiliansChat = "civilians_chat_box"; // Хранит id HTML елемента отвечаючего за отображение сообщений в чате мирных жителей.
let mafiaChat = "mafia_chat_box"; // Хранит id HTML елемента отвечаючего за отображение сообщений в чате мафии.
let stompClient;
let name;
let isMafia = false;
let isNight;

function connect() {
    // Подключается через SockJS. Он сам решит использовать ли WebSocket
    // или имитировать их другими средствами
    const socket = new SockJS("http://localhost:8080/chat-messaging");
    stompClient = Stomp.over(socket);

    // Пытаемся подключиться, передавая пустой список заголовков - {}
    // И две функции: одну для обработки успешного подключения,
    // и вторую для обработки ошибки подключения
    stompClient.connect({}, afterConnect, onError);
    // Отключаем кнопку подключения, чтобы пользователь не
    // начинал несколько попыток подключения за раз
    document.getElementById("connect").disabled = true;
}

// Будем вызвано после установления соединения
function afterConnect(connection) {
    console.log("Успешное подключение: " + connection);
    stompClient.subscribe("/chat/civ_messages", getMessage);
    stompClient.subscribe("/chat/mafia_messages", getMessage);
    stompClient.subscribe("/chat/game_stat", getStat);
    // Теперь когда подключение установлено
    // Включаем кнопки для отправки сообщений и отключения от сервера
    document.getElementById("send_mafia_button").disabled = false;
    document.getElementById("send_civilians_button").disabled = false;
    document.getElementById("disconnect").disabled = false;
}

// Будет вызвано при ошибке установления соединения
function onError(error) {
    console.log("Не удалось установить подключение: " + error);
    // Включаем кнопку подключения обратно.
    // Вдруг в следующий раз подключиться получиться
    document.getElementById("connect").disabled = false;
}

//Получает информацию об игре с сервера.
function getStat(response) {
    const data = JSON.parse(response.body);
    isNight = data.night;
    console.log(isNight);
    setTimeInterface(isNight);
    sendTimeMessage(isNight)
}

function getMessage(response) {
    const data = JSON.parse(response.body);
    console.log("Получено сообщение: " + data.message);
    console.log("Название чата: " + data.role);
    console.log("From = " + data.from)
    if (data.role === "MAFIA") addToChat(data.from + " " + data.message, mafiaChat);
    else if (data.role === "CIVILIAN")
        addToChat(data.from + " " + data.message, civiliansChat);
}


function addToChat(text, chat) {
    const node = document.createElement("LI");  // Создаем элемент списка <li>
    node.setAttribute("class", "message");
    const textNode = document.createTextNode(text);  // Создаем текстовый элемент
    node.appendChild(textNode); // Вставляем текстовый внутрь элемента списка
    document.getElementById(chat).appendChild(node);
}

function disconnect() {
    stompClient.disconnect();
    // Отключаем кнопки отправки\отключения и включаем кнопку подключения
    document.getElementById("connect").disabled = false;
    document.getElementById("send_civilians_button").disabled = true;
    document.getElementById("send_mafia_button").disabled = true;
    document.getElementById("disconnect").disabled = true;
    // document.getElementById()
}

function sendMessage(chat, view) {
    const str = JSON.stringify({
        // Это работает на JQuery
        // 'message': $("#message_input_value").val()
        // А это на чистом JavaScript
        'message': document.getElementById(view).value,
        'from': name
    });
    console.log(str);
    stompClient.send(chat, {}, str);
}

//Метод установки ника пользователя в чате.
function setName() {
    name = document.getElementById("name_input").value;
    console.log("Name " + name + " set!");
    document.getElementById("set_name").disabled = true;
    document.getElementById("name_input").disabled = true;
}

// Метод устанавливающий начальную конфигурацию пользовательского интерфейса в зависимости от роли игрока.
function setRoleInterface(isMafia) {
    console.log(isMafia)
    if (isMafia === true) document.getElementById("mafia_chat_button").style.visibility = "visible";
    document.getElementById("civilians_chat_button").style.visibility = "visible";
    document.getElementById("civilians_chat_button").click();
}

//Метод выводящий сообщение в зависимости от времени в игре.
function sendTimeMessage(isNight) {
    if (isNight === false) {
        addToChat("День настал!", mafiaChat);
        addToChat("День настал!", civiliansChat);
    }
    if (isNight === true) {
        addToChat("Ночь настала!", mafiaChat);
        addToChat("Ночь настала!", civiliansChat);

    }
}

//Метод меняющий чат в зависимости от премени в игре.
function setTimeInterface(isNight) {
    if (isNight === true) {
        //document.body.style.backgroundColor = "#03133C";
        document.getElementById("send_mafia_button").disabled = false;
        document.getElementById("send_civilians_button").disabled = true;
    }
    if (isNight === false) {
        //document.body.style.backgroundColor = "#FFFFFF";
        document.getElementById("send_mafia_button").disabled = true;
        document.getElementById("send_civilians_button").disabled = false;
    }
}

//Метод для выбора роли при помощи кнопок.
function chooseRole(id) {
    if (id === "mafia_role_button") isMafia = true;
    else if (id === "civilians_role_button") isMafia = false;
    document.getElementById("mafia_role_button").disabled = true;
    document.getElementById("civilians_role_button").disabled = true;
    setRoleInterface(isMafia);
}

function startGame() {

}

function openCity(evt, cityName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

