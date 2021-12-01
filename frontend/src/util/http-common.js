import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080";

axios.defaults.headers.post['Content-Type'] = 'application/json';

// axios 객체 생성
export default axios
