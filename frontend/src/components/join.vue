<template>
    <div class="main">
        <v-row>
            <v-form ref="form"
                    v-model="valid"
                    lazy-validation
            >
                <v-text-field 
                    v-model="userName" 
                    type="text" 
                    label="이름" 
                    required
                    :rules="userNameRule">
                </v-text-field>
                <v-text-field v-model="userId" type="text" label="아이디" required
                :rules="userId_rule">
                </v-text-field>
                <v-text-field v-model="userPw" type="password" label="비밀번호" :rules="userPw_rule" required></v-text-field>
                <v-text-field
                    v-model="userPwCk"
                    type="password"
                    label="비밀번호 확인"
                    required
                    :rules="userPwCk_rule">
                </v-text-field>
                <v-text-field
                    v-model="userEmail"
                    type="text"
                    label="이메일"
                    required
                    :rules="userEmail_rule">
                </v-text-field>

                <v-btn color="success" class="mr-4" >
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
    export default {
        name: "join",
        props: {
            msg: String
        },
        data() {
            return {
                valid : false,
                userName: null, 
                userPw: null, 
                userId: null, 
                userEmail: null, 
                userPwCk: null};

                userNameRule:[
                    v => !!v || '이름은 필수 입력사항입니다.',
                    v => !(v && v.length >= 30) || '이름은 30자 이상 입력할 수 없습니다.',
                    v => !/[~!@#$%^&*()_+|<>?:{}].test(v)/ || '이름에는 특수문자를 사용할 수 없습니다.'
                ]
        },
        methods: {
            //...mapActions('user', ["requestRegister", "requestDuplicate", "requestEstate"]),
            clickDuplicate(userId){
                this.requestDuplicate(userId);
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