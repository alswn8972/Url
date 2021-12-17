
import http from '@/utils/http';
import router from "@/routes/routes";
import VueSimpleAlert from "vue-simple-alert";
export default {
namespaced: true,
state: {
    urlList: '',
    urlCheck: [],
    availableAdd: false,
    urlHistory: {
        success: '',
        fail: '',
    },
    urlNoHistory: null,
    mailGroup:null,
    },
getters: {
        getUrlList:(state)=> {
            return state.urlList;
        },
        getUrlCheck:(state)=> {
            return state.urlCheck;
        },
        getUrlInit:(state)=> {
            state.urlCheck = null;
            return state.urlCheck;
        },
        getUrlAvailable:(state)=>{
            return state.availableAdd;
        },
        getInitAvailable:(state)=> {
            state.availableAdd = false;
            return state.availableAdd;
        },
        getUrlHistory:(state)=> {
            return state.urlHistory;
        },
        getUrlNoHistory: (state) => {
            return state.urlNoHistory
        },
        getUrlMails: (state) => {
            return state.mailGroup;
        },
        getModifyAvailable:(state)=>{
            console.log("getA" , state.modifyAvilable)
            return state.modifyAvilable;
        },

        
    },
    mutations: {
        URLLIST(state, payload) {
            state.urlList = payload;
            console.log(state.urlList)
            for (var i = 0; i < payload.length; i++) {
                state.urlList[i].urlCheckTime=state.urlList[i].urlCheckTime.substring(5, 7)+'/'+state.urlList[i].urlCheckTime.substring(8, 10)+' '+state.urlList.urlCheckTime[i].substring(11, 13)+'시'+state.urlList.urlCheckTime[i].substring(14, 16)+'분';
            }
        },
        URLCHECK(state, payload) {
            state.urlCheck = payload;
        },
        URLAVAILABLE(state, payload) {
            state.availableAdd = payload;
        },
        URLHISTORY(state, payload) {
            state.urlHistory = payload;
            var success = 0;
            var fail = 0;
            var total = payload.length;
            for (var i = 0; i < payload.length; i++) {
                if (state.urlHistory[i].statusCode == 200) {
                    success++;
                } else {
                    fail++;
                }
                state.urlHistory[i].createdDate=state.urlHistory[i].createdDate.substring(5, 7)+'/'+state.urlHistory[i].createdDate.substring(8, 10)+' '+state.urlHistory[i].createdDate.substring(11, 13)+'시'+state.urlHistory[i].createdDate.substring(14, 16)+'분';
            }
            state.urlHistory.success = success;
            state.urlHistory.fail = fail;
            state.urlHistory.total = total;
            console.log(state.urlHistory.total)
            
        },
        URLNOHISTORY(state, payload) {
            state.urlNoHistory = payload;
        },
        URLGETMAILGRUOP(state, payload) {
            state.mailGroup = payload.emails;
        },
        MODIFYAVAILABLE(state,payload){
            console.log("수정",payload)
            state.modifyAvilable = payload;
        },

    
    },
    actions: {
        requestUrlList({ commit }, userId) {
            http
                .get(`/api/url/list/`+userId)
                .then(({ data }) => {
                    commit("URLLIST", data);
                })
                .catch((err) => {

                });
        },
        requestAddUrl({ commit }, body) {
            console.log(body);
            //const CSRF_TOKEN = localStorage.getItem("accessToken");
            http
                .post(`/api/url/add/`, body)
                .then(({ data }) => {
                    if (data.statusCode == 201) {
                        VueSimpleAlert.fire({
                            title: "Save",
                            text: "등록이 완료되었습니다.😀",
                            type: "success",
                        })
                    }
                })
                .catch((err) => {

                });
        },
        requestCheckUrl({ commit }, url) {
                http
                .post(`/api/url/check/`, url)
                .then(({ data }) => {
                    commit("URLCHECK", data);
                })
                .catch((err) => {
                    console.log(err);
                    // if (err.response.status == 404) {
                    //     let data = { statusCode: 400, urlAddress:url};
                    //     commit("URLCHECK", data);
                    // }
                });
        },
        requestAddtoCheckUrl({ commit }, url) {
            http
                .post(`/api/url/check/`, url)
                .then(({ data }) => {
                    if (data[0].statusCode != 200) {
                        commit("URLAVAILABLE", false);
                        VueSimpleAlert.fire({
                            title: "Warning",
                            text: "Url 상태를 다시한번 확인해주세요.👋",
                            type: "error",
                        })
                    } else {
                        commit("URLAVAILABLE", true);
                        VueSimpleAlert.fire({
                            title: "OK",
                            text: "등록 가능한 Url 입니다.😀",
                            type: "success",
                        })
                    }
                })
                .catch((err) => {

                    if (err.response.status == 404) {
                        commit("URLAVAILABLE", false);
                        VueSimpleAlert.fire({
                            title: "Warning",
                            text: "Url 상태를 다시한번 확인해주세요.👋",
                            type: "error",
                        })
                    }
                });
        },
        requestHistory({ commit }, urlId) {
            http
                .get(`/api/url/history/`+urlId)
                .then(({ data }) => {
                    commit("URLHISTORY", data);
                    commit("URLNOHISTORY", false);
                })
                .catch((err) => {
                    console.log(err.response.status);
                    if (err.response.status == 400) {
                        commit("URLNOHISTORY", true);
                    }
                });
        },
        requestGetMailGroup({ commit }, urlId) {
            http.get(`/api/reservation/` + urlId)
            .then(({ data }) => {
                commit("URLGETMAILGRUOP", data)
            })
            .catch((err) => {

            });
        },
        requestRegisterMail({ commit }, body) {
            console.log(body)
            http.post(`/api/reservation/add` , body)
            .then(({ data }) => {
                if (data.statusCode == 201) {
                    VueSimpleAlert.fire({
                        title: "성공",
                        text: "이메일 추가 완료!😀",
                        type: "success",
                    })
                }
            })
            .catch((err) => {

            });
        },
        requestModitoCheckUrl({ commit }, url) {
            http
                .post(`/api/url/check/`, url)
                .then(({ data }) => {
                    if (data[0].statusCode != 200) {
                        commit("MODIFYAVAILABLE", false);
                        VueSimpleAlert.fire({
                            title: "Warning",
                            text: "Url 상태를 다시한번 확인해주세요.👋",
                            type: "error",
                        })
                    } else {
                        commit("MODIFYAVAILABLE", true);
                        VueSimpleAlert.fire({
                            title: "OK",
                            text: "수정 가능한 Url 입니다.😀",
                            type: "success",
                        })
                        
                    }
                })
                .catch((err) => {

                    if (err.response.status == 404) {
                        commit("MODIFYAVAILABLE", false);
                        VueSimpleAlert.fire({
                            title: "Warning",
                            text: "Url 상태를 다시한번 확인해주세요.👋",
                            type: "error",
                        })
                    }
                });
        },
        requestPactchUrl({commit},url){
            console.log(url)
            http.patch(`/api/url/patch/`,url)
            .then(({data})=> {
                if (data.statusCode == 201) {
                    VueSimpleAlert.fire({
                        title: "Save",
                        text: "수정이 완료되었습니다.😀",
                        type: "success",
                    })
                    commit("MODIFYAVAILABLE", false);
                }

            })
            .catch((err) => {
                console.log(err)

            })
        },
        requestDeleteUrl({commit},delurl)
        {
            http.delete('/api/url/delete',delurl)
            .then(({data})=> {
                if (data.statusCode == 201) {
                    VueSimpleAlert.fire({
                        title: "Delete",
                        text: "삭제이 완료되었습니다.😀",
                        type: "success",
                    })
                    
                }

            })
            .catch((err) => {
                console.log(err)

            })
        }
    },
}
