let url = "http://localhost:3333/api/users";


fetch(url)
    .then(res => res.json())
    .then(data => {
        makeTable(data);
    });


function makeTable(array) {
    console.log(array)
    let returnstring = "<thead><tr>"
    let keysarray = Object.keys(array[0]);
    keysarray.forEach(function (item) {
        returnstring += `<th>${item}</th>`
    })
    returnstring += "</tr></thead><tbody>"
    returnstring += array.map(user => `<tr><td>${user.id}</td><td>${user.age}</td><td>${user.name}</td><td>${user.gender}</td><td>${user.email}</td></tr>`)
    returnstring += "</tbody>"
    document.getElementById("allUsers").innerHTML = returnstring;
}

let inputfield = document.getElementById("inputSearchid")
let buttonSearch = document.getElementById("btnsearchid")

buttonSearch.addEventListener("click", fetchuser)

function fetchuser(){
    console.log("I WAS CLICKED")
    fetch("http://localhost:3333/api/users/" + inputfield.value )
    .then(res => {
        if(!res.ok){ //OK is false for statuscodes >= 400
          console.log("fuck")
        }
       res.json();
      })
     
    .then(data => {
        document.getElementById("user").innerHTML = `ID:${data.id} Age: ${data.age} Name: ${data.name} Gender: ${data.gender} Email: ${data.email}`
    })
}

let addButton = document.getElementById("addbtn")

addButton.addEventListener("click", addUser)

function addUser(){
    let inputname = document.getElementById("nameinput").value
    let inputage = document.getElementById("ageinput").value
    let inputgender = document.getElementById("genderinput").value
    let inputemail = document.getElementById("emailinput").value

    let options = {
        method:"POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            name: inputname,
            age: inputage,
            gender: inputgender,
            email: inputemail
        })
    }

    fetch("http://localhost:3333/api/users/", options)
}

let deletebtn = document.getElementById("deletebtn")

deletebtn.addEventListener("click", deleteUser)

function deleteUser(){
    let inputid = document.getElementById("idinput").value

    let options = {
        method:"DELETE",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
        }
    }

    fetch("http://localhost:3333/api/users/"+inputid, options)
}
