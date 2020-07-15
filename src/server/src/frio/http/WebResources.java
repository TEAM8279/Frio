package frio.http;

import java.util.ArrayList;

import frio.Ingredient;
import frio.IngredientRow;
import frio.Recipe;
import frio.Storage;

public class WebResources {
	public static HTTPResponse postResource(HTTPRequest request) {
		return null;
	}

	public static HTTPResponse getResource(HTTPRequest request) {
		HTTPResponse response = new HTTPResponse();

		String[] parts = request.getPath().split("/");

		if (parts.length == 1) {
			if (parts[0].equals("ingredients")) {

				response.setCode(200);

				response.addHeader("Access-Control-Allow-Origin", "*");
				response.addHeader("Content-Type", "application/json");

				response.setData(createIngredientsJSON());

				return response;
			}
		} else if (parts.length == 2) {
			if (parts[0].equals("recipes")) {
				if (parts[1].equals("top")) {
					String indexString = request.getParameters().get("index");
					String countString = request.getParameters().get("count");

					try {
						int index = Integer.parseInt(indexString);

						int count = 20; // Default value

						if (countString != null) {
							count = Integer.parseInt(countString);
						}

						ArrayList<Recipe> selected = new ArrayList<>();

						for (int i = 0; i < count; i++) {
							if (i + index >= Storage.recipesCount()) {
								break;
							}

							selected.add(Storage.getRecipe(i + index));
						}

						response.setCode(200);

						response.addHeader("Access-Control-Allow-Origin", "*");
						response.addHeader("Content-Type", "application/json");

						response.setData(createRecipeListJSON(selected));

						return response;
					} catch (Exception e) {

					}
				} else if (parts[1].equals("search")) {
					String name = request.getParameters().get("name");

					if (name != null) {
						ArrayList<Recipe> selected = new ArrayList<>();

						for (int i = 0; i < Storage.recipesCount(); i++) {
							Recipe recipe = Storage.getRecipe(i);

							if (containsAllChars(recipe.getName(), name)) {
								selected.add(recipe);
							}
						}

						response.setCode(200);

						response.addHeader("Access-Control-Allow-Origin", "*");
						response.addHeader("Content-Type", "application/json");

						response.setData(createRecipeListJSON(selected));

						return response;
					}

					// TODO search by ingredients
				} else {
					try {
						int id = Integer.parseInt(parts[1]);

						Recipe recipe = Storage.getRecipeByID(id);

						if (recipe != null) {
							response.setCode(200);

							response.addHeader("Access-Control-Allow-Origin", "*");
							response.addHeader("Content-Type", "application/json");

							response.setData(createRecipeJSON(recipe));

							return response;
						}
					} catch (Exception e) {

					}
				}
			}
		}

		response.setCode(404);

		return response;
	}

	private static String createIngredientsJSON() {
		StringBuilder json = new StringBuilder("[");

		for (int i = 0; i < Storage.ingredientsCount(); i++) {
			Ingredient ingredient = Storage.getIngredient(i);

			if (i > 0) {
				json.append(",");
			}

			json.append("{");

			json.append("\"id\":" + ingredient.getID() + ",");

			json.append("\"name\":\"" + ingredient.getName() + "\",");

			json.append("\"icon\":\"" + ingredient.getIcon() + "\"");

			json.append("}");
		}

		json.append("]");

		return json.toString();
	}

	private static String createRecipeListJSON(ArrayList<Recipe> selected) {
		StringBuilder json = new StringBuilder("[");

		for (int i = 0; i < selected.size(); i++) {
			Recipe recipe = selected.get(i);

			if (i > 0) {
				json.append(",");
			}

			json.append("{");

			json.append("\"id\":" + recipe.getID() + ",");

			json.append("\"name\":\"" + recipe.getName() + "\",");

			json.append("\"icon\":\"" + recipe.getIcon() + "\"");

			json.append("}");
		}

		json.append("]");

		return json.toString();
	}

	private static String createRecipeJSON(Recipe recipe) {
		String[] instructions = recipe.getInstructions();
		IngredientRow[] ingredientsRows = recipe.getIngredientsRows();

		StringBuilder json = new StringBuilder("{");

		json.append("\"id\":" + recipe.getID() + ",");

		json.append("\"name\":\"" + recipe.getName() + "\",");

		json.append("\"icon\":\"" + recipe.getIcon() + "\",");

		json.append("\"instructions\":[");
		for (int i = 0; i < instructions.length; i++) {
			if (i > 0) {
				json.append(",");
			}

			json.append("\"" + instructions[i] + "\"");
		}
		json.append("],");

		json.append("\"ingredients\":[");
		for (int i = 0; i < ingredientsRows.length; i++) {
			if (i > 0) {
				json.append(",");
			}

			json.append("{");

			json.append("\"id\":" + ingredientsRows[i].getIngredientID() + ",");

			json.append("\"quantity\":" + ingredientsRows[i].getQuantity() + ",");

			json.append("\"unit\":\"" + ingredientsRows[i].getUnit() + "\"");

			json.append("}");
		}
		json.append("]");

		json.append("}");

		return json.toString();
	}

	private static boolean containsAllChars(String str, String chars) {
		for (int i = 0; i < chars.length(); i++) {
			if (!str.contains(chars.substring(i, i + 1))) {
				return false;
			}
		}

		return true;
	}
}