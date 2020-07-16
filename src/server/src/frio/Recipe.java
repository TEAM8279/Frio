package frio;

import java.util.ArrayList;

public class Recipe {
	private static int recipesCount = 0;

	private int id;
	private String name;
	private String icon;
	private String[] instructions;
	private IngredientRow[] ingredientsRows;

	public Recipe(String name, String icon, String[] instructions, IngredientRow[] ingredientsRows) {
		id = recipesCount++;
		this.name = name;
		this.icon = icon;
		this.instructions = instructions;
		this.ingredientsRows = ingredientsRows;
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIcon() {
		return icon;
	}

	public String[] getInstructions() {
		return instructions;
	}

	public IngredientRow[] getIngredientsRows() {
		return ingredientsRows;
	}

	public boolean containsIngredient(int id) {
		for (int i = 0; i < ingredientsRows.length; i++) {
			if (ingredientsRows[i].getIngredientID() == id) {
				return true;
			}
		}

		return false;
	}

	public int[] getMissingIngredients(int[] ingredients) {
		ArrayList<Integer> missing = new ArrayList<>();

		for (int i = 0; i < ingredientsRows.length; i++) {
			boolean miss = true;

			for (int j = 0; j < ingredients.length; j++) {
				if (ingredients[j] == ingredientsRows[i].getIngredientID()) {
					miss = false;
					break;
				}
			}

			if (miss) {
				missing.add(ingredientsRows[i].getIngredientID());
			}
		}

		int[] out = new int[missing.size()];

		for (int i = 0; i < out.length; i++) {
			out[i] = missing.get(i);
		}

		return out;
	}

	public int[] getUsedIngredients(int[] ingredients) {
		ArrayList<Integer> used = new ArrayList<>();

		for (int i = 0; i < ingredients.length; i++) {
			if (containsIngredient(ingredients[i])) {
				used.add(ingredients[i]);
			}
		}

		int[] out = new int[used.size()];

		for (int i = 0; i < out.length; i++) {
			out[i] = used.get(i);
		}

		return out;
	}
}
