function iLikeIt(button) {
    if (button.textContent == '♡') {
        button.textContent = '♥';
    } else {
        button.textContent = '♡';
    }
}