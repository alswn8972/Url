<template>

    <v-container>
        <v-row>
            <v-col cols="12" sm="12">
                <img src="../assets/images/main.png" max-height="125" class="logo"/>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="12" sm="2"></v-col>
            <v-col cols="12" sm="8">
                <v-row>
                    <v-col cols="12" sm="8">
                        <v-text-field class="input" label="사이트 주소를 입력하세요"></v-text-field>
                    </v-col>

                    <v-col cols="12" sm="4">
                        <v-btn class="input_btn" outlined="outlined">
                            Check
                        </v-btn>
                    </v-col>
                </v-row>

            </v-col>
            <v-col cols="12" sm="2"></v-col>

        </v-row>
    </v-container>
    <!-- <v-row class="main"> <img src="../assets/images/main.png" max-height="125"
    class="logo" /> <v-col cols="12" sm="6" md="10"> <v-text-field class="input"
    label="사이트 주소를 입력하세요"></v-text-field> </v-col> <v-col cols="6" md="2"> <v-btn
    class="input_btn" outlined="outlined"> Check </v-btn> </v-col> </v-row> -->

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

    .main {
        /* 이하 필수 설정 */
        position: absolute;
        left: 50%;
        top: 55%;
        transform: translate(-50%, -50%);
        text-align: center;
        width: 800px;
    }
</style>