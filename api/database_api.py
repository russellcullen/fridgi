## Updating the global ingredients and recipes database
import pymongo
import creator
import time
import os

class DatabaseApi:

	# Administrative Database Functions

	def __init__(self, db = 'heroku_app8911714'):
		self.creator = creator.ObjectCreator()
		self.connection = pymongo.Connection(os.environ.get('MONGOLAB_URI', None))
		self.db = self.connection[db]

	def add_ingredient(self, ingredient):
		""" Adds ingredient to Ingredients collection """
		ingredients = self.db.ingredients
		ingredients.insert(ingredient)
	  
	def add_recipe(self, recipe):
		""" Adds recipe to Recipes collection """
		recipes = self.db.recipes
		recipes.insert(recipe)

	def add_fridge(self, name):
		""" Adds fridge to Fridge collection """
		fridges = self.db.fridges
		fridges.insert({'name': name, 'ingredients' : [], 'favorite_recipes' : [], 'grocery_list' : []})

	# Ingredient functions

	def get_ingredient_info_from_name(self, ingredient_name):
		ingredients = self.db.ingredients
		return ingredients.find_one({'name' : ingredient_name})

	def get_ingredient_info_from_upc(self, upc):
		ingredients = self.db.ingredients
		return ingredients.find_one({'upc' : upc})

	def get_ingredients_by_recipe(self, recipe_name):
		r = get_recipe_info(recipe_name)
		return r['ingredients']

	# Recipe functions

	def get_recipe_info(self, recipe_name):
		recipes = self.db.recipes
		return recipes.find_one({'name' : recipe_name})

	# untested
	def get_recipe_by_id(self, recipe_id):
		recipes = self.db.recipes
		return recipes.find_one({'_id' : recipe_id})

	# untested
	def update_recipe_time_by_id(self, recipe_id):
		recipes = self.db.recipes
		recipes.update({'_id' : recipe_id}, {'$set' : {'last_used' : time.time()}})

	def get_recipes_by_ingredients(self, ingredient_name):
		recipes = self.db.recipes
		return list(recipes.find({'ingredients.name' : ingredient_name}))

	def find_recipe_by_tag(self, tag_list):
		recipes = self.db.recipes
		recipe_list = list(recipes.find({'tags' : {'$in' : tag_list}}))
		
		tag_set = set(tag_list)
		for recipe in recipe_list:
			recipe['relevance'] = len(set(recipe['tags']) & tag_set)

		return sorted(recipe_list, cmp=lambda  x,y: - cmp(x['relevance'],y['relevance']))

	def search_recipes(self, query):
		tag_list = str.split(query)
		return self.find_recipe_by_tag(tag_list)

	# untested
	def search_by_current_recipe(self, recipe_id):
		current_recipe = self.get_recipe_by_id(recipe_id)
		current_recipe_tags = current_recipe['tags']
		return self.find_recipe_by_tag(current_recipe_tags)

	# Fridge functions

	def get_fridge(self, fridge_name):
		fridges = self.db.fridges
		fridge = fridges.find_one({'name' : fridge_name})
		return fridge

	def get_current_ingredients(self, fridge_name):
		fridge = self.get_fridge(fridge_name)
		return fridge['ingredients']

	def insert_ingredient(self, ingredient_name, fridge_name):
		fridges = self.db.fridges
		i = self.get_ingredient_info_from_name(ingredient_name)
		f = fridges.find_one({'name' : fridge_name, 'ingredients.name' : ingredient_name})
		if (f != None):
			for ins in f['ingredients']:
				if (ins['name'] == ingredient_name):
					self.update_ingredient(ingredient_name, ins['quantity']+i['quantity'], fridge_name)
		else:
			fridge_ingredient = self.creator.create_fridge_ingredient(i['_id'], i['name'], i['quantity'], time.time(), 0, i['default_tags'])
			fridges.update({'name' : fridge_name}, {'$push' : {'ingredients' : fridge_ingredient}})

	def remove_ingredient(self, ingredient_name, fridge_name, quantity):
		fridges = self.db.fridges
		f = fridges.find_one({'name' : fridge_name, 'ingredients.name' : ingredient_name})
		if (f != None):
			for ins in f['ingredients']:
				if (ins['name'] == ingredient_name):
					self.update_ingredient(ingredient_name, ins['quantity']-quantity, fridge_name)

	# untested
	def update_ingredient(self, ingredient_name, quantity, fridge_name):
		ingredients = self.get_current_ingredients(fridge_name)
		for i in ingredients:
			if (i['name'] == ingredient_name):
				if (quantity > 0):
					i['quantity'] = quantity
				else:
					ingredients.remove(i)
		fridges = self.db.fridges
		fridges.update({'name' : fridge_name}, {'$set' : {'ingredients' : ingredients}})

	# untested
	def add_item_to_grocery_list(self, recipe_ingredient, fridge_name):
		fridges = self.db.fridges
		fridges.update({'name' : fridge_name}, {'$push' : {'grocery_list' : recipe_ingredient}})

	#untested
	def remove_item_from_grocery_list(self, recipe_ingredient, fridge_name):
		fridges = self.db.fridges
		fridge = self.get_fridge(fridge_name)
		grocery_list = fridge['grocery_list']
		grocery_list.remove(recipe_ingredient)
		fridges.update({'name' : fridge_name}, {'$set' : {'grocery_list' : grocery_list}})

	# Functions used for Testing

	def get_all_ingredients(self):
		ingredients = self.db.ingredients
		return list(ingredients.find())

	def get_all_recipes(self):
		recipes = self.db.recipes
		return list(recipes.find())

	def clear_db(self):
		""" Deletes all data in the database"""
		for col in self.db.collection_names():
			try:
				self.db.drop_collection(col)
			except Exception:
				pass
