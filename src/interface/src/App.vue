<template>
  <div id="q-app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
img {
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -o-user-select: none;
  user-select: none;
}
/* style des aliments séléctionné */
.listing-ingredient.q-scrollarea {
  padding: 0 10px;
}
.listing-ingredient.q-scrollarea .scroll.relative-position.fit.hide-scrollbar .absolute.full-height {
  display: flex;
  button, div.selected {
    margin-left: 10px;
  }
}
</style>
