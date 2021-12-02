<template>
    <div class="main">
        <v-row>
            <v-form ref="form" v-model="valid" lazy-validation="lazy-validation">
                <v-text-field v-model="userName" type="text" label="이름" required="required"></v-text-field>
                <v-text-field v-model="userId" type="text" label="아이디" required="required"></v-text-field>
                <v-text-field v-model="userPw" type="password" label="비밀번호" required="required"></v-text-field>
                <v-text-field
                    v-model="userPwCk"
                    type="password"
                    label="비밀번호 확인"
                    required="required"></v-text-field>
                <v-text-field
                    v-model="userEmail"
                    type="text"
                    label="이메일"
                    required="required"></v-text-field>

                <v-btn color="success" class="mr-4">
                    Validate
                </v-btn>

                <v-btn color="error" class="mr-4">
                    Reset Form
                </v-btn>

                <v-btn color="warning">
                    Reset Validation
                </v-btn>
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
                valid : true,
                userName: null, userPw: null, userId: null, userEmail: null, userPwCk: null};
        }
    };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
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