## Updating the global ingredients and recipes database
import pymongo
import creator
import time
import os

class DatabaseApi:
	def __init__(self, db = 'heroku_app8911714'):
		self.creator = creator.ObjectCreator()
		self.connection = pymongo.Connection(os.environ.get('MONGOLAB_URI', None))
		self.db = self.connection[db]

	# DELETES ALL DATA IN DB
	# Really only for tesing purposes.
	def clear_db(self):
		for col in self.db.collection_names():
			try:
				self.db.drop_collection(col)
			except Exception:
				pass

	# input  : ingredient dict
	def add_ingredient(self, ingredient):
		""" Adds ingredient to Ingredients collection """
		ingredients = self.db.ingredients
		ingredients.insert(ingredient)
	  
	# input  : recipe dict
	def add_recipe(self, recipe):
		""" Adds recipe to Recipes collection """
		recipes = self.db.recipes
		recipes.insert(recipe)

	def add_fridge(self, name):
		fridges = self.db.fridges
		fridges.insert({'name': name, 'ingredients' : [], 'favorite_recipes' : []})

	# input  : name of ingredient (as string)
	# return : Ingredient Object
	def get_ingredient_info_from_name(self, ingredient):
		ingredients = self.db.ingredients
		return ingredients.find_one({'name' : ingredient})

	# input  : upc of ingredient (as num)
	# return : Ingredient Object
	def get_ingredient_info_from_upc(self, upc):
		ingredients = self.db.ingredients
		return ingredients.find_one({'upc' : upc})

	# input  : name of recipe (as string)
	# return : Recipe Object
	def get_recipe_info(self, recipe):
		recipes = self.db.recipes
		return recipes.find_one({'name' : recipe})

	# input  : name of recipe (as string)
	# return : array of required Ingredients
	def get_ingredients_by_recipe(self, recipe):
		r = get_recipe_info(recipe)
		return r['ingredients']

	# input  : name of ingredient (as string)
	# return : array of Recipes that use ingredient input
	def get_recipes_by_ingredients(self, ingredient):
		recipes = self.db.recipes
		return list(recipes.find({'ingredients.name' : ingredient}))

	# input  : fridge name
	# return : fridge dictionary
	def get_fridge(self, fridge):
		fridges = self.db.fridges
		fridge = fridges.find_one({'name' : fridge})
		return fridge

	# input  : fridge name
	# return : array of current Ingredients
	def get_current_ingredients(self, fridge):
		fridge = self.get_fridge(fridge)
		return fridge['ingredients']

	# input  : ingredient name, fridge name
	def insert_ingredient(self, ingredient, fridge):
		fridges = self.db.fridges
		f = fridges.find_one({'ingredients.name' : ingredient})
		if (f != None):
			for ins in f['ingredients']:
				if (ins['name'] == ingredient):
					self.update_ingredient(ingredient, ins['quantity']+1, fridge)
		else:
			i = self.get_ingredient_info_from_name(ingredient)
			fridge_ingredient = self.creator.create_fridge_ingredient(i['_id'], i['name'], i['quantity'], time.time(), 0, i['default_tags'])
			fridges.update({'name' : fridge}, {'$push' : {'ingredients' : fridge_ingredient}})

	# input  : ingredient name, quantity, fridge name
	def update_ingredient(self, ingredient, quantity, fridge):
		ingredients = self.get_current_ingredients(fridge)
		for i in ingredients:
			if (i['name'] == ingredient):
				i['quantity'] = quantity
		fridges = self.db.fridges
		fridges.update({'name' : fridge}, {'ingredients' : ingredients})

	def find_recipe_by_tag(self, tag_list):
		recipes = self.db.recipes
		return list(recipes.find({'tags' : {'$all' : tag_list}}))

	def search_recipes(self, query):
		tag_list = str.split(query)
		return self.find_recipe_by_tag(tag_list)

	#######     Should never need to use these.    #######
	#### Any searching can be done faster within mongo ###

	# return : entire Ingredients collection
	def get_all_ingredients(self):
		ingredients = self.db.ingredients
		return list(ingredients.find())

	# return : entire Recipes collection
	def get_all_recipes(self):
		recipes = self.db.recipes
		return list(recipes.find())