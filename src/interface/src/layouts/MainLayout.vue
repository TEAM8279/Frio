<template>
  <q-layout>
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: 'MainLayout',
  data () {
    return {
    }
  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
}
.q-layout {
  background-color: #2A2A2E;
}
</style>
