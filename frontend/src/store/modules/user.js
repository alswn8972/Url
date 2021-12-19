import jwt_decode from 'jwt-decode';
import http from '@/utils/http';
import router from "@/routes/routes";
import VueSimpleAlert from "vue-simple-alert";
function reset(){ 
  return {
    userInfo: null,
    userName: "",
    userId:"",
    accessToken: null,
    idNumber:null,
    userIdAvailable:false,
    userEmailAvailable: false,
    userModifyEmail: false,
    userEmail : null,
  }
}
export default {

  namespaced: true,
  
  state: reset(),
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getLoginStatus(state) {
      return state.loginStatus;
    },
    getUserInfo(state) {
      var info = {
        userName : state.userName,
        userId : state.userId,
        userEmail : state.userEmail,
        idNumber :state.idNumber
      }
      state.userInfo = info; 
      return state.userInfo;
    },

    getUserName(state) {
      return state.userName;
    },
    getUserId(state) {
      return state.userId;
    },
    getUseIdAvailable(state){
      return state.userIdAvailable
    },
    getUserEmailAvailable(state){
      return state.userIdAvailable
    },
    getUserUniqueId(state) {
      return state.idNumber;
    },
    getModifyEmail(state) {
      return state.userModifyEmail;
    }
  },
  mutations: {
    LOGIN(state, payload) {
      const decode = jwt_decode(payload.accessToken);
      const info = decode.userInfo;
      state.accessToken = payload.accessToken;
      state.userId = info.id;
      state.userName = info.name;
      state.idNumber = info.idNumber;
      state.userEmail = info.userEmail;
      localStorage.setItem("accessToken", state.accessToken);
    },
    LOGOUT(state) {
      state.accessToken = null;
      localStorage.clear();
      Object.assign(state, reset())
    },
    USERINFO(state, payload) {
      state.userInfo = payload;
    },
    USERIDAVAIL(state, payload){
      state.userEmailAvailable = payload;
    },
    USEREMAILAVAIL(state, payload){
      state.userEmailAvailable = payload;
    },
    USERSEREMAIL(state , payload){
      state.userEmail = payload;
    },
    MODIFYEMAIL(state, payload)
    {
      state.userModifyEmail = payload;
    }

  },
  actions: {
    requestLogout({ commit }) {
      commit("LOGOUT");
    },
    requestRegister({ commit }, payload) {
      let body = payload
      http.post('/api/join', body).then(() => {
        VueSimpleAlert.fire({
          title: "SUCCESS",
          text: "회원가입이 완료 되었습니다.👋",
          type: "success",
        })
        router.push('/');
      }).catch((err) => {
        if (err.response.status == 409) {
          VueSimpleAlert.alert("중복된 아이디 입니다.😭");
        }
      });
    },
    requestLogin({ commit }, user) {
      http
        .post(`/api/auth/login`, user)
        .then(({ data }) => {
          commit("LOGIN", data);
          VueSimpleAlert.fire({
            title: "로그인 성공",
            text: "로그인이 완료 되었습니다.🙌",
            type: "success",
          })
          if (router.path !== '/')
            router.push('/');
        })
        .catch((err) => {
          if (err.response.status == 401) {
            VueSimpleAlert.fire({
              title: "로그인 실패",
              text: "아이디와 패드워드를 다시 확인해주세요.😭",
              type: "error",
            })
          } else if (err.response.status == 500) {
            VueSimpleAlert.fire({
              title: "로그인 실패",
              text: "회원정보가 없습니다.😱",
              type: "error",
            })
          } else {

          }
        });
    },
    requestUserInfo({ commit }) {
      const CSRF_TOKEN = localStorage.getItem("accessToken");
      http
        .get(`/api/users/me`, {
          headers: { "Authorization": 'Bearer ' + CSRF_TOKEN }
        })
        .then(({ data }) => {
          commit("USERINFO", data);

        })
        .catch((err) => {

        });
    },
    requestDuplicate({ commit }, userId) {
      http
        .get(`/api/users/` + userId)
        .then((res) => {
          commit("USERIDAVAIL", true);
          VueSimpleAlert.fire({
            title: "SUCCESS",
            text: "사용가능한 아이디입니다.",
            type: "success",
          })
        })
        .catch((error) => {
          if (error.response.data.statusCode == 409) {
            commit("USERIDAVAIL", false);
            VueSimpleAlert.fire({
              title: "FAIL",
              text: "이미 존재하는 아이디입니다.😱",
              type: "error",
            })
          }
        });
    },
    requestCode({ commit }, email) {
      http.get('/api/mail/code/' + email).
        then(({ data }) => {
          VueSimpleAlert.fire({
            title: "인증코드 전송 성공",
            text: "메일을 확인해주세요.🙌",
            type: "success",
          })
        }).catch((err) => {
          VueSimpleAlert.fire({
            title: "인증코드 전송 실패",
            text: "인증을 다시 시도해주세요.😱",
            type: "error",
          })
        });
    },
    requestCheckCode({ commit }, payload) {
      http.post('/api/mail/check', payload).
        then(({ data }) => {
          if (data.statusCode == 201)
            commit("USEREMAILAVAIL", true);
          VueSimpleAlert.fire({
            title: "인증 성공",
            text: "인증코드가 일치합니다.🙌",
            type: "success",
          })
        }).catch((err) => {
          commit("USEREMAILAVAIL", false);
          VueSimpleAlert.fire({
            title: "인증 실패",
            text: "인증코드를 다시 입력해주세요.😱",
            type: "error",
          })
        });
    },
    requsetModifyCheckCode({ commit }, payload) {
      http.post('/api/mail/check', payload).
        then(({ data }) => {
          if (data.statusCode == 201)
            commit("MODIFYEMAIL", true);
          VueSimpleAlert.fire({
            title: "인증 성공",
            text: "인증코드가 일치합니다.🙌",
            type: "success",
          })
        }).catch((err) => {
          commit("MODIFYEMAIL", false);
          VueSimpleAlert.fire({
            title: "인증 실패",
            text: "인증코드를 다시 입력해주세요.😱",
            type: "error",
          })
        });
    },
    requestModify({ commit }, user) {
      http
        .patch(`/api/user/patch` , user)
        .then(({ data }) => {
          commit("USERSEREMAIL", user.userEmail);
          commit("MODIFYEMAIL", false);
          VueSimpleAlert.fire({
            title: "수정 성공",
            text: "내용이 성공적으로 반영되었습니다.✍️",
            type: "success",
          })
          setTimeout(location.reload(),2000);
        })
        .catch(() => {

        });
    },

  },

}
