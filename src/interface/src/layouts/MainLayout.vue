<template>
  <q-layout>
    <q-page-container>
      <router-view :objectData="objectData"/>
    </q-page-container>
  </q-layout>
</template>

<script>
import Data from '../assets/data'
export default {
  name: 'MainLayout',
  data () {
    return {
      objectData: new Data()
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
