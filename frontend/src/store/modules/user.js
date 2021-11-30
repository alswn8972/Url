import jwt_decode from 'jwt-decode';
import http from '@/util/http-common';
import router from "@/router/router";
import VueSimpleAlert from "vue-simple-alert";
import axios from 'axios';
export default {
  namespaced: true,
  state: {
    userInfo: null,
    accessToken: "",
    accessEstate: null,
    estateInfo: null,
    myfavoriteList: null,
    residenceInfo: null,
    totalPage: 0,
  },
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getAccessInfo(state) {
      return state.accessEstate;
    },
    getLoginStatus(state) {
      return state.loginStatus;
    },
    getUserInfo(state) {
      return state.userInfo;
    },
    getEstateInfo(state) {
      return state.estateInfo;
    },
    getMyfavoriteList(state) {
      return state.myfavoriteList;
    },
    getResidenceInfo(state) {
    
      return state.residenceInfo;
    },
    getTotalPage(state) {
      return state.totalPage;
    }
  },
  mutations: {
    LOGIN(state, payload) {
      const decode = jwt_decode(payload.accessToken);
      const info = decode.userInfo;
      state.accessEstate = info.isEstate;
      state.accessToken = payload.accessToken;
      localStorage.setItem("accessToken", state.accessToken);
      localStorage.setItem("accessEstate", state.accessEstate);
    },
    USERINFO(state, payload) {
      state.userInfo = payload;
    },
    ESTATEINFO(state, payload) {
      state.estateInfo = payload;
    },
    FAVORITELIST(state, payload) {
      state.myfavoriteList = payload;
    },
    RESIDENCEINFO(state, payload) {
   
      state.residenceInfo = payload.residenceInfo;
      state.totalPage = payload.pageSize;
    }
  },
  actions: {
    requsetFavoriteList({ commit }) {
      const CSRF_TOKEN = localStorage.getItem("accessToken");
      if (CSRF_TOKEN != null) {
        http.get('/api/v1/favorites', {
          headers: { "Authorization": 'Bearer ' + CSRF_TOKEN }
        }).then((res) => {

          commit("FAVORITELIST", res.data.userFavoriteList);
       
        })
      }
    },
    requestResiSaled(context, payload) {
     
      http
        .delete("/api/v1/residences?residenceId=" + payload)
        .then(({ data }) => {
        
        })
        .catch((err) => {
         
        });
    },
    requestRegister(context, payload) {
      let body = payload

      http.post('/api/v1/users', body).then(() => {
        VueSimpleAlert.fire({
          title: "SUCCESS",
          text: "회원가입이 완료 되었습니다.👋",
          type: "success",
        })
        router.push('/login');
      }).catch((err) => {
        if (err.response.status == 409) {
          VueSimpleAlert.alert("중복된 아이디 입니다.😭");
        }
      });
    },
    requestLogin({ commit }, user) {
      http
        .post(`/api/v1/auth/login`, user)
        .then(({ data }) => {
          commit("LOGIN", data);
          VueSimpleAlert.fire({
            title: "로그인 성공",
            text: "로그인이 완료 되었습니다.🙌",
            type: "success",
          })
          router.push('/');
        })
        .catch((err) => {

          if (err.response.status == 401) {
            VueSimpleAlert.fire({
              title: "로그인 실패",
              text: "아이디와 패드워드를 다시 확인해주세요.😭",
              type: "error",
            })
          } else if (err.response.status == 404) {
            VueSimpleAlert.fire({
              title: "로그인 실패",
              text: "회원정보가 없습니다.😭",
              type: "error",
            })
          } else {
          
          }
        });
    },
    requestUserInfo({ commit }) {
      const CSRF_TOKEN = localStorage.getItem("accessToken");
      http
        .get(`/api/v1/users/me`, {
          headers: { "Authorization": 'Bearer ' + CSRF_TOKEN }
        })
        .then(({ data }) => {
          commit("USERINFO", data);
         
        })
        .catch((err) => {
       
        });
    },
    requestModify({ commit }, user) {
    
      http
        .patch(`/api/v1/users/` + user.userId, user)
        .then(({ data }) => {
          commit("USERINFO", data);
          VueSimpleAlert.fire({
            title: "수정 성공",
            text: "내용이 성공적으로 반영되었습니다.✍️",
            type: "success",
          })
          window.location.reload();
        })
        .catch(() => {

        });
    },
    requestDuplicate({ commit }, userId) {
      http
        .get(`/api/v1/users/` + userId)
        .then((res) => {
          VueSimpleAlert.fire({
            title: "SUCCESS",
            text: "사용가능한 아이디입니다.",
            type: "success",
          })
          commit('user/USERID', true, { root: true });
        })
        .catch((error) => {
          if (error.response.data.statusCode == 409) {
            commit('user/USERID', false, { root: true });
            VueSimpleAlert.fire({
              title: "FAIL",
              text: "이미 존재하는 아이디입니다.",
              type: "error",
            })
          }
        });
    },
    requestEstate({ commit }, estateNum) {
     
        http.get(`/api/v1/users/estate`, { params: { registrationNumber: estateNum } })
        .then((res) => {
          commit("user/ESTATEINFO", res.data.estateInfo, { root: true });
          VueSimpleAlert.fire({
            title: "SUCCESS",
            text: "사업자 번호가 확인되었습니다.",
            type: "success",
          })



            .catch((error) => {
              if (error.response.data.statusCode == 500) {
                commit("ESTATENUMBER", false);
                VueSimpleAlert.fire({
                  title: "FAIL",
                  text: "사업자 번호가 존재하지 않습니다.",
                  type: "error",
                })
              }
            });

        });
    },
    requestDuplicate({ commit }, userId) {
      http
        .get(`/api/v1/users/` + userId)
        .then((res) => {
          VueSimpleAlert.fire({
            title: "SUCCESS",
            text: "사용가능한 아이디입니다.",
            type: "success",
          })
          commit('user/USERID', true, { root: true });
        })
        .catch((error) => {
          if (error.response.data.statusCode == 409) {
            commit('user/USERID', false, { root: true });
            VueSimpleAlert.fire({
              title: "FAIL",
              text: "이미 존재하는 아이디입니다.",
              type: "error",
            })
          }
        });
    },
    requestEstate({ commit }, estateNum) {
   
      http
        .get(`/api/v1/users/estate`, { params: { registrationNumber: estateNum } })
        .then((res) => {
          commit("user/ESTATEINFO", res.data.estateInfo, { root: true });
          VueSimpleAlert.fire({
            title: "SUCCESS",
            text: "사업자 번호가 확인되었습니다.",
            type: "success",
          })

        })
        .catch((error) => {
          if (error.response.data.statusCode == 500) {
            commit("ESTATENUMBER", false);
            VueSimpleAlert.fire({
              title: "FAIL",
              text: "사업자 번호가 존재하지 않습니다.",
              type: "error",
            })
          }
        });
    },
    requestRegistResi({ commit }, residence) {
      var formData = new FormData();

      formData.append('area', residence.area);
      formData.append('buildingFloor', residence.buildingFloor);
      formData.append('cost', residence.cost);
      formData.append('deposit', residence.deposit);
      formData.append('direction', residence.direction);
      formData.append('dong', residence.dong);
      formData.append('estateId', residence.estateId);

      formData.append('jeonseCost', residence.jeonseCost);
      formData.append('wolseCost', residence.wolseCost);
      formData.append('lat', residence.lat);
      formData.append('lon', residence.lon);
      formData.append('manageCost', residence.manageCost);
      formData.append('myFloor', residence.myFloor);
      formData.append('name', residence.name);
      formData.append('residenceCategory', residence.residenceCategory);
      formData.append('residenceType', residence.residenceType);
      formData.append('structure', residence.structure);
      if (residence.feature.length > -1) {
        for (let i = 0; i < residence.feature.length; i++) {
          formData.append(`feature[${i}]`, residence.feature[i]);
        }
      }
      if (residence.thumbnails.length > -1) {
        for (let i = 0; i < residence.thumbnails.length; i++) {
          const imageForm = residence.thumbnails[i]
          formData.append(`thumbnails[${i}]`, imageForm);
        }
      }
      http
        .post(`/api/v1/residences`, formData,
          {
            headers: { 'Content-Type': 'multipart/form-data' },
          })
        .then(({ data }) => {
          this.router.go();
        })
        .catch((err) => {
       
        });
    },
    requestGetResi({ commit }, data) {
     
      http
        .post(`/api/v1/residences/estateIds`, data)
        .then(({ data }) => {
        
          commit("user/RESIDENCEINFO", data, { root: true });

        })
        .catch((err) => {
          
        });
    },
    requestModifyResi({ commit }, data) {
      
      http
        .patch("/api/v1/residences?residenceId=" + data.residenceId, data)
        .then(({ data }) => {
         
        })
        .catch((err) => {
        
        });
    }
  },

}
