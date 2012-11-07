import database_api


class FridgeApi:
	def __init__(self, db = 'heroku_app8911714'):
		self.api = database_api.DatabaseApi(db)

	# input  : recipe name as string
	# return : boolean representing if fridge has necessary ingredients
	def can_cook(self, recipe, fridge):
		# check if recipe in recipes collection
		r = self.api.get_recipe_info(recipe)
		if r is None:
			return False
		r_ingredients = r['ingredients']
		
		# get fridge ingredients
		f_ingredients = self.api.get_current_ingredients(fridge)

		# look through Recipe Ingredient objects, compare to Fridge Ingredients
		hasIngredients = True
		for r_i in r_ingredients:
			hasIngredient = False
			for f_i in f_ingredients:
				if (r_i['name'] == f_i['name']) and (f_i['quantity'] >= r_i['quantity']):
					hasIngredient = True
			hasIngredients = hasIngredients and hasIngredient

		return hasIngredients

	def search_recipes(self, query):
		tag_list = str.split(query)
		return self.api.find_recipe_by_tag(tag_list)

	def search_fridge_recipes(self, query, fridge):
		recipe_list = self.search_recipes(query)
		for r in recipe_list:
			if not self.can_cook(r['name'], fridge):
				recipe_list.remove(r)
				
		return recipe_list



