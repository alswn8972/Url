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
                          <md-card-content>
                            <md-table v-model="mailGroups">
                              <md-table-row slot="md-table-row">
                                <md-table-cell md-label="순서" v-for="(item, key) in mailGroups" :key="key" v-bind:value="key">
                                  {{key+1}} : {{item.emailGroup}}
                                </md-table-cell>
                              </md-table-row>
                            </md-table>
                            <!-- <md-table v-if="!mailGroups">
                              <p>등록된 메일이 없습니다. 결과를 공유하려면 이메일을 추가해주세요! 등록된 URL 상태에 대한 메일을 함꼐 보내드립니다.</p>
                            </md-table> -->
                          </md-card-content>
                        </md-card>

                      </div>
                    <div class="md-layout-item md-size-100 text-center">
                      <md-button class="md-raised md-info">알림신청</md-button>
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
    }
  },
  mounted(){

  },
  computed:{
    ...mapGetters('url', {urlList:'getUrlList', mailGroups:'getUrlMails'}),
    ...mapGetters('user', {userId:'getUserId'}),
  },
  methods:{
    ...mapActions('url', ["requestUrlList", "requestRegisterMail","requestGetMailGroup"]),
    onChange(event){
      console.log(event);
      this.$store.state.mailGroup=null;
      this.addUrlId=event;
      console.log(this.addUrlId)
      this.requestGetMailGroup(event);
    },
    clickAddEmail(){
      let body={
        emailGroup:this.addMail,
        urlId: this.addUrlId,
        userId:this.userId
      }
      console.log(body)
      this.requestRegisterMail(body);
    }

  },
  created(){
    this.requestUrlList(this.userId);
  },


};
</script>
