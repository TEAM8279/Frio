package frio;

public class Ingredient {
	private static int ingredientsCount = 0;

	private int id;
	private String name;
	private String icon;

	public Ingredient(String name, String icon) {
		id = ingredientsCount++;
		this.name = name;
		this.icon = icon;
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
}
