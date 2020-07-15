<template>
    <div class="ingredients">
      <div class="flex">
        <ingredient v-for="ingredient in objectData.ingredients" :key="ingredient.id" :ingredient="ingredient" :img-base-path="objectData.getImgPath()" :object-data="objectData"></ingredient>
      </div>
      <q-btn style="background-color: #F7A62B" class="button-search" label="Chercher" @click="dialog = false" v-close-popup/>
    </div>
</template>

<script>
import Ingredient from 'components/Ingredient'
export default {
  name: 'ingredients',
  props: ['objectData'],
  components: {
    Ingredient
  }
}
</script>

<style scoped lang="scss">
.ingredients {
  height: 100vh;
  .flex {
    justify-content: center;
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
