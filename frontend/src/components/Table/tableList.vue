<template>
    <div class="content">
        <div class="md-layout">
            <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100">
                <md-card>
                    <md-card-header data-background-color="blue">
                        <h4 class="title">내가 등록한 Url</h4>
                        <p class="category">자신이 등록한 Url 상태를 한눈에 볼 수 있습니다.</p>
                    </md-card-header>
                    <md-card-content v-if="urlList != null">
                        <table-list-item @history="history" table-header-color="blue"></table-list-item>
                    </md-card-content>
                    <md-card-content text-center v-else>
                        <p>등록된 Url이 없습니다. 관리하고 계신 Url을 등록해 상태를 확인해보세요! :D</p>
                    </md-card-content>

                </md-card>
            </div>
            <div  class="md-layout" v-if="urlNoHistory==false" >
                <div class="md-layout-item md-medium-size-50 md-xsmall-size-50 md-size-25">
                    <stats-card v-if="urlHistory.success != null" data-background-color="green">
                    <template slot="header">
                        <md-icon>sentiment_very_satisfied</md-icon>
                    </template>

                    <template slot="content">
                        <p class="category">SUCCESS</p>
                        <h3 class="title">{{urlHistory.success}}/{{urlHistory.total}}</h3>
                    </template>
                    <template slot="footer">
                        <p>클라이언트의 요청을<br>성공적으로 수행한 횟수는<br> {{urlHistory.total}}회 중 {{urlHistory.success}}회 입니다.</p>
                    </template>
                    </stats-card>
                </div>
                <div class="md-layout-item md-medium-size-50 md-xsmall-size-50 md-size-25">
                    <stats-card data-background-color="red" v-if="urlHistory.fail != null">
                    <template slot="header">
                        <md-icon>sentiment_dissatisfied</md-icon>
                    </template>
                    <template slot="content">
                        <p class="category">FAIL</p>
                        <h3 class="title">{{urlHistory.fail}}/{{urlHistory.total}}</h3>
                    </template>
                    <template slot="footer">
                        <p>리다이렉션 혹은<br>클라이어트, 서버 오류는<br>총 {{urlHistory.total}}회 중 {{urlHistory.fail}}회 입니다.</p>
                    </template>
                    </stats-card>
                </div>
                <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-50">
                        <md-card class="md-card-plain">
                            <md-card-header data-background-color="blue">
                                <h4 class="title">{{this.isName}}</h4>
                                <p class="category">현재 도메인의 상태확인 히스토리입니다.(2초 지연시 오류로 간주)</p>
                            </md-card-header>
                            <md-card-content style="height: 200px;overflow: scroll;" >
                                <history-table></history-table>
                            </md-card-content>
                        </md-card>
                    </div>
                </div>
            </div>
        <md-speed-dial class="md-bottom-right">
            <md-speed-dial-target @click="showDialog = true" class="md-primary">
                <md-icon class="icon">add</md-icon>
            </md-speed-dial-target>
        </md-speed-dial>
        <md-dialog :md-active.sync="showDialog">
            <md-card>
                <md-card-header class="addCard">
                    Url 등록
                </md-card-header>
                <md-card-content>
                    <div class="md-layout">
                        <div class="md-layout-item md-small-size-40 md-size-30">
                            <md-field>
                                <label for="protocol">Protocol</label>
                                <md-select v-model="url.protocol" name="protocol" id="protocol">
                                    <md-option value="1">http://</md-option>
                                    <md-option value="2">https://</md-option>
                                </md-select>
                            </md-field>
                        </div>
                        <div class="md-layout-item md-small-size-60 md-size-50">
                            <md-field>
                                <label>EX) bccard.com</label>
                                <md-input v-model="url.address" type="text"></md-input>
                            </md-field>
                        </div>

                        <div class="md-layout-item md-small-size-100 md-size-20">
                            <div class="places-buttons text-center">
                                <md-button
                                    class="md-info"
                                    :disabled="urlAvaliable"
                                    @click="clickCheckStatus">상태 확인</md-button >
                            </div>
                        </div>
                        <div class="md-layout-item md-small-size-30 md-size-30">
                            <md-field v-show="urlAvaliable">
                                <label>Url 이름</label>
                                <md-input v-model="url.urlName" type="text"></md-input>
                            </md-field>
                        </div>
                        <div class="md-layout-item md-small-size-70 md-size-70">
                            <md-field v-show="urlAvaliable">
                                <label>Url 설명</label>
                                <md-input v-model="url.urlContent" type="text"></md-input>
                            </md-field>
                        </div>
                        <div class="md-layout-item md-small-size-100 md-size-100 text-center">
                            <md-button
                                v-if="urlAvaliable"
                                @click="clickAddUrl"
                                class="md-raised md-info">등록</md-button>
                        </div>
                    </div>
                </md-card-content>
            </md-card>
            <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100">
                <div class="alert alert-danger" v-if="isEmpty">
                    <button type="button" aria-hidden="true" class="close" @click="clickClose">
                        ×
                    </button>
                    <span>
                        <b>주소를 입력해 주세요! 입력한 주소의 상태를 알려드립니다. :D</b>
                    </span>
                </div>
            </div>
            <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100">
                <div class="alert alert-danger" v-if="isProtocol">
                    <button
                        type="button"
                        aria-hidden="true"
                        class="close"
                        @click="clickProtocoeClose">
                        ×
                    </button>
                    <span>
                        <b>프로토콜을 선택해 주세요! 상세한 주소 상태를 알려드립니다. :D</b>
                    </span>
                </div>
            </div>
            <!-- <md-dialog-actions> <md-button class="md-primary" @click="showDialog =
            false">Close</md-button> <md-button class="md-primary" @click="showDialog =
            false">Save</md-button> </md-dialog-actions> -->
        </md-dialog>
    </div>
