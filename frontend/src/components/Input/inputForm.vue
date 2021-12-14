<template>
    <div class="content">
        <div class="md-layout">
            <div
                        class="md-layout-item md-medium-size-50 md-xsmall-size-100 md-size-50"
                    >
                        <stats-card data-background-color="green">
                        <template slot="header">
                            URL 상태코드 확인
                        </template>

                        <template slot="content">
                            <p></p>
                        </template>
                        <template slot="footer">
                            <div class="stats">
                            SITE Monitor를 통해 URL에 대한 상태를 확인하고, <br>리다이렉션 되는 도메인을 확인 할 수 있습니다.
                            </div>
                        </template>

                        </stats-card>
                    </div>
                    <div
                        class="md-layout-item md-medium-size-50 md-xsmall-size-100 md-size-50"
                    >
                        <stats-card data-background-color="orange">
                        <template slot="header">
                            에러, 비정상 상태 알림
                        </template>
                        <template slot="content">
                            <p></p>
                        </template>
                        <template slot="footer">
                            <div class="stats">
                            자신이 관리하고 있는 URL에 대한 에러, 비정상 상태 알림을<br> 등록된 메일을 통해 실시간으로 받을 수 있습니다.
                            </div>
                        </template>

                        </stats-card>
                    </div>
            <div
                    class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100"
                >
                <md-card>
                    <md-card-header data-background-color="blue">
                        <p class="category">
                            확인하고자 하는 SITE 주소를 입력해주세요!
                        </p>
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
                                    <md-button class="md-info" @click="clickCheckStatus">상태 확인</md-button >
                                </div>
                            </div>
                        </div>
                    </md-card-content>
                </md-card>
                </div>
                <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100">
                    <div class="alert alert-danger" v-if="isEmpty">
                        <button type="button" aria-hidden="true" class="close" @click="clickClose">
                            ×
                        </button>
                        <span><b>주소를 입력해 주세요! 입력한 주소의 상태를 알려드립니다. :D</b> </span>
                    </div>
                </div>
                <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100">
                    <div class="alert alert-danger" v-if="isProtocol">
                        <button type="button" aria-hidden="true" class="close" @click="clickProtocoeClose">
                            ×
                        </button>
                        <span><b>프로토콜을 선택해 주세요! 상세한 주소 상태를 알려드립니다. :D</b> </span>
                    </div>
                </div>
                <div v-if="this.urlCheck!=null" class="md-layout-item md-small-size-100 md-size-100">
                <md-table v-model="this.urlCheck">
                <md-table-row slot="md-table-row" slot-scope="{ item }">
                    <md-table-cell class="code">
                        <md-chip v-if="item.statusCode >= 200 && item.statusCode < 300" class="successChip">{{ item.statusCode }}</md-chip>
                        <md-chip v-if="item.statusCode >= 300 && item.statusCode < 400" class="wraningChip">{{ item.statusCode }}</md-chip>
                        <md-chip v-if="item.statusCode >= 400" class="dangerChip">{{ item.statusCode }}</md-chip>
                    </md-table-cell>
                    <md-table-cell>{{ item.urlAddress }}</md-table-cell>
                </md-table-row>
    </md-table>
  </div>
        </div>
    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import {
  StatsCard,
} from "@/components";
    export default {
        components: {
    StatsCard,
  },
        data() {
            return {
                url:{
                    address:null,
                    protocol:null,
                },
                isEmpty: false,
                isProtocol:false,
            };
        },
        created(){
            this.$store.state.urlCheck = this.urlRemove;
        },
        computed:{
        ...mapGetters('url', {urlCheck:'getUrlCheck', urlRemove:'getUrlInit'}),
        },
        methods: {
            ...mapActions('url',['requestCheckUrl']),
            clickClose(){
                this.isEmpty = false;
            },
            clickProtocoeClose(){
                this.isProtocol=false;
            },
            clickCheckStatus() {
                console.log('되나')
                if(this.url.address == null) {
                    this.isEmpty=true;
                }if(this.url.protocol == null){
                    this.isProtocol=true;
                }if(this.url.address !=null && this.url.protocol!=null){
                    this.requestCheckUrl(this.url);
                }
            },
        }
    };
</script>
<style scoped>
.successChip{
    background-color: cornflowerblue !important;
    color : white !important;
    justify-content:center !important;
        margin-left: 40%;
}
.wraningChip{
    background-color: #ffa726 !important;
    color : white !important;
    justify-content:center !important;
        margin-left: 40%;
}
.dangerChip{
    background-color: rgb(233, 93, 93) !important;
    color : white !important;
    justify-content:center !important;
        margin-left: 40%;
}
.code{
    margin-left: 20px;
}
</style>