// var a = 1;
/*
{
    var a = 1;
    let b = 1;
}

console.log(a)
console.log(b)*/


/*
var a = 10;
var a = "sss";

let b = 2;
let b = "dwa";*/

/*console.log(a)
var a = 10*/

/*
console.log(b);
let b = 10;*/

/*
const a = 10;
a = 20;*/

// 解构
/*const arr = [1, 2, 3];
const [x, y, z] = arr;
console.log(x);
console.log(y);
console.log(z);*/


/*const person = {
    name: "张三",
    age : 20,
    email : "20@qq.com"
}*/

/*
console.log(person.name);
console.log(person.age);
console.log(person.email);

const {name, age, email} = person;
console.log(name);
console.log(age);
console.log(email);*/


/*
function print({name, age, email}) {
    console.log(name);
    console.log(age);
    console.log(email);
}

print(person);*/

// 链判断
/*let message = {
    body : {
        user : {
            firstName : "John"
        }
    }
};
let first = message?.body?.user?.firstName || "default"
console.log(first)*/

/*function add(a = 0 , b = 0) {
    return a + b;
}

console.log(add(1, 2))
console.log(add(1))*/


// 箭头函数
/*function print1 (arg){
    console.log(arg)
}

let print2 = function (arg){
    console.log(arg)
}

let print3 = (arg) => {
    console.log(arg)
}*/

/*let sum = (a, b) => {
    let x = a + b
    console.log(x)
}

sum(1, 3)*/

// 模板字符串
/*
let name = "时荣宝"
let age = 20
let str = `你好，我叫${name}，今年${age}岁了`
console.log(str)*/

// let url = "https://mdn.github.io/learning-area/javascript/apis/fetching-data/can-store/products.json"
/*console.log(1)
const promise = fetch(url);
promise.then(resp => {
    resp.json().then(data => {
        console.log(data)
    })
}) // 调用成功后执行
console.log(2)
promise.catch(err => {
    console.log("远程抓取失败", err)
})
// promise.catch() // 调用失败后执行*/

/*function get(url) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: url,
            type: "GET",
            success(result) {
                resolve(result);
            },
            error(error) {
                reject(error);
            }
        });
    })
}

get("").then(resp => console.log(resp))*/

/*function func1() {
    return new Promise((resolve, reject) => {
        let x = 101;
        if (x % 2 === 0) {
            resolve(x)
        } else {
            reject("X不是偶数")
        }
    })
}

func1().then(resp => console.log(resp)).catch(err => console.log(err));*/

/*
async function func1() {
    let x = 102;
    if (x % 2 === 0) {
        return x;
    } else {
        throw new Error("x 不是偶数");
    }
}

func1().then(resp => console.log("then ", resp)).catch(err => console.log("err ", err))*/

/*async function fetchProducts() {
    let promise1 = await fetch(url)
    let promise2 = await promise1.json();
    console.log("promise2", promise2)
}

fetchProducts();*/



















