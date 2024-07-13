import axios from 'axios';

// 创建 axios 实例
const request = axios.create({
    baseURL: 'http://localhost:5000', // 服务器地址，根据实际情况调整
    timeout: 10000, // 请求超时时间
    headers: {
        'Content-Type': 'application/json', // 默认传输JSON格式
        // 可添加其他默认需要携带的头部信息
    }
});

// 请求拦截器
request.interceptors.request.use(
    config => {
        return config;
    },
    error => {
        // 处理请求错误
        return Promise.reject(error);
    }
);

// 响应拦截器
request.interceptors.response.use(
    response => {
        // 只返回数据部分
        return response.data;
    },
    error => {
        if (error.response) {
            // 服务器返回了响应，可以根据response状态码进行不同处理
            console.error(`API Error ${error.response.status}: ${error.response.data}`);
        } else if (error.request) {
            // 请求已经发出去了，但没有收到服务器回应
            console.error('No response received for API request.');
        } else {
            // 在设置请求时触发了某些错误
            console.error('Error setting up API request:', error.message);
        }
        return Promise.reject(error);
    }
);

export default request;
