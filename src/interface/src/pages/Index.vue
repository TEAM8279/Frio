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
          <q-btn style="background-color: #F7A62B" class="button-ingredient" label="Ingrédients" />
          <div class="explore">
            <p>Recettes</p>
            <img src="../assets/chevron-down.png" alt="down">
          </div>
        </div>
      </q-tab-panel>
      <q-tab-panel v-for="recipe in recipes" :key="recipe.id" :name="recipe.id">
        <recipe :recipe="recipe" :img_path="objectData.getImgPath()" :object-data="objectData" :recipes-view="$refs.recipesView"></recipe>
      </q-tab-panel>
    </q-tab-panels>
  </q-page>
</template>

<script>
import Recipe from 'components/Recipe'
export default {
  name: 'PageIndex',
  props: ['objectData'],
  components: {
    Recipe
  },
  watch: {
    panel: function () {
      if (this.panel === this.recipes[this.recipes.length - 1].id) {
        this.objectData.getRecipesAtEnd(this.recipes.length, data => {
          this.recipes = this.recipes.concat(data)
        })
      }
    }
  },
  data () {
    return {
      recipes: [],
      panel: 'main'
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
.landing {
  height: 100vh;
  h1, div.explore p {
    color: #f7a62b
  }
  .button-ingredient {
    color: #2A2A2E;
    margin: 5vh auto 0 auto;
    display: block;
    font-size: 24px;
  }
  h1 {
    font-size: 60px;
    font-family: 'Roboto', sans-serif;
    text-align: center;
    font-weight: normal;
    padding-top: 10vh;
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
</style>
