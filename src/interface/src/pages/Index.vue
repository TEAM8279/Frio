<template>
  <q-page>
    <q-tab-panels ref="recipesView"
      v-model="panel"
      animated
      swipeable
      vertical
    >
      <q-tab-panel name="main">
        <div class="landing">
          <h1>Donne moi une recette avec</h1>
          <q-scroll-area
            horizontal
            style="height: 110px"
            class="listing-ingredient"
          >
            <q-btn label="+" class="button-ingredient" @click="dialog = true"></q-btn>
            <ingredient v-for="ingredient in objectData.selectedIngredients" :key="ingredient.id" :ingredient="objectData.getIngredientById(ingredient)" :object-data="objectData"></ingredient>
          </q-scroll-area>
          <q-dialog
            @close="panel = '0'"
            v-model="dialog"
            persistent
            :maximized="maximizedToggle"
            transition-show="slide-up"
            transition-hide="slide-down"
          >
            <ingredients :object-data="objectData"></ingredients>
            <q-btn style="background-color: #F7A62B" class="button-search" label="Chercher" @click="searchRecipes" v-close-popup/>
          </q-dialog>
          <div class="explore">
            <p>Recettes</p>
            <img src="../assets/chevron-down.png" alt="down">
          </div>
        </div>
      </q-tab-panel>
      <q-tab-panel v-for="(recipe, index) in objectData.recipes" :key="recipe.id" :name="index">
        <recipe :recipe="recipe" :img_path="objectData.img_path" :object-data="objectData" :recipes-view="$refs.recipesView"></recipe>
      </q-tab-panel>
    </q-tab-panels>
  </q-page>
</template>

<script>
import Recipe from 'components/Recipe'
import Ingredients from 'components/Ingredients'
import Ingredient from 'components/Ingredient'
export default {
  name: 'PageIndex',
  props: ['objectData'],
  components: {
    Recipe,
    Ingredients,
    Ingredient
  },
  watch: {
    panel: function () {
      if (this.panel === this.objectData.recipes[this.objectData.recipes.length - 1].id) {
        this.objectData.getRecipesAtEnd()
      }
    }
  },
  data () {
    return {
      panel: 'main',
      dialog: false,
      maximizedToggle: true
    }
  },
  mounted () {
    this.objectData.getRecipes(data => {
      this.recipes = data
    })
  }
}
</script>

<style scoped lang="scss">
  .button-ingredient {
    color: #2A2A2E;
    background-color: #F7A62B;
    display: block;
    font-size: 24px;
    width:100px;
    height: 100px;
  }
.landing {
  height: 100vh;
  h1, div.explore p {
    color: #f7a62b
  }
  h1 {
    font-size: 60px;
    font-family: 'Roboto', sans-serif;
    text-align: center;
    font-weight: normal;
    padding-top: 10vh;
    max-width: 320px;
    margin: auto;
  }
  div.explore {
    text-align: center;
    width: 100vw;
    position: absolute;
    top: 100vh;
    transform: translateY(-100%);
    p {
      font-size: 24px;
      margin: 3px;
    }
  }
}
.q-panel.scroll .q-tab-panel{
  background-color: #2A2A2E;
  padding: 0;
}
.somesome {
  display: flex;
  flex-direction: row;
}
</style>
