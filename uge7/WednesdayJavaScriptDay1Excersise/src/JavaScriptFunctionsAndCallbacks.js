//JavaScript functions and Callbacks

//task 1)
function add(n1, n2){
   return n1 +n2;
}

var sub = function(n1,n2){
  return n1 - n2
} 

var cb = function(n1,n2, callback){
    if(typeof n1 === "number" && typeof callback === "function"){
        return "Result from the two numbers: "+n1+"+"+n2+"="+callback(n1,n2);
    }else{
        throw new Error("Wrong parameters, make sure the parameters are 2 numbers and a function")
    }

}


//task 2:
console.log( add(1,2) )
//return 3

console.log( add )
//Prints out the add function since no parameters where given

console.log( add(1,2,3) ) ;
//Runs the methos with the first 2 variables

console.log( add(1) );	 
//Returns NaN since the second parameter is not a number but undefined and therefore it cannot complete the function. it cannot add 1 to undefined

console.log( cb(3,3,add) );
//returns: "Result from the two numbers: 3+3=6" because it uses the cb function with the add function as callback

console.log( cb(4,3,sub) ); 
//returns: "Result from the two numbers: 4+3=1" because it uses the cb function with the sub function as callback


//task 3:
try{
//console.log(cb(3,3,add())); 
}catch(e){
    console.error(e.message)
}
//cannot run beacuse the add functions returns not a number since there is no parameters but the cb function excepts a function that can use with 3 and 3

console.log(cb(3,"hh",add));
//return: "Result from the two numbers: 3+hh=3hh" Beacuse of string concatenation.



//task 4:
function mul(n1, n2){
   return n1 * n2;
}

console.log(cb(3,3,mul));
//returns Result from the two numbers: 3+3=9


//task 5:
console.log(cb(3,3,function(n1,n2){
    return n1 / n2;
}))



