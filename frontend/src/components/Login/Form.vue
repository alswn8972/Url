<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-size-100">
          <form novalidate class="md-layout">
            <md-card>
              <md-card-header>
                  <h4 class="title">Login</h4>
                  <p class="category">더 많은 기능을 이용하시려면 로그인을 해주세요 :D</p>
                </md-card-header>

                <md-card-content>
                  <div class="md-layout">

                    <div class="md-layout-item md-small-size-100 md-size-100">
                      <md-field>
                        <label>아이디</label>
                        <md-input v-model="user.userId" type="text"></md-input>
                        <span class="md-error" v-if="error.idRequired">아이디를 입력해주세요.</span>
                      </md-field>
                      
                    </div>
                    <div class="md-layout-item md-small-size-100 md-size-100">
                      <md-field>
                        <label>비밀번호</label>
                        <md-input v-model="user.userPw" type="password"></md-input>
                        <span class="md-error" v-if="error.pwRequired">비밀번호를 입력해주세요.</span>
                      </md-field>
                    </div>

                    <div class="md-layout-item md-size-100 text-right">
                      <md-button @click="clickLogin" class="md-raised md-info">로그인</md-button>
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
  import { validationMixin } from 'vuelidate'
  import {
    required,
    minLength,
  } from 'vuelidate/lib/validators'
import {mapActions} from "vuex"
export default {
  mixins: [validationMixin],
  components: {
     required,
    minLength,
  },
  data(){
    return {
      user:{
        userId:null,
        userPw:null,
      },
      error:{
        idRequired:false,
        pwRequired:false,
      }
    }
  },
  methods: {
            ...mapActions("user", ["requestLogin"]),
            clickLogin() {
    
                this.requestLogin(this.user)
                
              
              
            },
            
        },
};
</script>
