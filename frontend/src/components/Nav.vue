<template>
    <div class="Nav">
        <v-card>
            <v-toolbar color="cyan" flat="flat">

                <v-toolbar-title>SITE Monitor</v-toolbar-title>

                <v-spacer></v-spacer>

                <v-app-bar-nav-icon></v-app-bar-nav-icon>
                <v-btn
                    class="login_btn"
                    @click="openDialog()"
                    outlined="outlined"
                    color="indigo">
                    Login
                </v-btn>
                <v-btn class="sign_btn" outlined="outlined" color="indigo">
                    SignUp
                </v-btn>
            </v-toolbar>
        </v-card>
        <Login v-if="this.dialog"/>

    </div>
</template>

<script>
    import Login from "./Login.vue"
    export default {
        name: "Nav",
        components: {
            Login
        },
        props: {},
        data() {
            return {
                dialog:false,
            };
        },
        methods: {
            openDialog() {
                console.log("click");
                this.dialog = true;
            }
        }
    };
</script>

<style>
    .login_btn {
        margin-right: 20px;
    }
</style>