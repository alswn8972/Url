<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100"
      >
        <md-card>
          <md-card-header data-background-color="blue">
            <h4 @click="visibleSuccessful =!visibleSuccessful" class="title">Successful responses
                <md-icon v-show="!visibleSuccessful" class=" myicon">add</md-icon> 
                <md-icon v-show="visibleSuccessful" class=" myicon">menu</md-icon> 
                </h4>   

          </md-card-header>
          <md-card-content>
            <md-table v-model="success" v-show="visibleSuccessful">
              <md-table-row slot="md-table-row" slot-scope="{ item }">
                <md-table-cell md-label="상태">{{ item.status }}</md-table-cell>
                <md-table-cell md-label="코드">{{ item.code }}</md-table-cell>
                <md-table-cell md-label="뜻">{{ item.content }}</md-table-cell>
              </md-table-row>
            </md-table>
          </md-card-content>
        </md-card>
      </div>
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100"
      >
        <md-card>
          <md-card-header data-background-color="orange">
            <h4  @click="visibleRedirection =!visibleRedirection" class="title">Redirection messages 
                <md-icon v-show="!visibleRedirection" class=" myicon">add</md-icon> 
                <md-icon v-show="visibleRedirection" class=" myicon">menu</md-icon> 
            </h4>
          </md-card-header>
          <md-card-content>
            <md-table v-model="redirect" v-show="visibleRedirection">
              <md-table-row slot="md-table-row" slot-scope="{ item }">
                <md-table-cell md-label="상태">{{ item.status }}</md-table-cell>
                <md-table-cell md-label="코드">{{ item.code }}</md-table-cell>
                <md-table-cell md-label="뜻">{{ item.content }}</md-table-cell>
              </md-table-row>
            </md-table>
          </md-card-content>
        </md-card>
      </div>
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100"
      >
        <md-card>
          <md-card-header data-background-color="red">
            <h4   @click="visibleError =!visibleError" class="title">Client error responses
                <md-icon v-show="!visibleError" class=" myicon">add</md-icon> 
                <md-icon v-show="visibleError" class=" myicon">menu</md-icon> 
            </h4>
          </md-card-header>
          <md-card-content>
            <md-table v-model="error" v-show="visibleError">
              <md-table-row slot="md-table-row" slot-scope="{ item }">
                <md-table-cell md-label="상태">{{ item.status }}</md-table-cell>
                <md-table-cell md-label="코드">{{ item.code }}</md-table-cell>
                <md-table-cell md-label="뜻">{{ item.content }}</md-table-cell>
              </md-table-row>
            </md-table>
          </md-card-content>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>
import {
  NavTabsCard,
  NavTabsTable,
} from "@/components";

export default {
  components: {
    NavTabsCard,
    NavTabsTable,
  },
  data() {
    return {
      //여기부터
      visibleSuccessful: true,
      visibleRedirection: true,
      visibleError: true,
      //여기까지
        options: {
          axisX: {
            showGrid: false,
          },
          low: 0,
          high: 1000,
          chartPadding: {
            top: 0,
            right: 5,
            bottom: 0,
            left: 0,
          },
        },
        success: [
          {
            status:"OK",
            code : "200",
            content : "요청이 성공적으로 되었습니다. 정보는 요청에 따른 응답으로 반환됩니다."
          },
          {
            status:"Created",
            code : "201",
            content : "요청이 성공적이었으며 그 결과로 새로운 리소스가 생성되었습니다."
          },
         {
            status:"Accepted",
            code : "202",
            content : "요청을 수신하였지만, 그에 응하여 행동할 수 없습니다."
          },
          {
            status:"Non-Authoritative Information",
            code : "203",
            content : "돌려받은 메타 정보 세트가 오리진 서버의 것과 일치하지 않습니다."
          },
        ],
        redirect: [
          {
            status:"Multiple Choice",
            code : "300",
            content : "요청에 대해서 하나 이상의 응답이 가능합니다."
          },
          {
            status:"Moved Permanently",
            code : "301",
            content : "이 응답 코드는 요청한 리소스의 URI가 변경되었음을 의미합니다. 새로운 URI가 응답에서 아마도 주어질 수 있습니다."
          },
         {
            status:"Found",
            code : "302",
            content : "이 응답 코드는 요청한 리소스의 URI가 일시적으로 변경되었음을 의미합니다."
          },
          {
            status:"Permanent Redirect",
            code : "308",
            content : "리소스가 이제 HTTP 응답 헤더의 Location:에 명시된 영구히 다른 URI에 위치하고 있음을 의미합니다."
          },
        ],
        responsiveOptions: [
          [
            "screen and (max-width: 640px)",
            {
              seriesBarDistance: 5,
              axisX: {
                labelInterpolationFnc: function (value) {
                  return value[0];
                },
              },
            },
          ],
        ],
        //여기부터
        error : [
        {
            status:"Multiple Choice",
            code : "300",
            content : "요청에 대해서 하나 이상의 응답이 가능합니다."
          },
          {
            status:"Moved Permanently",
            code : "301",
            content : "이 응답 코드는 요청한 리소스의 URI가 변경되었음을 의미합니다. 새로운 URI가 응답에서 아마도 주어질 수 있습니다."
          },
         {
            status:"Found",
            code : "302",
            content : "이 응답 코드는 요청한 리소스의 URI가 일시적으로 변경되었음을 의미합니다."
          },
          {
            status:"Permanent Redirect",
            code : "308",
            content : "리소스가 이제 HTTP 응답 헤더의 Location:에 명시된 영구히 다른 URI에 위치하고 있음을 의미합니다."
          },
        ]
        //여기까지
      }
  },
  methods :{
    showSucc(){
      console.log("clcik")
      
    }
  }
};
</script>
<style scoped>
 .myicon{
    float: right;
 }
</style>