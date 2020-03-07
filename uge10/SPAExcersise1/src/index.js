import 'bootstrap/dist/css/bootstrap.css'
import jokes from "./jokes";

const allJokes = jokes.getJokes().map(joke => "<li>"+joke+"</li>");
document.getElementById("jokes").innerHTML = allJokes.join("");

const searchField = document.getElementById("idsearch");
const searchBtn = document.getElementById("searchButton");

searchBtn.addEventListener("click",(event) =>{
    event.preventDefault
    document.getElementById("joke_id").innerHTML = jokes.getJokeById(searchField.value - 1) 
})

const addField = document.getElementById("addJoke");
const addBtn = document.getElementById("addButton");

addBtn.addEventListener("click",(event) =>{
    event.preventDefault
    jokes.addJoke(addField.value)
    document.getElementById("addConfirmation").innerHTML = "Joke added: " + addField.value
    const allJokes = jokes.getJokes().map(joke => "<li>"+joke+"</li>");
    document.getElementById("jokes").innerHTML = allJokes.join("");
})
let url = "https://studypoints.info/jokes/api/jokes/period/hour";
const qouteButton = document.getElementById("button");
const qoutediv = document.getElementById("mainDiv");

qouteButton.addEventListener("click", fetching)

function fetching() {
    fetch(url)
            .then(res => res.json()) 
            .then(data => {
                qoutediv.innerHTML = data.joke
                
            });
}

document.addEventListener('click', function (event) {
	if (!event.target.closest('g')) return;
	document.getElementById("pressed").innerHTML = event.target.closest('g').id;
}, false);


