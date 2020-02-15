//Simple DOM manipulation and Event handling
//task a
changecolors(document.getElementsByTagName("div"));


function changecolors(list){
    for (let item of list) {
    item.style.backgroundColor = "#008000";
}
}

//task b
document.getElementById("button").onclick = changecolorsbutton;

function changecolorsbutton(){
    changecolor("div1", "red");
    changecolor("div2", "purple");
    changecolor("div3", "blue");
}

function changecolor(divid, color){
    document.getElementById(divid).style.backgroundColor = color;
}

