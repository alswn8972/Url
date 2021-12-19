<template>
    <div class="content">
        <div class="md-layout">
            <div class="md-layout-item md-medium-size-100 md-size-100">
                <form novalidate="novalidate" class="md-layout">
                    <md-card>
                        <md-card-header data-background-color="blue">
                            <h4 class="title">My Profile</h4>
                            <p class="category">마이페이지에서 이메일을 수정해주세요.</p>
                        </md-card-header>
                        <md-card-content>
                            <div class="md-layout md-gutter">
                                <div class="md-layout-item md-small-size-100 md-size-100">
                                    <md-field>
                                        <label for="userName">이름</label>
                                        <md-input
                                            name="userName"
                                            id="userName"
                                            autocomplete="given-name"
                                            v-model="myInfo.userName"
                                            :disabled="true"/>
                                    </md-field>
                                </div>
                                <div class="md-layout-item md-small-size-100 md-size-100">
                                    <md-field >
                                        <label for="userId">아이디</label>
                                        <md-input
                                            name="userId"
                                            id="userId"
                                            autocomplete="given-name"
                                            v-model="myInfo.userId"
                                            :disabled="true"/>
                                    </md-field>
                                </div>
                            
                               
                                <div class="md-layout-item md-medium-size-50 md-xsmall-size-50 md-small-size-50 md-size-40">
                                    <md-field :class="getValidationClass('userEmail')">
                                        <label>이메일</label>
                                        <md-input v-model="form.userEmail" type="text"/>
                                       
                                        <span class="md-error" v-if="!$v.form.userEmail.required">이메일은 필수 입력 값입니다.</span>
                                        <span class="md-error" v-else-if="!$v.form.userEmail.email">이메일 양식대로 입력해주세요.</span>
                                    </md-field>
                                </div>
                                <div class="md-layout-item md-medium-size-50 md-xsmall-size-50 md-small-size-50 md-size-40">
                                    <md-field :class="getValidationClass('userCode')">
                                        <label>인증코드</label>
                                        <md-input v-model="form.userCode" type="text" :disabled="sending" ></md-input>
                                        <span class="md-error" v-if="!$v.form.userCode.required">인증 번호는 필수 입력 값입니다.</span>
                                    </md-field>
                                </div>
                                <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-small-size-100 md-size-20">
                                    <div class="places-buttons text-center">
                                        <md-button v-if="!isCheckCode" class="md-primary" @click="clickSendCode()">이메일 확인</md-button >
                                        <md-button v-if="isCheckCode" class="md-primary" @click="clickCheckCode">인증번호 확인</md-button >
                                    </div>
                                </div>

                                <div class="md-layout-item md-size-100 text-center">
                                    <md-button
                                        :disabled="!modiable"
                                        @click="clickModiProfile"
                                        class="md-raised md-info">정보 수정</md-button>
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
    import {} from "@/pages";
    import {mapActions, mapGetters} from "vuex"
    import {required, email} from 'vuelidate/lib/validators'
    import {validationMixin} from 'vuelidate'
    export default {
        mixins: [validationMixin],
        components: {},
        data() {
            return {           
                isCheckCode: false,
                disabled: true,
                form: {  
                    userEmail: null,                  
                    userCode: '',
                  
                },
                userSaved: false,
                sending: true,
                lastUser: null
            }
        },
        validations: {
            form: {               
                userEmail: {
                    required,
                    email
                },             
                userCode: {
                    required
                }
            }
        },
        created(){
            this.injectionEmail();
        },
        methods: {
            ...mapActions('user', [ "requestCode", "requestCheckCode","requestModify","requsetModifyCheckCode"]),
            clickSendCode() {
                this.disabled = false
                this.isCheckCode = true
                this.requestCode(this.form.userEmail);
                this.sending = false
            },
            clickCheckCode() {
                let form ={
                  code : this.form.userCode,
                  email : this.form.userEmail
                }
                console.log(form)
                this.requsetModifyCheckCode(form)
            },
            getValidationClass(fieldName) {
                const field = this.$v.form[fieldName]
                if (field) {
                    return {
                        'md-invalid': field.$invalid && field.$dirty
                    }
                }
            },
          
            clickModiProfile(){
                var modiInfo = {
                    id: this.myInfo.idNumber,
                    userEmail: this.form.userEmail
                }

                if(this.isCheckCode == true){
                    console.log("야야")    
                }
                

                   this.requestModify(modiInfo)
            
          
               
            },

           

            injectionEmail(){
                this.form.userEmail = this.myInfo.userEmail
            }
        },
        computed:{
            //여기
          ...mapGetters('user', { userEmailAvail:'getUserEmailAvailable' , myInfo : 'getUserInfo' ,modiable : 'getModifyEmail'})
          //까지
        },
    };
</script>