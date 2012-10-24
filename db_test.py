import database_api
import creator


def insert_some_ingredients(api, c):  
  chicken_breast = c.create_ingredient(upc = '01234', name = 'chicken breast', default_tags = ['meat', 'white'])
  onion = c.create_ingredient(upc = '62567', name = 'onion', default_tags = ['vegetable'])
  mushroom = c.create_ingredient(upc = '17847', name = 'mushroom')
  penne = c.create_ingredient(upc = '16882', name = 'penne', unit = 'ounce')
  cherry_tomato = c.create_ingredient(upc = '73546', name = 'cherry tomato', unit = 'ounce')
  arugula_leaves = c.create_ingredient(upc = '725211', name = 'arugula leaves', unit ='cup')
  feta_cheese = c.create_ingredient(upc = '92776', name = 'feta cheese', unit = 'cup')
  olive_oil = c.create_ingredient(upc = '703824', name = 'olive oil', unit = 'tablespoon')
  garlic = c.create_ingredient(upc = '26842', name = 'garlic', unit = 'clove')
  red_pepper = c.create_ingredient(upc = '55204', name = 'crushed red pepper', unit = 'teaspoon')

  api.add_ingredient(chicken_breast)
  api.add_ingredient(onion)
  api.add_ingredient(mushroom)
  api.add_ingredient(penne)
  api.add_ingredient(cherry_tomato)
  api.add_ingredient(arugula_leaves)
  api.add_ingredient(feta_cheese)
  api.add_ingredient(olive_oil)
  api.add_ingredient(garlic)
  api.add_ingredient(red_pepper)

def insert_some_recipes(api, c):
  tomatoes = {'name' :'cherry tomato', 'ingredient' : api.get_ingredient_info_from_name('cherry tomato')['_id'],'quantity' : 36}
  olive_oil = {}
  garlic = {}
  pepper = {}
  chicken = {}
  penne = {}
  arugula = {}
  feta = {}
  ingredients = [tomatoes, olive_oil, garlic, pepper, chicken, penne, arugula, feta]
  chicken_pasta = c.create_recipe(name = 'chicken penne pasta', ingredients = ingredients, serving_size = 4)
  api.add_recipe(chicken_pasta)

def test():
	# Create fake testing db
	api = database_api.DatabaseApi('test')
 	c = creator.ObjectCreator()
	# Clear previous test data
	api.clear_db()
	# Put tests here
  	insert_some_ingredients(api, c)

  	all_ingredients = api.get_all_ingredients()
  	print all_ingredients[4]

  	insert_some_recipes(api, c)
  	all_recipes = api.get_all_recipes()
  	print all_recipes
	return True

print test()







