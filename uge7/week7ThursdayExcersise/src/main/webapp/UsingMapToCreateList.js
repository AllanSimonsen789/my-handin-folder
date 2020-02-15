//task 1
let boys = ["Peter", "Lars", "Ole", "Allan", "Jens", "Tobias"];

document.getElementById("main").innerHTML = htmlcode(boys)

function htmlcode(array) {
    return "<ul><li>" + array.join("</li><li>") + "</li></ul>"
}

//task 2
function addtoarray(string) {
    boys.push(string)
    document.getElementById("main").innerHTML = htmlcode(boys)
}

function submitname(event) {
    event.preventDefault();
    addtoarray(document.getElementById("name").value);

}

//task 3
document.getElementById("deletefirst").onclick = deletefirst
document.getElementById("deletelast").onclick = deletelast

function deletefirst() {
    boys.shift()
    document.getElementById("main").innerHTML = htmlcode(boys)

}
function deletelast() {
    boys.pop();
    document.getElementById("main").innerHTML = htmlcode(boys)

}

