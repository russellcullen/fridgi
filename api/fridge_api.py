class FridgeApi:
	def __init__(self, api):
		self.api = api

	# untested
	def can_cook(self, recipe_name, fridge_name):

		r = self.api.get_recipe_info(recipe_name)
		if r is None:
			return False

		recipe_ingredients = r['ingredients']
		fridge_ingredients = self.api.get_current_ingredients(fridge_name)

		hasIngredients = True
		for r_i in recipe_ingredients:
			hasIngredient = False
			for f_i in fridge_ingredients:
				if (r_i['name'] == f_i['name']) and (f_i['quantity'] >= r_i['quantity']):
					hasIngredient = True
			hasIngredients = hasIngredients and hasIngredient

		return hasIngredients

	# untested
	def search_fridge_recipes(self, query, fridge):
		recipe_list = self.search_recipes(query)
		for r in recipe_list:
			if self.can_cook(r['name'], fridge):
				r['can_cook'] = True
			else:
				r['can_cook'] = False		
		return recipe_list

	# untested
	def suggest_by_current_recipe(self, recipe_id, fridge):
		print recipe_id
		recipe_list = self.api.search_by_current_recipe(recipe_id)
		for recipe in recipe_list:
			if recipe['_id'] == recipe_id:
				continue
			if self.can_cook(recipe['name'], fridge):
				return recipe
		return {}

	# untested
	def add_item_to_grocery_list(self, recipe_ingredient, fridge_name):
		return self.api.add_item_to_grocery_list(recipe_ingredient, fridge_name)







