function iLikeIt(button, likeId) {
    var elementName = "likeCnt" + likeId;
    var likeCnt = document.getElementById(elementName);
    var currentLikeCnt = parseInt(likeCnt.textContent);

    if (button.textContent == '♡') {
        currentLikeCnt += 1;
        button.textContent = '♥';
    } else {
        currentLikeCnt -= 1;
        button.textContent = '♡';
    }

    likeCnt.textContent = currentLikeCnt;
}