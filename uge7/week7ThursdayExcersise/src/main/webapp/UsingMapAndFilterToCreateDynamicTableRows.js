let cars = [
  { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
  { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
  { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
  { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
  { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
];

document.getElementById("tablediv").innerHTML = maketable(cars);

function maketable(array){
    let returnstring = "<table><tr>"
    let keysarray = Object.keys(array[0]);
    keysarray.forEach(function(item){
        returnstring += "<th>" + item + "</th>"
    })
    returnstring += "</tr>"
    array.forEach(function(item){
        let values = Object.values(item)
        returnstring += "<tr>"
        values.forEach(function(value){
            returnstring += "<td>" + value + "</td>"
        })
        returnstring += "</tr>"
    })
    returnstring += "</table>"
    return returnstring
}

function submitprice(event){
    event.preventDefault();
    document.getElementById("tablediv").innerHTML = maketable(cars.filter(function (item){
    if(item.price < document.getElementById("price").value){
        return item
    }
}))
}