// 用于定义共享的pinia数据
import {
    defineStore
} from 'pinia'

// 定义一个响应式对象数据Person

export const definedPerson = defineStore({
    id: "personPinia", // 当前数据的id 必须全局唯一
    state: () => {
        return { // 返回的响应式数据
            username: "zhangsan",
            age: 20,
            hobbies: ["唱", "跳", "rap", "篮球"]
        }
    },
    getters: {
        // 专门定义一个获得数据或者是 使用数据计算结果的一些函数,这里函数不要修数据
        getAge() {
            return this.age
        },
        getHobbiesCount() {
            return this.hobbies.length
        }
    },
    actions: {
        // 专门定义一些修改数据的函数
        doubleAge() {
            this.age = this.age * 2
        }
    }
})