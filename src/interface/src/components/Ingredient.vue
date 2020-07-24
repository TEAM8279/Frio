<template>
    <div ref="mainDiv" :class="{selected : isSelected}" @click="selectIngredient(ingredient.id)">
      <img :src="objectData.img_path + ingredient.icon" :alt="ingredient.name">
      <p>{{ ingredient.name }}</p>
    </div>
</template>

<script>
export default {
  name: 'Ingredient',
  props: ['ingredient', 'imgBasePath', 'objectData'],
  data () {
    return {
      isSelected: this.objectData.isIngredientSelected(this.ingredient.id)
    }
  },
  methods: {
    selectIngredient (id) {
      if (this.isSelected) {
        this.isSelected = false
        this.objectData.removeIngredient(id)
      } else {
        this.isSelected = true
        this.objectData.selectIngredient(id)
      }
    }
  }
}
</script>

<style scoped lang="scss">
div {
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
  background-color: #F7A62B;
  color: #2A2A2E;
}
</style>
