<template>
    <div class="ingredients">
      <div class="flex">
        <ingredient v-for="ingredient in objectData.ingredients" :key="ingredient.id" :ingredient="ingredient" :img-base-path="objectData.img_path" :object-data="objectData"></ingredient>
      </div>
      <q-btn style="background-color: #F7A62B" class="button-search" label="Chercher" @click="searchRecipes" v-close-popup/>
    </div>
</template>

<script>
import Ingredient from 'components/Ingredient'
export default {
  name: 'ingredients',
  props: ['objectData'],
  components: {
    Ingredient
  },
  methods: {
    searchRecipes () {
      this.dialog = false
      this.objectData.getRecipesWithSpecifiedIngredients()
    }
  }
}
</script>

<style scoped lang="scss">
.ingredients {
  height: 100vh;
  .flex {
    justify-content: center;
    max-width: 340px;
    margin: auto;
    div {
      margin: calc((100% - 300px) / 6);
    }
  }
}
.button-search {
  margin: auto;
  position: absolute;
  bottom: 10px;
  left: 50vw;
  transform: translateX(-50%);
}
</style>
