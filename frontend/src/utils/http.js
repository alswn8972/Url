import axios from 'axios';

export default axios.create({
    baseURL: 'https://3.38.93.132:8080/',
    baseURL:'https://localhost:8080/',
    headers:{
        'Content-type':'application/json'
    },
});
