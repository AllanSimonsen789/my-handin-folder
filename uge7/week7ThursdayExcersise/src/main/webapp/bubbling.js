//task a, b, c
document.getElementById("outer").onclick = genericClick;
        
function genericClick(element){
    document.getElementById("para").innerHTML = "Hi from " + this.id + " and " + element.target.id;
    
}




