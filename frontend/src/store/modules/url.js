
import http from '@/utils/http';
import router from "@/routes/routes";
import VueSimpleAlert from "vue-simple-alert";
function reset(){ 
    return {
        urlList: '',
        urlCheck: [],
        availableAdd: false,
        modifyAvilable: false,
        urlHistory: {
            success: '',
            fail: '',
        },
        urlNoHistory: 0,
        mailGroup:null,
    }
}
export default {
namespaced: true,
state: reset(),
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
        getUrlAvailable: (state) => {
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
            return state.modifyAvilable;
        },

        
    },
    mutations: {
        URLLIST(state, payload) {
            state.urlList = payload;
            for (var i = 0; i < payload.length; i++) { 
                state.urlList[i].urlCheckTime = payload[i].urlCheckTime.substring(5, 7) + '월 ' + payload[i].urlCheckTime.substring(8, 10) + '일 ' + payload[i].urlCheckTime.substring(11, 13) + '시 ' + payload[i].urlCheckTime.substring(14, 16) + '분';
            }
        },
        URLCHECK(state, payload) {
            state.urlCheck = payload;
        },
        // URLERRORCHECK(state, payload) { 
        //     const body = {
        //         statusCode: '',
        //         urlAddress:'',
        //     }
        //     if (payload.protocol == 1) {
        //         body.urlAddress = 'http://' + payload.address;
        //         body.statusCode = 404;
        //     } else { 
        //         body.urlAddress = 'https://' + payload.address;
        //         body.statusCode = 404;
        //     }
        //     state.urlCheck = body;
        // },
        URLAVAILABLE(state, payload) {
            state.availableAdd = payload;
        },
        URLHISTORY(state, payload) {
            console.log(payload)
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
                state.urlHistory[i].createdDate=state.urlHistory[i].createdDate.substring(5, 7)+ '월 '+state.urlHistory[i].createdDate.substring(8, 10)+'일 '+state.urlHistory[i].createdDate.substring(11, 13)+'시 '+state.urlHistory[i].createdDate.substring(14, 16)+'분';
            }
            state.urlHistory.success = success;
            state.urlHistory.fail = fail;
            state.urlHistory.total = total;
        },
        URLNOHISTORY(state, payload) {
            state.urlNoHistory = payload;
        },
        URLGETMAILGRUOP(state, payload) {
            state.mailGroup = payload.emails;
        },
        MODIFYAVAILABLE(state,payload){
            state.modifyAvilable = payload;
        },
        RESET(state) {
            Object.assign(state, reset())
        },
    },
    actions: {
        requestReset({commit}) { 
            commit("RESET");
        },
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
            http
                .post(`/api/url/add/`, body)
                .then(({ data }) => {
                    setTimeout(commit("URLAVAILABLE", false),1000);

                    if (data.statusCode == 201) {
                        VueSimpleAlert.fire({
                            title: "Save",
                            text: "등록이 완료되었습니다.😀",
                            type: "success",
                        })
                    }
                    location.reload();
                })
                .catch((err) => {

                });
        },
        requestCheckUrl({ commit }, url) {
                http
                .post(`/api/url/check/`, url)
                    .then(({ data }) => {
                        console.log(data);
                    commit("URLCHECK", data);
                })
                .catch((err) => {
                    commit("URLCHECK", null);
                    VueSimpleAlert.fire({
                        title: "Error",
                        text: "비정상 적인 URL 입니다. (404에러)",
                        type: "error",
                    })
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
        requestResetHistory({commit}){
            commit("URLNOHISTORY", 0);
        },

        requestHistory({ commit }, urlId) {
            http
                .get(`/api/url/history/`+urlId)
                .then(({ data }) => {
                    commit("URLHISTORY", data);
                    commit("URLNOHISTORY", 1);
                })
                .catch((err) => {
                    if (err.response.status == 400) {
                        commit("URLNOHISTORY", 0);
                    }
                });
        },
        requestResetEmail({ commit }, data) {
            commit("URLGETMAILGRUOP", {})
        },

        requestGetMailGroup({ commit }, urlId) {
            http.get(`/api/mail/` + urlId)
                .then(({ data }) => {
                commit("URLGETMAILGRUOP", data)
            })
            .catch((err) => {

            });
        },
        requestRegisterMail({ commit }, body) {
            http.post(`/api/mail/add` , body)
            .then(({ data }) => {
                if (data.statusCode == 201) {
                    VueSimpleAlert.fire({
                        title: "성공",
                        text: "이메일 추가 완료!😀",
                        type: "success",
                    })
                    commit("URLGETMAILGRUOP", {})
                    setTimeout(location.reload(), 1500);
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
            http.patch(`/api/url/patch/`,url)
            .then(({data})=> {
                setTimeout( commit("MODIFYAVAILABLE", false), 1000);
                if (data.statusCode == 201) {
                    VueSimpleAlert.fire({
                        title: "Save",
                        text: "수정이 완료되었습니다.😀",
                        type: "success",
                    })
                    location.reload();
                }
            })
            .catch((err) => {

            })
        },

        requestDeleteUrl({commit},delurl)
        {
            http.delete('/api/url/delete',delurl)
            .then(({data})=> {
                if (data.statusCode == 201) {
                    VueSimpleAlert.fire({
                        title: "Delete",
                        text: "삭제가 완료되었습니다.😀",
                        type: "success",
                    })
                    
                }

            })
            .catch((err) => {

            })
        },
        requestModifyEmail({ commit }, mail) { 
            http.patch(`/api/mail/patch/`,mail)
            .then(({data})=> {
                if (data.statusCode == 201) {
                    VueSimpleAlert.fire({
                        title: "Save",
                        text: "수정이 완료되었습니다.😀",
                        type: "success",    
                    })
                }
                this.$state.mailGroup = null;
            })
            .catch((err) => {
                VueSimpleAlert.fire({
                    title: "Error",
                    text: "수정이 실패되었습니다.👋",
                    type: "error",
                })

            })
        },
        requestSearchUrl({ commit }, body) { 
            http
                .post(`/api/url/search/`, body)
                .then(({ data }) => {
                    commit("URLLIST", data.urlSearchResponseList);
                    if (data.statusCode != 201) {
                        VueSimpleAlert.fire({
                            title: "Success",
                            text: "검색 완료!😀",
                            type: "success",
                        })
                        
                    }
                })
                .catch((err) => {
                    commit("URLLIST", null);
                    if (err.response.status == 404) {
                        VueSimpleAlert.fire({
                            title: "Warning",
                            text: "조회된 사이트가 존재하지 않습니다.👋",
                            type: "error",
                        })
                    }
                });
        },
        requestSearchHistory({ commit }, body) { 
            http
                .post(`/api/url/history/option/`, body)
                .then(({ data }) => {
                    commit("URLHISTORY", data);
                    if (data.statusCode != 201) {
                        VueSimpleAlert.fire({
                            title: "Success",
                            text: "검색 완료!😀",
                            type: "success",
                        })
                        
                    }
                })
                .catch((err) => {
                    commit("URLLIST", null);
                    if (err.response.status == 500) {
                        VueSimpleAlert.fire({
                            title: "Warning",
                            text: "조회된 사이트가 존재하지 않습니다.👋",
                            type: "error",
                        })
                    }
                });
        },

    },
}
