<template>
  <div class="hello">
    <v-row class="main">
        <v-col cols="12"
        sm="6"
        md="9">
          <v-text-field class="input" label="사이트 주소를 입력하세요"></v-text-field>
        </v-col>
        <v-col
        cols="6"
        md="3">
        <v-btn class="input_btn" outlined="outlined">
                    Check
                </v-btn>
        </v-col>
      </v-row>
    
    
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  props: {
    msg: String,
  },
  
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
body,html { 
    margin: auto; /* 화면 축소시 스크롤 방지 */
}
.main {
  /* 이하 필수 설정 */
  position: absolute;
  left: 50%; top: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  width: 800px;
  }
</style>
