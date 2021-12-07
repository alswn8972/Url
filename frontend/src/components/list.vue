<template>
    <v-list two-line>
      <v-list-item-group
        v-model="selected"
        active-class="pink--text"
        multiple
      >
        <template v-for="(item, index) in items">
          <v-list-item :key="item.title">
            <template v-slot:default="{ active }">
              <v-list-item-content>
                <v-list-item-title v-text="item.title"></v-list-item-title>

                <v-list-item-subtitle
                  class="text--primary"
                  v-text="item.headline"
                ></v-list-item-subtitle>

                <v-list-item-subtitle v-text="item.subtitle"></v-list-item-subtitle>
              </v-list-item-content>

              <v-list-item-action>
                <v-list-item-action-text v-text="item.action"></v-list-item-action-text>

                <v-icon
                  v-if="!active"
                  color="grey lighten-1"
                >
                  mdi-star-outline
                </v-icon>

                <v-icon
                  v-else
                  color="yellow darken-3"
                >
                  mdi-star
                </v-icon>
              </v-list-item-action>
            </template>
          </v-list-item>

          <v-divider
            v-if="index < items.length - 1"
            :key="index"
          ></v-divider>
        </template>
      </v-list-item-group>
      <v-fab-transition>
              <v-btn
                class="add_btn"
                color="blue"
                absolute
                bottom
                right
                fab
                @click="showDialog()"
              >
                +
              </v-btn>
      </v-fab-transition>
      <v-dialog v-model="listDialog" max-width="500">
            <listModal @hide="hideDialog()" @submit="submit">
                <template v-slot:body>
                    <v-text-field placeholder="내용을 입력하세요"/>
                </template>
            </listModal>
      </v-dialog>
    </v-list>
    
</template>

<script>
import listModal from "./listModal.vue"
  export default {
    name: "list",
    components: {
        listModal
    },
    data: () => ({
      selected: [2],
      isClick:false,
      listDialog : false,
      items: [
        {
          action: '15 min',
          headline: 'Brunch this weekend?',
          subtitle: `I'll be in your neighborhood doing errands this weekend. Do you want to hang out?`,
          title: 'Ali Connors',
        },
        {
          action: '2 hr',
          headline: 'Summer BBQ',
          subtitle: `Wish I could come, but I'm out of town this weekend.`,
          title: 'me, Scrott, Jennifer',
        },
        {
          action: '6 hr',
          headline: 'Oui oui',
          subtitle: 'Do you have Paris recommendations? Have you ever been?',
          title: 'Sandra Adams',
        },
        {
          action: '12 hr',
          headline: 'Birthday gift',
          subtitle: 'Have any ideas about what we should get Heidi for her birthday?',
          title: 'Trevor Hansen',
        },
        {
          action: '18hr',
          headline: 'Recipe to try',
          subtitle: 'We should eat this: Grate, Squash, Corn, and tomatillo Tacos.',
          title: 'Britta Holt',
        },
      ],
    }),
    methods:{
      showDialog() {
        this.listDialog = true
      },
      hideDialog() {
        this.listDialog = false
      },
    },
  }
</script>
<style scoped>
.add_btn {
  margin-bottom: 50px ;
}
</style>