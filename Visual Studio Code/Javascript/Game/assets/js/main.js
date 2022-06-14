import * as myModule from "./module/data.js";
var list_1 = [];
var list_2 = [];

for (var i = 0; i < 10; i++) {
    list_1.push(Math.floor(Math.random()*20 + 1));
    list_2.push(Math.floor(Math.random()*20 + 1));
}

console.log(list_1, list_2);