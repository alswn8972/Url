<template v-slot:extension>
    <v-tabs align-with-title="align-with-title">
        <v-tabs-slider color="yellow"></v-tabs-slider>

        <v-tab v-for="item in items" :key="item">
            {{ item }}
        </v-tab>
    </v-tabs>
</template>

<script>
    export default {
    data () {
      return {
        tab: null,
        
        text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
      }
    },
  }
</script>

<style></style>