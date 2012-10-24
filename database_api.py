## Updating the global ingredients and recipes database
import pymongo
import creator
import time

class DatabaseApi:
	def __init__(self, db = 'db'):
		self.creator = creator.ObjectCreator()
		self.connection = pymongo.Connection()
		self.db = self.connection[db]

	# DELETES ALL DATA IN DB
	# Really only for tesing purposes.
	def clear_db(self):
		self.connection.drop_database(self.db)

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
	# return : array of current Ingredients
	def get_current_ingredients(self, fridge):
		fridges = self.db.fridges
		fridge = fridges.find_one({'name' : fridge})
		return fridge['ingredients']

	# input  : ingredient name, fridge name
	def insert_ingredient(self, ingredient, fridge):
		fridges = self.db.fridges
		f = fridges.fine_one({'ingredients.name' : ingredient})
		if (f != None):
			for ins in f['ingredients']:
				if (ins['name'] == ingredient):
					self.update_ingredient(ingredient, ins['quantity']+1, fridge)
		else:
			i = get_ingredient_info_from_name(ingredient)
			fridge_ingredient = self.creator.create_fridge_ingredient(i['_id'], i['name'], i['quantity'], time.time(), 0, i['deafult_tags'])
			fridges.update({'name' : fridge}, {'$push' : {'ingredients' : fridge_ingredient}})

	# input  : ingredient name, quantity, fridge name
	def update_ingredient(self, ingredient, quantity, fridge):
		pass

	# input  : recipe name as string
	# return : boolean representing if fridge has necessary ingredients
	def can_cook(self, recipe, fridge):
		# check if recipe in recipes collection
		# look through Recipe Ingredient objects, compare to Fridge Ingredients
		# if all Recipe Ingredients are accounted for, return true
		# else, return false
		pass


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
