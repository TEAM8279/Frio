import RecipesWithSpecified from './specifiedrecipes'
import Axios from 'axios'

export default class Data {
  constructor () {
    // Class variable description
    this.base_url_path = 'https://parallgames.net:58279'
    this.img_path = 'http://parallgames.net/assets/img/'
    this.ingredients = []
    // Get of ingredients
    Axios.get(this.base_url_path + '/ingredients').then(response => {
      this.ingredients = response.data
    })
  }

  getIngredients () {
    return this.ingredients
  }

  getIngredientById (id) {
    const ingredient = this.ingredients.filter(ingredient => ingredient.id === id)[0]
    return ingredient
  }

  getRecipes (callback) {
    Axios.get(this.base_url_path + '/recipes/top?index=0&count=20').then(response => {
      callback(response.data)
    })
  }

  getImgPath () {
    return this.img_path
  }

  getRecipesWithSpecifiedIngredients (ingredients) {
    return RecipesWithSpecified
  }

  getRecipe (id, callback) {
    Axios.get(this.base_url_path + '/recipes/' + id).then(response => {
      callback(response.data)
    })
  }

  getRecipesAtEnd (index, callback) {
    Axios.get(this.base_url_path + '/recipes/top?index=' + index + '&count=20').then(response => {
      callback(response.data)
    })
  }
}
