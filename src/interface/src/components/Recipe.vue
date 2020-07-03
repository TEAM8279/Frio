<template>
    <div @ class="recipe" ref="recipe">
      <div class="element">
        <p class="title">{{ recipe.name }}</p>
      </div>
      <div class="filter"></div>
      <img :src="recipe.image" alt="">
    </div>
</template>

<script>
export default {
  name: 'Recipe',
  props: ['recipe'],
  methods: {
    sendMessage () {
      console.log('hello')
    }
  }
}
</script>

<style scoped lang="scss">
.recipe {
  height: 100vh;
  overflow: hidden;
  img {
    opacity: 0.5;
    min-height: 100vh;
    min-width: 100vw;
  }
  .element {
    z-index: 300;
    font-size: 48px;
    color: #F7A62B;
    position: absolute;
    margin-top: 100vh;
    transform: translateY(-100%);
    .title {
      padding: 0 20px 0 20px;
    }
  }
  .filter {
    width: 100vw;
    height: 100vh;
    position: absolute;
    top: 0;
    color: #233445;
    z-index: 100;
  }
}
</style>
