<template>
    <div class="Nav">
        <v-app-bar app="app" color="white" flat="flat">
            <v-container class="py-0 fill-height">

                <router-link class="a" to="/" style="color:black;">
                    SITE Monitor
                </router-link>
                <v-spacer></v-spacer>
                <div v-if="!isLogin">
                    <v-btn
                        class="login_btn"
                        color="primary"
                        outlined="outlined"
                        small="small"
                        @click="showDialog()">로그인</v-btn>
                    <router-link class="a" to="/join"> 
                        <v-btn color="primary" outlined="outlined" small="small">
                            회원가입
                        </v-btn>
                    </router-link>
                </div>
                <div v-if="isLogin">
                    <v-btn color="primary" outlined="outlined" small="small" @click="clickLogout()">로그아웃</v-btn>
                </div>
            </v-container>
            <template v-slot:extension>
                <v-tabs
                    centered="centered"
                    class="ml-n9"
                    color="grey darken-1"
                    fixed-tabs="fixed-tabs">
                    <v-tab>
                        Url 상태 체크
                    </v-tab>
                    <router-link class="a" to="/list">  
                    <v-tab>
                        내 Url 목록
                    </v-tab>
                    </router-link>
                </v-tabs>
            </template>

        </v-app-bar>
        <!-- <v-card> <v-toolbar flat="flat"> <menu/> <router-link class="a" to="/">
        <v-toolbar-title style="color:black;">SITE Monitor</v-toolbar-title>
        </router-link> <v-spacer></v-spacer> <div v-if="!isLogin"> <v-btn
        class="login_btn" @click="showDialog()" outlined="outlined"> Login </v-btn>
        <router-link class="a" to="/join"> <v-btn class="sign_btn" outlined="outlined">
        Join </v-btn> </router-link> </div> <div v-if="isLogin"> <v-btn
        class="login_btn" @click="clickLogout()" outlined="outlined"> Logout </v-btn>
        </div> <template v-slot:extension> <v-tabs fixed-tabs color="black"> <v-tab>
        <router-link class="a" to="/"> Checking </router-link> </v-tab> <v-tab>
        <router-link class="a" to="/list"> My Site </router-link> </v-tab> </v-tabs>
        </template> </v-toolbar> </v-card> -->
        <v-dialog v-model="loginDialog" max-width="500">
            <login header-title="로그인" @hide="hideDialog()" @submit="submit">
                <template v-slot:body>
                    <v-text-field placeholder="내용을 입력하세요"/>
                </template>
            </login>
        </v-dialog>

    </div>
</template>

<script>
    import login from "./login"
    import menu from "./menu"
    import {mapActions} from "vuex"
    export default {
        name: "Nav",
        components: {
            login,
            menu
        },
        props: {},
        data() {
            return {
                loginDialog: false,
                drawer: false,
                user: {
                    userId: '',
                    userPw: ''
                },
                links: [
                    'Login', 'Join', 'Logout', 'MyList'
                ],
                isLogin: false
            };
        },
        mounted() {
            if (localStorage.getItem("accessToken") != null) {
                this.isLogin = true;
            } else {
                this.isLogin = false;
            }
        },

        methods: {
            ...mapActions("user", ["requestLogin"]),
            showDialog() {
                this.loginDialog = true
            },
            hideDialog() {
                this.loginDialog = false
            },
            submit(user) {
                this.user.userId = user.userId
                this.user.userPw = user
                    .userPw
                    this
                    .requestLogin(this.user)
                this.hideDialog()
            },
            openMenu() {
                this.openMenu = true;
            },
            clickLogout() {
                this.isLogin = false;
                localStorage.clear();
                this
                    .$router
                    .push("/");
            }
        }
    };
</script>

<style>
    .login_btn {
        margin-right: 20px;
    }
    .a {
        text-decoration: none;
        color: black;
    }
</style>