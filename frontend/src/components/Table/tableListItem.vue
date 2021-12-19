<template>
    <div>
        <md-table v-if="this.urlList" v-model="this.urlList" :table-header-color="tableHeaderColor">
            <md-table-row
                @click="showHistory(item)"
                slot="md-table-row"
                slot-scope="{ item }">
                <md-table-cell md-label="이름">{{ item.urlName }}</md-table-cell>
                <md-table-cell md-label="설명">{{ item.urlContent }}</md-table-cell>
                <md-table-cell md-label="주소">{{ item.urlAddress }}</md-table-cell>
                <md-table-cell md-label="상태">
                    <md-chip v-if="item.urlStatusCode <200">error</md-chip>
                    <md-chip v-else-if="item.urlStatusCode >= 200 && item.urlStatusCode < 300" class="successChip">{{ item.urlStatusCode }}</md-chip>
                    <md-badge v-else-if="item.urlStatusCode >= 300 && item.urlStatusCode < 400 && item.urlIsPending==1" md-content="!">
                        <md-chip class="wraningChip">{{ item.urlStatusCode }}</md-chip>
                    </md-badge>
                    <md-chip v-else-if="item.urlStatusCode >= 300 && item.urlStatusCode < 400 && item.urlIsPending==0" class="wraningChip">{{ item.urlStatusCode }}</md-chip>
                    <md-badge v-else-if="item.urlStatusCode >= 400 && item.urlStatusCode < 500 && item.urlIsPending==1" md-content="!">
                        <md-chip class="wraningChip">{{ item.urlStatusCode }}</md-chip>
                    </md-badge>
                    <md-chip v-else-if="item.urlStatusCode >= 400 && item.urlStatusCode < 500 && item.urlIsPending==0" class="dangerChip">{{ item.urlStatusCode }}</md-chip>
                </md-table-cell>

                <md-table-cell md-label="검사시간">
                    {{item.urlCheckTime}}
                </md-table-cell>
                <md-table-cell md-label="수정/삭제">
                    <md-chip class="wraningChip"  @click="requestModifyDialog(item)">
                        수정
                    </md-chip>
                    <md-chip class="dangerChip">
                        삭제
                    </md-chip>
                </md-table-cell>
            </md-table-row>
        </md-table>
        <md-dialog :md-active.sync="showModifyDialog">
          <md-card>
                <md-card-header class="addCard">
                    Url 수정
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
                                    @click="clickCheckStatus"  :disabled="modifyAvaliable">상태 확인</md-button >
                            </div>
                        </div>
                        <div class="md-layout-item md-small-size-30 md-size-30">
                            <md-field >
                                <label>Url 이름</label>
                                <md-input v-model="url.urlName" type="text"></md-input>
                            </md-field>
                        </div>
                        <div class="md-layout-item md-small-size-70 md-size-70">
                            <md-field >
                                <label>Url 설명</label>
                                <md-input v-model="url.urlContent" type="text"></md-input>
                            </md-field>
                        </div>
                        <div class="md-layout-item md-small-size-100 md-size-100 text-center">
                            <md-button
                              :disabled="!modifyAvaliable"
                                @click="clickModifyUrl"
                                class="md-raised md-info">수정</md-button>
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
        </md-dialog>

    </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex';

    export default {
        
        components: {},
        name: "simple-table",
        props: {
            tableHeaderColor: {
                type: String,
                default: ""
            }
        },
        data() {
            return {selected: [],
            showModifyDialog:false,
            url: {
                    protocol: '',
                    address: '',
                    urlName: '',
                    urlContent: ''
                },
                ModifyUrl : true,
                isProtocol: false,
                isEmpty: false,
                };
        },
        methods: {
            ...mapActions('url', ['requestUrlList', 'requestChangePending',
            'requestAddtoCheckUrl', 'requestAddUrl', 'requestHistory',
            'requestModitoCheckUrl','requestPactchUrl','requestDeleteUrl']),
            showHistory: function (item) {
                this.$emit('history', item)
            },
            clickCheck: function(item){
                console.log("chip누름")
                this.requestChangePending(item.id)
            },
            requestModifyDialog: function(item){
                this.showModifyDialog = true;
                console.log("modify :" , item)

                var curItem = item;
                var checkProtocol  = 0;

                checkProtocol = curItem.urlAddress.indexOf('http://')
                console.log(checkProtocol)
                if(checkProtocol== 0)
                {
                    this.url.protocol =1;
                    this.url.address = curItem.urlAddress.substr(7,curItem.urlAddress.length);
                }
                else
                {
                    this.url.protocol =2;
                    this.url.address = curItem.urlAddress.substr(8,curItem.urlAddress.length);
                }
                this.url.urlName= curItem.urlName;
                this.url.urlContent = curItem.urlContent;
                this.url.urlId= curItem.urlId;
                console.log("geturl:",this.url)
            },
            requestDeleteUrl: function(item)
            {
                var delurl ={
                     id: item.urlId,
                     userId: this.id,
                } // alswn8972 가 아니라 고유 id가 필요함 
            
            this.requestDeleteUrl(delurl)

                this
                    .$router
                    .go();
            },
            clickModifyUrl() {
                if (this.url.protocol == 2) {
                    var body = {
                        urlAddress: "https://" + this.url.address,
                        urlName: this.url.urlName,
                        urlContent: this.url.urlContent,
                       id : this.url.urlId,
                    }
                    this.requestPactchUrl(body);
                } else {
                    var body2 = {
                        urlAddress: "http://" + this.url.address,
                        urlName: this.url.urlName,
                        urlContent: this.url.urlContent,
                        id : this.url.urlId,
                    }
                    this.requestPactchUrl(body2);
                }
                 this
                    .$router
                    .go();
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

            clickClose() {
                this.isEmpty = false;
            },
        },
        computed: {
            ...mapGetters('user', {userId: 'getUserId', id:'getUserUniqueId'}),
            ...mapGetters('url', {urlList: 'getUrlList', modifyAvaliable: 'getModifyAvailable'})
        },
        created() {
            this.requestUrlList(this.userId);
            setInterval(() => this.requestUrlList(this.userId), 60000);
        },
        mounted(){

        },
    };
</script>
<style scoped="scoped">
    .successChip {
        background-color: cornflowerblue !important;
        color: white !important;
    }
    .wraningChip {
        background-color: rgb(224, 206, 41) !important;
        color: white !important;
    }
    .dangerChip {
        background-color: rgb(233, 93, 93) !important;
        color: white !important;
    }
</style>