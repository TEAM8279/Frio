package frio;

public class IngredientRow {

	private int ingredientID;
	private int quantity;
	private String unit;

	public IngredientRow(int ingredientID, int quantity, String unit) {
		this.ingredientID = ingredientID;
		this.quantity = quantity;
		this.unit = unit;
	}

	public int getIngredientID() {
		return ingredientID;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getUnit() {
		return unit;
	}
}
