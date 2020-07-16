import Axios from 'axios'

export default class Data {
  constructor () {
    // Class variable description
    this.base_url_path = 'https://parallgames.net:58279'
    this.img_path = 'http://parallgames.net/assets/img/'
    this.ingredients = []
    this.selectedIngredients = []
    this.recipes = []

    // Drop item in class variables
    this.getIngredients()
    this.getRecipes()
  }

  // gestion d'ingrédient
  getIngredients () {
    Axios.get(this.base_url_path + '/ingredients').then(response => {
      this.ingredients = response.data
    })
  }

  getIngredientById (id) {
    return this.ingredients.filter(ingredient => ingredient.id === id)[0]
  }

  // gestion de liste de recettes
  getRecipes () {
    Axios.get(this.base_url_path + '/recipes/top?index=0&count=20').then(response => {
      this.recipes = response.data
    })
  }

  getRecipesWithSpecifiedIngredients () {
    // eslint-disable-next-line camelcase
    let s_ingredients = ''
    this.selectedIngredients.forEach(item => {
      // eslint-disable-next-line camelcase
      s_ingredients += item + ','
    })
    // eslint-disable-next-line camelcase
    const url = this.base_url_path + '/recipes/search?ingredients=' + s_ingredients
    Axios.get(url).then(response => {
      this.recipes = response.data
    })
  }

  getRecipesAtEnd () {
    Axios.get(this.base_url_path + '/recipes/top?index=' + this.recipes.length + '&count=20').then(response => {
      console.log(response.data)
    })
  }

  // gestion de recette
  getRecipe (id, callback) {
    Axios.get(this.base_url_path + '/recipes/' + id).then(response => {
      callback(response.data)
    })
  }
}
