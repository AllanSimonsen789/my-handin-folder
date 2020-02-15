let boys = ["Peter", "Lars", "Ole", "Allan", "Jens", "Tobias"];

console.log(boys);

//task1
console.log(boys.filter(
        function (item) {
            if (item.length <= 3) {
                return item
            }
        }))
        
//task 2
console.log(boys.map(
        function (item) {
            return item.toUpperCase()
        }))
        
//task 3
function htmlcode(array){
    return "<ul><li>" + array.join("</li><li>") + "</li></ul>"
}

console.log(htmlcode(boys))

//task 4
let cars = [
  { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
  { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
  { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
  { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
  { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
];

//sorts year only higher than 1999
console.log(cars.filter(function (item){
    if(item.year > 1999){
        return item
    }
}))

//sorts cars from volvo
console.log(cars.filter(function (item){
    if(item.make === "Volvo"){
        return item
    }
}))

//sorts cars cheaper than 5000
console.log(cars.filter(function (item){
    if(item.price < 5000){
        return item
    }
}))

//task 4a
function makesqlstatement(array){
    let resultString = "";
    for(i = 0; i < cars.length; i++){
        resultString += "INSERT INTO cars ("+ Object.keys(array[i]).join(", ") +") VALUES (" + Object.values(array[i]).join(", ") + "); \n";
    }
    return resultString;
}

console.log(makesqlstatement(cars))