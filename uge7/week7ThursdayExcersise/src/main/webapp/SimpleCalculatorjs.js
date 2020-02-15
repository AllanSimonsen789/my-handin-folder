document.getElementById("buttons").onclick = buttonclick;

function buttonclick(element) {
    if (element.target.innerHTML === "=") {

    } else {
        document.getElementById("display").innerHTML += element.target.innerHTML;
    }
}