</template>

<script>
    import tableListItem from "@/components/Table/tableListItem.vue";
    import historyTable from "@/components/Table/historyTable.vue";
    import {
    StatsCard
} from "@/components";
    import {mapGetters, mapActions} from 'vuex';
    export default {
        components: {
            tableListItem,
            historyTable,
            StatsCard
        },
        data() {
            return {
                showDialog: false,
                url: {
                    protocol: '',
                    address: '',
                    urlName: '',
                    urlContent: ''
                },
                isName:'',
                isEmpty: false,
                isProtocol: false,
                charOption :{
                    options: {
                        lineSmooth: this
                            .$Chartist
                            .Interpolation
                            .cardinal({tension: 2}),
                        low: 0,
                        high: 600, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
                        chartPadding: {
                            top: 0,
                            right: 0,
                            bottom: 0,
                            left: 0
                        }
                    }
                },
                urlId: ''
            }
        },
        created() {
            this.requestUrlList(this.userId);
        },
        computed: {
            ...mapGetters('url', {
                urlAvaliable: 'getUrlAvailable',
                urlInit: 'getInitAvailable',
                urlList: 'getUrlList',
                urlHistory: 'getUrlHistory',
                urlNoHistory:'getUrlNoHistory',
            }),
            ...mapGetters('user', {userId: 'getUserId'})
        },
        mounted(){

        },
        methods: {
            ...mapActions('url', ['requestAddtoCheckUrl', 'requestAddUrl', 'requestHistory', 'requestUrlList']),
            clickClose() {
                this.isEmpty = false;
            },
            clickProtocoeClose() {
                this.isProtocol = false;
            },
            history(id) {
                this.isName = id.urlName;
                this.requestHistory(id.urlId);     
            },
            clickCheckStatus() {
                if (this.url.address == "") {
                    this.isEmpty = true;
                }
                if (this.url.protocol == "") {
                    this.isProtocol = true;
                }
                if (this.url.address != null && this.url.protocol != null) {
                    this.requestAddtoCheckUrl(this.url);
                }
            },
            clickAddUrl() {
                if (this.url.protocol == 2) {
                    var body = {
                        urlAddress: "https://" + this.url.address,
                        urlName: this.url.urlName,
                        urlContent: this.url.urlContent,
                        userId: this.userId
                    }
                    this.requestAddUrl(body);
                } else {
                    var body2 = {
                        urlAddress: "http://" + this.url.address,
                        urlName: this.url.urlName,
                        urlContent: this.url.urlContent,
                        userId: this.userId
                    }
                    this.requestAddUrl(body2);
                }
                this.url.protocol = '';
                this.url.address = '';
                this.url.urlName = '';
                this.urlContent = '';
                this.showDialog = false;
                this
                    .$router
                    .go();
            }
        }
    };
</script>
<style scoped="scoped">
    .icon {
        margin-left: -35%;
    }
    .addCard {
        margin-top: 0.3% !important;
    }
</style>