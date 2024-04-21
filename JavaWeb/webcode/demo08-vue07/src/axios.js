import axios from 'axios'

// 使用axios函数创建一个可以发送请求的实例对象
const instance = axios.create({
    // 设置请求的基础路径
    baseURL: "https://api.uomg.com/"
    // 超时时间
    // timeout: 10000  // 毫秒
})

// 设置请求拦截器
instance.interceptors.request.use(
    // 第一个函数  设置请求的信息
    config => {
        console.log("请求前拦截器");
        config.headers.Accept = "application/json, text/plain, text/html,*/*"

        // 设置完毕之后必须返回config
        return config
    },
    // 第二个函数  请求错误的时候这里返回的数据就是axios接收到的信息
    error => {
        console.log("fuck you");
        // 最后要返回一个失败状态的promise
        return Promise.reject("something wrong")
    }
)

instance.interceptors.response.use(
    // 第一个函数   响应状态码为200时执行的函数
    response => {
        // 处理相应数据
        // 最终要返回这个response
        return response;
    },
    // 第二个函数   响应状态码非200时执行的函数
    error => {
        // 最后一定要响应一个的promise
        return Promise.reject("something wrong")
    }
)
export default instance