<template>
    <div class="hello">
        <img
              src="../assets/images/main.png"
              max-height="125"
              class="logo"
            />
        <v-row class="main">
            <v-col cols="12" sm="6" md="10">
                <v-text-field class="input" label="사이트 주소를 입력하세요"></v-text-field>
            </v-col>
            <v-col cols="6" md="2">
                <v-btn class="input_btn" outlined="outlined">
                    Check
                </v-btn>
            </v-col>
        </v-row>

    </div>
</template>

<script>
    export default {
        name: "main",
        props: {
            msg: String
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
    .logo {
        /* 이하 필수 설정 */
        position: absolute;
        left: 50%;
        top: 40%;
        transform: translate(-50%, -40%);
        text-align: center;
        
    }
    .main {
        /* 이하 필수 설정 */
        position: absolute;
        left: 50%;
        top: 55%;
        transform: translate(-50%, -55%);
        text-align: center;
        width: 800px;
    }
</style>