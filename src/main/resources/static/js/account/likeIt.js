function iLikeIt(button) {
    var state = 0;
    var btnId = document.getElementsById(button);

    if (button.textContent == '♡') {
        button.textContent = '♥';
        state = 1;
    } else {
        button.textContent = '♡';
    }

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/main", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    
    var data = JSON.stringify({
        btnState : state,
        btnId : btnId
    });
    xhr.send(data);
}