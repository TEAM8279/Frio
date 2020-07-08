package frio;

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
}
