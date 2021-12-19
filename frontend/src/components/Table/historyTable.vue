<template>
  <div>
    <md-table  v-model="urlHistory" :table-header-color="tableHeaderColor">
      <md-table-row slot="md-table-row" slot-scope="{ item }">
        <md-table-cell md-label="확인 시간">{{ item.createdDate }}</md-table-cell>
        <md-table-cell md-label="상태 코드">{{ item.statusCode }}</md-table-cell>
      </md-table-row>
    </md-table>
  </div>
</template>

<script>
import {mapGetters} from 'vuex';
export default {
  name: "ordered-table",
  props: {
    tableHeaderColor: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      selected: [],
      
    };
  },
  computed: {
            ...mapGetters('url', {
                urlHistory: 'getUrlHistory'
            }),
    },
};
</script>
