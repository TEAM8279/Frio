<template>
    <div ref="mainDiv" :class="{selected : isSelected}" @click="selectIngredient(ingredient.id)">
      <img :src="imgBasePath + ingredient.icon" :alt="ingredient.name">
      <p>{{ ingredient.name }}</p>
    </div>
</template>

<script>
export default {
  name: 'Ingredient',
  props: ['ingredient', 'imgBasePath', 'objectData'],
  data () {
    return {
      isSelected: false
    }
  },
  methods: {
    selectIngredient (id) {
      if (this.isSelected) {
        this.isSelected = false
        this.objectData.selectedIngredients.splice(this.objectData.selectedIngredients.indexOf(id), 1)
      } else {
        this.isSelected = true
        this.objectData.selectedIngredients.push(id)
      }
    }
  }
}
</script>

<style scoped lang="scss">
div {
  margin: calc((100vw - 300px) / 6);
  width: 100px;
  height: 100px;
  text-align: center;
  background-color: #2A2A2E;
  padding: 10px;
  border-radius: 4px;
  font-size: 18px;
  font-family: 'Roboto', '-apple-system', 'Helvetica Neue', Helvetica, Arial, sans-serif;
  color: #F7A62B;
  img {
    max-width: 50px;
    max-height: 50px;
  }
}
div.selected {
  background-color: #00bcd4;
}
</style>
