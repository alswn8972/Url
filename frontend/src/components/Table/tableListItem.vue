<template>
    <div>
        <md-table v-if="this.urlList" v-model="this.urlList" :table-header-color="tableHeaderColor">
            <md-table-row
                @click="showHistory(item)"
                slot="md-table-row"
                slot-scope="{ item }">
                <md-table-cell md-label="이름">{{ item.urlName }}</md-table-cell>
                <md-table-cell md-label="설명">{{ item.urlContent }}</md-table-cell>
                <md-table-cell md-label="주소">{{ item.urlAddress }}</md-table-cell>
                <md-table-cell md-label="상태">
                    <md-chip v-if="item.urlStatusCode <200">error</md-chip>
                    <md-chip v-if="item.urlStatusCode >= 200 && item.urlStatusCode < 300" class="successChip">{{ item.urlStatusCode }}</md-chip>
                    <md-badge v-else-if="item.urlStatusCode >= 300 && item.urlStatusCode < 400" md-content="!">
                        <md-chip class="wraningChip">{{ item.urlStatusCode }}</md-chip>
                    </md-badge>
                    <md-badge v-else-if="item.urlStatusCode >= 400 && item.urlStatusCode < 500" md-content="!">
                        <md-chip class="dangerChip">{{ item.urlStatusCode }}</md-chip>
                    </md-badge>
                </md-table-cell>
            </md-table-row>
        </md-table>
    </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex';

    export default {
        components: {},
        name: "simple-table",
        props: {
            tableHeaderColor: {
                type: String,
                default: ""
            }
        },
        data() {
            return {selected: []};
        },
        methods: {
            ...mapActions('url', ['requestUrlList']),
            showHistory: function (item) {
                this.$emit('history', item)
            }
        },
        computed: {
            ...mapGetters('user', {userId: 'getUserId'}),
            ...mapGetters('url', {urlList: 'getUrlList'})
        },
        created() {
            setInterval(() => this.requestUrlList(this.userId), 60000);            
        },
        mounted(){
            
        },
    };
</script>
<style scoped="scoped">
    .successChip {
        background-color: cornflowerblue !important;
        color: white !important;
    }
    .wraningChip {
        background-color: rgb(224, 206, 41) !important;
        color: white !important;
    }
    .dangerChip {
        background-color: rgb(233, 93, 93) !important;
        color: white !important;
    }
</style>