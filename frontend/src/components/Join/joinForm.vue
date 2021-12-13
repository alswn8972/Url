<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-size-100">
          <form novalidate class="md-layout" @submit.prevent="validateUser">
            <md-card>
                <md-card-header data-background-color="blue">
                  <h4 class="title">Join</h4>
                  <p class="category">회원가입을 통해 자신이 관리하는 사이트의 상태를 한눈에 확인해보세요.</p>
                </md-card-header>
                <md-card-content>
                  <div class="md-layout md-gutter">
                    <div class="md-layout-item md-small-size-100 md-size-100">
                      <md-field :class="getValidationClass('userName')">
                          <label for="userName">이름</label>
                          <md-input name="userName" id="userName" autocomplete="given-name" v-model="form.userName" :disabled="sending" />
                        <span class="md-error" v-if="!$v.form.userName.required">이름은 필수 입력 값입니다.</span>
                        <span class="md-error" v-else-if="!$v.form.userName.minLength">이름은 최소 2글자 이상입니다.</span>
                      </md-field>
                    </div>
                    <div class="md-layout-item md-small-size-70 md-size-85">
                      <md-field :class="getValidationClass('userId')">
                          <label for="userId">아이디</label>
                          <md-input name="userId" id="userId" autocomplete="given-name" v-model="form.userId" :disabled="sending" />
                        <span class="md-error" v-if="!$v.form.userId.required">아이디는 필수 입력 값입니다.</span>
                      </md-field>
                    </div>
                    <div class="md-layout-item md-small-size-30 md-size-15 ">
                      <div class="places-buttons">
                        <md-button
                          class="md-primary"
                          @click="clickDuplicate(form.userId)"
                          >아이디 확인</md-button
                        >
                      </div>
                    </div>
                    <div class="md-layout-item md-small-size-100 md-size-100">
                      <md-field :class="getValidationClass('userPw')">
                          <label>비밀번호</label>
                        <md-input v-model="form.userPw" type="password" :disabled="sending" />
                        <span class="md-error" v-if="!$v.form.userPw.required">비밀번호는 필수 입력 값입니다.</span>
                      </md-field>
                    </div>
                    <div class="md-layout-item md-small-size-100 md-size-100">
                      <md-field :class="getValidationClass('userPwCheck')">
                        <label>비밀번호 확인</label>
                        <md-input v-model="form.userPwCheck" type="password" :disabled="sending" />
                        <span class="md-error" v-if="!$v.form.userPwCheck.required">비밀번호 확인은 필수 입력 값입니다.</span>
                      </md-field>
                    </div>
                    <div class="md-layout-item md-small-size-35 md-size-45">
                      <md-field :class="getValidationClass('userEmail')">
                        <label>이메일</label>
                        <md-input v-model="form.userEmail" type="text" :disabled="sending"/>
                        <span class="md-error" v-if="!$v.form.userEmail.required">이메일은 필수 입력 값입니다.</span>
                        <span class="md-error" v-else-if="!$v.form.userEmail.email">이메일 양식대로 입력해주세요.</span>
                      </md-field>
                    </div>
                    <div class="md-layout-item md-small-size-30 md-size-40">
                      <md-field :class="getValidationClass('userCode')">
                        <label>인증코드</label>
                        <md-input v-model="form.userCode" type="text" :disabled="sending"></md-input>
                        <span class="md-error" v-if="!$v.form.userCode.required">인증 번호는 필수 입력 값입니다.</span>
                      </md-field>
                    </div>
                    <div class="md-layout-itemmd-small-size-30  md-size-15">
                      <div class="places-buttons text-center">
                        <md-button
                          v-if="!isCheckCode"
                          class="md-primary"
                          @click="clickSendCode()"
                          >이메일 확인</md-button
                        >
                        <md-button
                          v-if="isCheckCode"
                          class="md-primary"
                          @click="clickCheckCode()"
                          >인증번호 확인</md-button
                        >
                      </div>
                    </div>

                    <div class="md-layout-item md-size-100 text-center">
                      <md-button type="submit" :disabled="sending" @click="clickJoin" class="md-raised md-info">회원가입</md-button>
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
import {mapActions} from "vuex"
import {
    required,
    minLength,
    email
} from 'vuelidate/lib/validators'
import { validationMixin } from 'vuelidate'
export default {
  mixins: [validationMixin],
  components: {
  },
  data(){
    return {
      isCheckId:false,
      isCheckCode:false,
      disabled:true,
      form: {
            userId: null,
            userPw: null,
            userEmail:null,
            userName:null,
            userCode:'',
            userPwCheck:null,
        },
      
        userSaved: false,
        sending: false,
        lastUser: null,

  
      
      

    }
  },
  validations: {
        form: {
            userId: {
            required,
            minLength: minLength(2),
            },
            userName:{
              required,
            },
            userPw: {
            required,
            },
            userEmail:{
              required,
              email,
            },
            userPwCheck:{
          required,
        },
        userCode:{
          required,
        }
        },
        
    },
  methods:{
    ...mapActions('user', ["requestRegister", "requestDuplicate"]),
    clickSendCode(){
      this.disabled=false
      this.isCheckCode=true
    },
    clickCheckCode(){
      console.log('인증코드 확인 클릭');
    },
    clickDuplicate(userId){
      this.requestDuplicate(userId);
    },
    clickJoin(){
        for (var item in this.user) {
              if(this.user[item]==""){
                  this.$alert("입력 정보를 다시 확인해주세요.");
                  return;
              }
          }
        this.requestRegister(this.user);
    },
    getValidationClass (fieldName) {
            const field = this.$v.form[fieldName]

            if (field) {
            return {
                'md-invalid': field.$invalid && field.$dirty
            }
            }
        },
  validateUser () {
    this.$v.$touch()
      if (!this.$v.$invalid) {
        this.requestLogin(this.form)
      }
  }
    
            
  }
};
</script>
