<template>
  <q-tab-panels
    @before-transition="loadRecipe"
    v-model="panel"
    animated
    swipeable
    class="myTiles"
  >
    <q-tab-panel name="view">
      <div class="recipe" ref="recipe">
        <div @click="switchToOther">
          <arrow-right></arrow-right>
        </div>
        <div class="element" @click="switchToOther">
          <p class="title">{{ recipe.name }}</p>
          <p class="missing" v-show="recipe.missing_ingredients.length > 0">
            manquant: <span v-for="(ingredient_missed, index) in recipe.missing_ingredients" :key="ingredient_missed">{{ objectData.getIngredientById(ingredient_missed).name }}<span v-if="index != recipe.missing_ingredients.length -1">, </span></span>
          </p>
        </div>
        <div class="filter"></div>
        <div class="img" :style="'background-image: url(\'' + img_path + recipe.icon + '\')'"></div>
      </div>
    </q-tab-panel>
    <q-tab-panel name="recipe" style="color: #fff" class="recipeFullView">
      <div @click="switchToOther">
        <arrow-left></arrow-left>
      </div>
      <img :src="img_path + fullRecipe.icon" alt="Image de la recette" />
      <p class="title full recipe">{{ fullRecipe.name }}</p>
      <p class="ingredients title">Ingrédients</p>
      <ul>
        <li v-for="ingredient in fullRecipe.ingredients" :key="ingredient.id">{{ ingredient.quantity + ingredient.unit + " " + objectData.getIngredientById(ingredient.id).name }}</li>
      </ul>
      <p class="step title">Marche à suivre</p>
      <ol>
        <li v-for="step in fullRecipe.instructions" :key="step.id">{{ step }}</li>
      </ol>
    </q-tab-panel>
  </q-tab-panels>
</template>

<script>
import ArrowRight from 'assets/ArrowRight'
import ArrowLeft from 'assets/ArrowLeft'
export default {
  name: 'Recipe',
  props: ['recipe', 'img_path', 'objectData', 'recipesView'],
  components: {
    ArrowRight,
    ArrowLeft
  },
  data () {
    return {
      panel: 'view',
      fullRecipe: {}
    }
  },
  methods: {
    sendMessage () {
      console.log(this.recipe)
      this.panel = 'recipe'
    },
    loadRecipe () {
      this.recipesView.swipeable = !this.recipesView.swipeable
      if (this.panel === 'recipe') {
        this.objectData.getRecipe(this.recipe.id, response => {
          this.fullRecipe = response
        })
      }
    },
    switchToOther () {
      if (this.panel === 'view') {
        this.panel = 'recipe'
      } else {
        this.panel = 'view'
      }
    }
  }
}
</script>

<style scoped lang="scss">
.recipe {
  height: 100vh;
  overflow: hidden;
  .img {
    opacity: 0.5;
    min-height: 100vh;
    min-width: 100vw;
    background-size: cover;
    background-position-x: center;
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
      margin: 0;
    }
    .missing {
      font-size: 18px;
      padding: 0 20px;
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

.q-panel.scroll .q-tab-panel{
  background-color: #2A2A2E;
  padding: 0;
}
.recipeFullView {
  padding: 0 10px;
}
img {
  max-width: 100%;
}
.title.full.recipe {
  font-size: 48px;
  color: #F7A62B;
  height: 100%;
  padding: 10px;
  margin: 0;
}
.ingredients.title, .step.title {
  margin: 0;
  font-size: 30px;
  color: #F7A62B;
  padding: 0 10px;
}
ul, ol {
  padding: 10px 40px;
  font-size: 20px;
}
ul li, ol li {
  padding: 5px;
}
</style>
