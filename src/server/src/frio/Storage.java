package frio;

import java.util.ArrayList;

public class Storage {
	private static ArrayList<Ingredient> ingredients = new ArrayList<>();
	private static ArrayList<Recipe> recipes = new ArrayList<>();

	static {
		addIngredient(new Ingredient("sucre", "sucre.png")); // 0
		addIngredient(new Ingredient("sel", "sel.png")); // 1
		addIngredient(new Ingredient("farine", "farine.png")); // 2
		addIngredient(new Ingredient("oeuf", "oeuf.png")); // 3

		addRecipe(new Recipe("oeuf au plat", "oeuf-au-plat.png", new String[] { "Faire cuire l'oeuf", "Saler l'oeuf" },
				new IngredientRow[] { new IngredientRow(3, 1, "pièce"), new IngredientRow(1, 1, "pincée") })); // 0

		addRecipe(new Recipe("petit pain", "petit-pain.png",
				new String[] { "Mélanger la farine et l'oeuf", "Faire cuire", "Saupoudrer de sucre pour le swag",
						"Non, il n'y a pas d'eau dans mon pain" },
				new IngredientRow[] { new IngredientRow(2, 200, "g"), new IngredientRow(3, 1, "pièce"),
						new IngredientRow(0, 10, "g") })); // 1
	}

	public static synchronized void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
	}

	public static synchronized int ingredientsCount() {
		return ingredients.size();
	}

	public static synchronized Ingredient getIngredient(int index) {
		return ingredients.get(index);
	}

	public static synchronized void addRecipe(Recipe recipe) {
		recipes.add(recipe);
	}

	public static synchronized int recipesCount() {
		return recipes.size();
	}

	public static synchronized Recipe getRecipe(int index) {
		return recipes.get(index);
	}

	public static synchronized Recipe getRecipeByID(int id) {
		for (Recipe r : recipes) {
			if (r.getID() == id) {
				return r;
			}
		}

		return null;
	}
}
