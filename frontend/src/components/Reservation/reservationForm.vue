<template>
  <div class="content">
    
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-size-100">
          <form>
            <md-card>
          <md-card-header data-background-color="blue">
                  <h4 class="title">Reservation</h4>
                  <p class="category">다른 사람과 함께 사이트 상태를 공유하고 싶으시면 메일을 추가 해주세요.</p>
                </md-card-header>
                <md-card-content>
                  <div class="md-layout">
                    <div class="md-layout-item md-small-size-100 md-size-100">
                        <md-field>
                          <label for="item">신청 도메인</label>
                          <md-select @md-selected="onChange($event)" v-model="select" name="item" id="item" md-dense>
                            <md-option v-for="item in urlList" v-bind:key="item.urlId" :value="item.urlId">
                              {{ item.urlName }}
                            </md-option>
                          </md-select>
                      </md-field>
                    </div>
                    <div class="md-layout-item md-small-size-81 md-size-80">
                      <md-field>
                        <label>이메일</label>
                        <md-input v-model="addMail" type="text"></md-input>
                      </md-field>
                    </div>
                    <div class="md-layout-item md-small-size-19 md-size-20">
                      <div class="places-buttons text-center">
                        <md-button @click="clickAddEmail" class="md-primary">추가</md-button>
                      </div>
                    </div>
                    <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100">
                        <md-card class="md-card-plain" >
                          <md-card-content >
                            <md-table v-if="mailGroups!=null" v-model="mailGroups">
                                <md-table-row slot="md-table-row" slot-scope="{ item }">
                                    <md-table-cell md-label="공유 이메일">
                                      {{item.emailGroup}}
                                    </md-table-cell>
                                    <md-table-cell md-label="수정/삭제">
                                        <md-chip class="wraningChip" @click="clickModifyEmail(item)">
                                            수정
                                        </md-chip>
                                        <md-chip class="dangerChip">
                                            삭제
                                        </md-chip>
                                    </md-table-cell>
                                </md-table-row>
                            </md-table>
                            <p v-else>사이트를 선택해주세요!</p>
                          </md-card-content>
                        </md-card>
                        <md-dialog :md-active.sync="showModifyEmailDialog">
                          <md-card>
                                <md-card-header class="addCard">
                                    이메일 수정
                                </md-card-header>
                                <md-card-content>
                                    <div class="md-layout-item md-small-size-100 md-size-100">
                                      
                                            <md-field>
                                                <md-input v-model="mail.address" type="text"></md-input>
                                            </md-field>
                                        </div>
                                      <div class="md-layout-item md-small-size-100 md-size-100">
                                            <div class="places-buttons text-center">
                                                <md-button
                                                    class="md-info"
                                                    @click="clickSendModifyEmail(mail)"
                                                    >이메일 수정</md-button >
                                            </div>
                                        </div>
                                </md-card-content>
                            </md-card>
                        </md-dialog>
                      </div>
                  </div>
                </md-card-content>
              </md-card>
            </form>
      </div>
    </div>
  </div>
</template>

<script>
import { } from "@/pages";
import { OrderedTable } from "@/components";
import {mapActions, mapGetters} from "vuex"
export default {
  components: {
    OrderedTable
  },
  data(){
    return {
      reference:null,
      select:'',
      isMailGroup:false,
      addMail:null,
      addUrlId:null,
      addUserId:null,
      showModifyEmailDialog:false,
      mail:{
        id:'',
        address:'',
      }

    }
  },
  mounted(){

  },
  computed:{
    ...mapGetters('url', {urlList:'getUrlList', mailGroups:'getUrlMails'}),
    ...mapGetters('user', {userId:'getUserId', id:'getUserUniqueId'}),
  },
  methods:{
    ...mapActions('url', ["requestUrlList", "requestRegisterMail","requestGetMailGroup", "requestModifyEmail"]),
    onChange(event){
      this.$store.state.mailGroup='';
      this.addUrlId=event;
      this.requestGetMailGroup(event);
    },
    clickAddEmail(){
      let body={
        emailGroup:this.addMail,
        urlId: this.addUrlId,
        userId:this.id
      }
      this.requestRegisterMail(body);
    },
    clickModifyEmail : function(item){
      this.showModifyEmailDialog = true;
      this.mail.address = item.emailGroup;
      this.mail.id = item.id;
      this.mail.urlId = item.urlId;
    },
    clickSendModifyEmail : function(item){
      this.requestModifyEmail(item);
    }

  },
  created(){
    this.requestUrlList(this.userId);
  },


};
</script>
<style scoped>
.wraningChip {
        background-color: rgb(224, 206, 41) !important;
        color: white !important;
    }
    .dangerChip {
        background-color: rgb(233, 93, 93) !important;
        color: white !important;
    }
</style>
