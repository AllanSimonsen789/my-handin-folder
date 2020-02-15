//The JavaScript Array Excersise

//task a
var boys = ["Peter", "lars", "Ole"];
var girls = ["Janne", "hanne", "Sanne"];
let allArray = new Array();

//task b
allArray = boys.concat(girls);

console.log(allArray);

//task c
let comma = allArray.join(", ");
let hyphen = allArray.join(" - ");

console.log(comma);
console.log(hyphen)

//task d
allArray.push("Lone", "Gitte")

console.log(allArray)

//task e
allArray.unshift("Hans", "Kurt")

console.log(allArray)

//task f
allArray.shift();

console.log(allArray)

//task g
allArray.pop();

console.log(allArray)

//task h
allArray.splice(3, 2)

console.log(allArray)

//task i

allArray = allArray.reverse()

console.log(allArray)

//task J & k
allArray.sort(function (a, b) {
    return a.toLowerCase().localeCompare(b.toLowerCase());
})

console.log("sort:" + allArray)

//task I
allArray = allArray.map(
        function (item) {
            return item.charAt(0).toUpperCase() + item.substring(1).toLowerCase()
        })

console.log(allArray)


//task m
allArray = allArray.filter(
        function (item) {
            if (item.includes("l") || item.includes("L")) {
                return item
            }
        })

console.log(allArray)