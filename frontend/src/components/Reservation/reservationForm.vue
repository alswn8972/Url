<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-size-100">
          <form>
            <md-card>
          <md-card-header data-background-color="blue">
                  <h4 class="title">Reservation</h4>
                  <p class="category">다른 사람과 함께 사이트 상태를 공유하고 싶으시면 예약 신청을 해주세요.</p>
                </md-card-header>
                <md-card-content>
                  <div class="md-layout">
                    <div class="md-layout-item md-small-size-100 md-size-100">
                        <md-field>
                          <label for="item">신청 도메인</label>
                          <md-select v-model="select" name="item" id="item" md-dense>
                            <md-option v-for="item in this.urlList" v-bind:key="item.urlId" :value="item.urlId">
                              {{ item.urlName }}
                            </md-option>
                          </md-select>
                      </md-field>
                    </div>
                    <div class="md-layout-item md-small-size-81 md-size-80">
                      <md-field>
                        <label>이메일</label>
                        <md-input v-model="reference" type="text"></md-input>
                      </md-field>
                    </div>
                    <div class="md-layout-item md-small-size-19 md-size-20">
                      <div class="places-buttons text-center">
                        <md-button
                          class="md-primary"
                          
                          >추가</md-button
                        >
                      </div>
                    </div>
                    <md-select v-model="select" name="item" id="item" md-dense>
                            <md-option v-for="item in this.urlList" v-bind:key="item.urlId" :value="item.urlId">
                              {{ item.urlName }}
                            </md-option>
                          </md-select>
                    <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100">
                        <md-card class="md-card-plain">
                          <md-card-content>
                            <md-table v-model="users" :table-header-color="tableHeaderColor">
                              <md-table-row slot="md-table-row" slot-scope="{ item }">
                                <md-table-cell md-label="ID">{{ item.id }}</md-table-cell>
                                <md-table-cell md-label="Name">{{ item.name }}</md-table-cell>
                                <md-table-cell md-label="Salary">{{ item.salary }}</md-table-cell>
                                <md-table-cell md-label="Country">{{ item.country }}</md-table-cell>
                                <md-table-cell md-label="City">{{ item.city }}</md-table-cell>
                              </md-table-row>
                            </md-table>
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
      emails:[],
      users: [
        {
          id: 1,
          name: "Dakota Rice",
          salary: "$36,738",
          country: "Niger",
          city: "Oud-Turnhout",
        },
        
      ],

    }
  },
  computed:{
    ...mapGetters('url', {urlList:'getUrlList'}),
  },
  methods:{
    ...mapActions('user', ["requestRegister", "requestDuplicate"]),
  },
  created(){
    
  },
  
};
</script>
