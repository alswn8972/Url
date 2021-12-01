<template>
    <div class="Nav">
        <v-card>
            <v-toolbar flat="flat">

                <v-toolbar-title>SITE Monitor</v-toolbar-title>

                <v-spacer></v-spacer>

                <v-btn
                    class="login_btn"
                    @click="showDialog('Text')"
                    outlined="outlined"
                    >
                    Login
                </v-btn>
                <v-btn class="sign_btn" outlined="outlined">
                    SignUp
                </v-btn>
            </v-toolbar>
        </v-card>
        <v-dialog
            v-model="roroTextDialog"
            max-width="500"
        >
          <login
            header-title="로그인"
            @hide="hideDialog('Text')"
            @submit="submitDialog('Text')"
          >
            <template v-slot:body>
              <v-text-field
                  placeholder="내용을 입력하세요"
              />
            </template>
          </login>
        </v-dialog>

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
                roroTextDialog: false,
                roroListDialog: false,
                roroImageDialog: false,
            };
        },
        methods: {
            showDialog(type) {
                console.log('열려라')
                this[`roro${type}Dialog`] = true
            },
            hideDialog(type) {
                this[`roro${type}Dialog`] = false
            },
            submitDialog(type) {
                console.log('submit 완료!')
                this.hideDialog(type)
            },
        }
    };
</script>

<style>
    .login_btn {
        margin-right: 20px;
    }
</style>