<template>
    <div class="Nav">
        <v-card>
            <v-toolbar flat="flat">
                <menu/>
                <router-link class="a" to="/">
                    <v-toolbar-title style="color:black;">SITE Monitor</v-toolbar-title>
                </router-link>

                <v-spacer></v-spacer>

                <v-btn class="login_btn" @click="showDialog()" outlined="outlined">
                    Login
                </v-btn>
                <router-link class="a" to="/join">
                    <v-btn class="sign_btn" outlined="outlined">
                        Join
                    </v-btn>
                </router-link>

                <template v-slot:extension>
                    <v-tabs fixed-tabs color="black">
                        
                            <v-tab>
                                <router-link class="a" to="/">
                                    Checking
                                </router-link>
                            </v-tab>
                        <v-tab>
                                <router-link class="a" to="/list">
                                    My Site
                                </router-link>
                            </v-tab>
                        
                    </v-tabs>
                </template>
            </v-toolbar>
        </v-card>
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
                items: [
                    'web', 'shopping', 'videos', 'images', 'news'
                ],
                loginDialog: false,
                drawer: false,
                user:{
                    userId:'',
                    userPw:'',
                },
                
            };
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
                console.log('submit 완료!')
                this.user.userId=user.userId
                this.user.userPw=user.userPw
                this.requestLogin(this.user)
                this.hideDialog()
            },
            openMenu() {
                console.log('메뉴')
                this.openMenu = true;
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