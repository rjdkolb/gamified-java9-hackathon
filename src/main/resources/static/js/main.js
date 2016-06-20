function getCurrentUser() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            console.info("Got user " + xhttp.responseText);
            document.getElementById("userid").value = xhttp.responseText;
        }
    };
    xhttp.open("POST", "go/", true);
    var data = new FormData();
    data.append('source', 'blank');
    xhttp.send(data);
}
