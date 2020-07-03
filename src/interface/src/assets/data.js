import Ingredients from './ingredients'
import Recipes from './recipes'
import RecipesWithSpecified from './specifiedrecipes'
import Recipe from './recipe'

export default {
  getIngredient () {
    return Ingredients
  },
  getRecipes () {
    return Recipes
  },
  getRecipesWithSpecifiedIngredients (ingredients) {
    return RecipesWithSpecified
  },
  getRecipe (id) {
    return Recipe
  },
  getRecipesAtEnd (id) {
    return [{
      id: id + 1,
      name: 'Poulet au curry',
      image: 'https://images.unsplash.com/photo-1588166524941-3bf61a9c41db?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=631&q=80'
    }]
  }
}
