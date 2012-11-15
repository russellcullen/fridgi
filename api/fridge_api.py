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

		for r_i in recipe_ingredients:
			if not self.hasIngredient(r_i, fridge_ingredients):
				return False

		return True

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

	# untested
	def add_recipe_ingredients_to_grocery_list(self, recipe_id, fridge_name):
		recipe = self.api.get_recipe_by_id(recipe_id)
		recipe_ingredients = self.api.get_ingredients_by_recipe(recipe['name'])
		fridge_ingredients = self.api.get_current_ingredients(fridge_name)

		for r_i in recipe_ingredients:
			if not self.hasIngredient(r_i, fridge_ingredients):
				self.add_item_to_grocery_list(r_i, fridge_name)

	# untested
	# maybe add parameters for rating/last_used later
	def use_recipe(self, recipe_id, fridge_name):
		recipe = self.api.get_recipe_by_id(recipe_id)
		recipe_ingredients = self.api.get_ingredients_by_recipe(recipe['name'])
		fridge_ingredients = self.api.get_current_ingredients(fridge_name)

		for r_i in recipe_ingredients:
			self.api.remove_ingredient(r_i['name'], fridge_name, r_i['quantity'])

		# self.api.use_recipe(Time.time())



	# untested, helper
	def hasIngredient(self, recipe_ingredient, fridge_ingredients):
		for f_i in fridge_ingredients:
			if (recipe_ingredient['name'] == f_i['name'] and (f_i['quantity'] >= recipe_ingredient['quantity'])):
				return True
		return False








