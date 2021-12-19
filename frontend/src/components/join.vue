<template>
    <div class="main">
        <v-row>
            <v-form ref="form"
                    v-model="valid"
                    lazy-validation
            >
                <v-text-field 
                    v-model="user.userName" 
                    type="text" 
                    label="이름" 
                    required
                    :rules="userName_rule">
                </v-text-field>
                <v-text-field v-model="user.userId" type="text" label="아이디" required
                :rules="userId_rule">
                </v-text-field>
                <v-text-field v-model="user.userPw" type="password" label="비밀번호" :rules="userPw_rule" required></v-text-field>
                <v-text-field
                    v-model="userPwCk"
                    type="password"
                    label="비밀번호 확인"
                    required
                    :rules="userPwCk_rule">
                </v-text-field>
                <v-text-field
                    v-model="user.userEmail"
                    type="text"
                    label="이메일"
                    required
                 
                    >
                </v-text-field>

                <v-btn color="success" class="mr-4" @click="clickJoin">
                    Join with Us
                </v-btn>

                <v-btn color="error" class="mr-4">
                    Reset Form
                </v-btn>

                <!-- <v-btn color="warning">
                    Reset Validation
                </v-btn> -->
            </v-form>
        </v-row>

    </div>
</template>

<script>
    import {mapActions} from 'vuex';
    export default {
        name: "join",
        props: {
            msg: String
        },
        data() {
            return {
                valid : false,
                user: {
                    userFirstName:"",
                    userName: "",
                    userId: "",
                    userPw: "",
                    userEmail: "",
                },

                userPwCk: null,
                userId_rule: [
                    v => !!v || '아이디는 필수 입력사항입니다.',
                    v => /^[a-zA-Z0-9]*$/.test(v) || '아이디는 영문+숫자만 입력 가능합니다.',
                    v => !( v && v.length >= 15) || '아이디는 15자 이상 입력할 수 없습니다.'
                ],

                userName_rule: [
                    v => !!v || '이름은 필수 입력사항입니다.',
                    v => !(v && v.length >= 30) || '이름은 10자 이상 입력할 수 없습니다.',
                    v => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || '이름에는 특수문자를 사용할 수 없습니다.'
                ],

                userPw_rule: [
                    v => this.state === 'ins' ? !!v || '패스워드는 필수 입력사항입니다.' : true,
                    v => !(v && v.length >= 30) || '패스워드는 30자 이상 입력할 수 없습니다.',
                ],
                userPwCk_rule: [
                    v => this.state === 'ins' ? !!v || '패스워드는 필수 입력사항입니다.' : true,
                    v => !(v && v.length >= 30) || '패스워드는 30자 이상 입력할 수 없습니다.',
                    v => v === this.user.userPw || '패스워드가 일치하지 않습니다.'
                ],    
                user_desc_rule: [
                    v => !(v && v.length >= 100) || '설명은 100자 이상 입력할 수 업습니다.'
                ]    
            };
        },
        methods: {
            ...mapActions('user', ["requestRegister", "requestDuplicate"]),
            clickDuplicate(userId){
                this.requestDuplicate(userId);
            },
            clickJoin(){
                const validate = this.$refs.form.validate();
                if(validate){
                    this.requestRegister(this.user);
                }
                
            },
            validate () {
                this.$refs.form.validate()
            },
            reset () {
                this.$refs.form.reset()
            },
            resetValidation () {
                this.$refs.form.resetValidation()
            },
        },
    };
</script>

<style scoped="scoped">
    body,
    html {
        margin: auto;
        /* 화면 축소시 스크롤 방지 */
    }
    .main {
        /* 이하 필수 설정 */
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        text-align: center;
    }
</style>