
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
        data: {
            labels: [],
            series: [

            ]
        },
    },
    urlMails:[],

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
        getUrlMails:(state)=> {
            return state.urlMails;
        }

    },
    mutations: {
        URLLIST(state, payload) {
            state.urlList = payload;
        },
        URLCHECK(state, payload) {
            state.urlCheck = payload;
        },
        URLAVAILABLE(state, payload) {
            state.availableAdd = payload;
        },
        URLHISTORY(state, payload) {
            let len = payload.length;
            var statusCode = new Array(len);
            for (var i = 0; i < len; i++) {
                state.urlHistory.data.labels[i] = payload[i].createdDate.substring(14, 16);
                statusCode[i] = payload[i].statusCode;
            }
            state.urlHistory.data.series[0] = statusCode;

        },
        URLMAILS(state, payload) {
            var splitEmailGroup = payload.emails[0].emailGroup.split(',');
            for(var i in splitEmailGroup){
                state.urlMails[i] = splitEmailGroup[i];
            }
            console.log(state.urlMails[0])
        },
        URLMAILSINIT(state){
            state.urlMails = [];
        }
    },
    actions: {
        requestUrlList({ commit }, userId) {
            //const CSRF_TOKEN = localStorage.getItem("accessToken");
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
            //const CSRF_TOKEN = localStorage.getItem("accessToken");
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
                })
                .catch((err) => {
                    console.log(err);
                });
        },
         async requestRegisterMail({commit}, urlId){
            await http.get(`/api/reservation/`+urlId)
            .then(({data})=> {
                commit("URLMAILS", data)
            })
            .catch((err) => {
                console.log(err)
                commit("URLMAILSINIT")
            })
        },
        requestChangePending({commit}, urlId){
            http.get(`/api/url/check/pending/`+urlId)
            .then(({data})=> {

            })
            .catch((err) => {
                console.log(err)

            })
        }
    },

}
