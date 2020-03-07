//-----Task 1-------
//Task 1A
let nameArray = ["Lars", "Jan", "Peter", "Bo", "Frederik", "Allan", "Abe"]
let newNameArray = nameArray.filter(function(name){
    if(name.toLowerCase().includes("a")) return name;
})

console.log(newNameArray)

//Task 1B
let newMapArray = nameArray.map(name => name.toLowerCase().split("").reverse().join("")).reverse();

console.log(newMapArray)

//Task 2(yellow not completed)

//Task 3(Red not completed)

//-----Task 4-------
//Task 4a
let array = [1,3,5,10,11]

let newArray = array.map((number, index, array) =>
    (index === array.length - 1 ? number : number + array[++index])
)
console.log("Hello world")
console.log(newArray);
//task 4b

let names = ["Lars", "Peter", "Jan", "Bo"]

let string = "<nav>" + names.map( name => `<a href=\"\">${name}</a>`).join("\n") + "</nav>"

console.log(string)

//task 4c
let persons = [{name:"Lars",phone:"1234567"}, {name: "Peter",phone: "675843"}, {name: "Jan", phone: "98547"},{name: "Bo", phone: "79345"}];

let personstring = persons.map(person => person.name + " " + person.phone).join("<br>");

console.log(personstring)

//task 4d

document.getElementById("names").innerHTML = string;
document.getElementById("names2").innerHTML = personstring;

//task 4e

document.getElementById("filterbutton").addEventListener("click", function(){
    persons = persons.filter(function(person){
        if(person.name.toLowerCase().includes("a")) return person;
    })
    personstring = persons.map(person => person.name + " " + person.phone).join("<br>");
    document.getElementById("names2").innerHTML = personstring;
})

//------Task 5--------
//task 4a
let all = ["Lars", "Peter", "Jan", "Bo"]

let singlestring = "#" + all.join(", #")

console.log(singlestring)

//task 4b
let numbers = [2,3,67,33]

let numbersum = numbers.reduce(function(accumulator, price){
    return accumulator + price
})
console.log(numbersum)

