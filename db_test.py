### Various Tests ###

import api.database_api
import api.fridge_api
import fake_data

def insert_ingredients_into_fridge(api):
	# change
	api.add_fridge('fridgi')
	api.insert_ingredient('chicken breast', 'fridgi')
	api.insert_ingredient('penne', 'fridgi')
	api.insert_ingredient('cherry tomato', 'fridgi')
	api.insert_ingredient('arugula leaves', 'fridgi')
	api.insert_ingredient('feta cheese', 'fridgi')
	api.insert_ingredient('olive oil', 'fridgi')
	api.insert_ingredient('garlic', 'fridgi')
	api.insert_ingredient('crushed red pepper', 'fridgi')

def insert_some_ingredients(api, c):  

	cup = 'cup'
	oz = 'ounce'
	na = ''
	tsp = 'teaspoon'
	clove = 'clove'

	d_i(c, 1, 'chicken breast', 5, cup)
	d_i(c, 2, 'onion', na)
	d_i(c, 3, 'mushroom', 5, na)
	d_i(c, 4, 'penne', 12, oz)
	d_i(c, 5, 'cherry tomato', 50, cup)
	d_i(c, 6, 'arugula leaves', 20, cup)
	d_i(c, 7, 'feta cheese', 10, cup)
	d_i(c, 8, 'olive oil', 8, tsp)
	d_i(c, 7, 'garlic', 50, clove)
	d_i(c, 7, 'red pepper', 20, tsp)


	chicken_breast = c.create_ingredient(upc = 41244001514, name = 'chicken breast', quantity = 5, default_tags = ['meat', 'white'], unit = 'cup')
	onion = c.create_ingredient(upc = 62567, name = 'onion', quantity = 2, default_tags = ['vegetable'])
	mushroom = c.create_ingredient(upc = 17847, name = 'mushroom', quantity = 5)
	penne = c.create_ingredient(upc = 30034035303, name = 'penne', quantity = 12, unit = 'ounce')
	cherry_tomato = c.create_ingredient(upc = 38000596582, name = 'cherry tomato', quantity = 50, unit = 'ounce')
	arugula_leaves = c.create_ingredient(upc = 725211, name = 'arugula leaves', quantity = 20, unit ='cup')
	feta_cheese = c.create_ingredient(upc = 92776, name = 'feta cheese', quantity = 10, unit = 'cup')
	olive_oil = c.create_ingredient(upc = 703824, name = 'olive oil', quantity = 8, unit = 'tablespoon')
	garlic = c.create_ingredient(upc = 26842, name = 'garlic', quantity = 50, unit = 'clove')
	red_pepper = c.create_ingredient(upc = 55204, name = 'crushed red pepper', quantity = 20, unit = 'teaspoon')

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

def d_i(c, upc, name, quantity, unit):
	ingredient = c.create_ingredient(upc = upc, name = name, quantity = quantity, unit = unit)
	api.add_ingredient(ingredient)

def r_i(api, name, quantity):
	ingredient = {}
	ingredient['name'] = name
	ingredient['ingredient'] = api.get_ingredient_info_from_name(name)['_id']
	ingredient ['quantity'] = quantity
	ingredient['unit'] = api.get_ingredient_info_from_name(name)['unit']
	return ingredient

def f_i(api, fridge, name):
	api.insert_ingredient(name, fridge)

def insert_some_recipes(api, c):
	tomatoes = {'name' :'cherry tomato', 'ingredient' : api.get_ingredient_info_from_name('cherry tomato')['_id'],'quantity' : 36}
	tomatoes['unit'] = api.get_ingredient_info_from_name('cherry tomato')['unit']
	olive_oil = {'name' :'olive oil', 'ingredient' : api.get_ingredient_info_from_name('olive oil')['_id'],'quantity' : 2}
	olive_oil['unit'] = api.get_ingredient_info_from_name('olive oil')['unit']
	garlic = {'name' :'garlic', 'ingredient' : api.get_ingredient_info_from_name('garlic')['_id'],'quantity' : 5}
	garlic['unit'] = api.get_ingredient_info_from_name('garlic')['unit']
	pepper = {'name' :'crushed red pepper', 'ingredient' : api.get_ingredient_info_from_name('crushed red pepper')['_id'],'quantity' : 0.75}
	pepper['unit'] = api.get_ingredient_info_from_name('crushed red pepper')['unit']
	chicken = {'name' :'chicken breast', 'ingredient' : api.get_ingredient_info_from_name('chicken breast')['_id'],'quantity' : 2}
	chicken['unit'] = api.get_ingredient_info_from_name('chicken breast')['unit']
	penne = {'name' :'penne', 'ingredient' : api.get_ingredient_info_from_name('penne')['_id'],'quantity' : 8}
	penne['unit'] = api.get_ingredient_info_from_name('penne')['unit']
	arugula = {'name' :'arugula leaves', 'ingredient' : api.get_ingredient_info_from_name('arugula leaves')['_id'],'quantity' : 6}
	arugula['unit'] = api.get_ingredient_info_from_name('arugula leaves')['unit']
	feta = {'name' :'feta cheese', 'ingredient' : api.get_ingredient_info_from_name('feta cheese')['_id'],'quantity' : 0.5}
	feta['unit'] = api.get_ingredient_info_from_name('feta cheese')['unit']

	ingredients = [tomatoes, olive_oil, garlic, pepper, chicken, penne, arugula, feta]

	step1 = "Preheat oven to 475F. Mix cherry tomatoes, oil, garlic, and crushed red pepper on rimmed baking sheet. Sprinkle with salt and pepper. Bake until tomatoes are soft and beginning to brown in spots, stirring occasionally, about 20 minutes. Transfer tomato mixture, including any juices, from sheet to large skillet. Add chicken to skillet and simmer until heated through, about 5 minutes."
	step2 = "Meanwhile, cook pasta in large pot of boiling salted water until just tender but still firm to bite, stirring occasionally. Ladle out 1/4 cup pasta cooking water and reserve. Drain pasta; return to pot."
	step3 = "Add tomato mixture, arugula, and reserved 1/4 cup pasta cooking water to pasta; toss over medium heat just until arugula begins to wilt, about 30 seconds. Season to taste with salt and pepper. Transfer pasta to bowl. Sprinkle with feta cheese and serve."
	steps = [step1, step2, step3]

	chicken_pasta = c.create_recipe(name = 'chicken penne pasta', ingredients = ingredients, instructions = steps, tags = ['delicious'], serving_size = 4)
	api.add_recipe(chicken_pasta)

	step1 = "Combine the apple juice, peach, banana, yogurt, and ice in a blender and puree until smooth. Add the honey and flaxseed oil and puree briefly to incorporate."
	step2 = "Pour into glasses and serve right away."
	steps = [step1, step2]
	sweet_peach_smoothie = c.create_recipe(name = 'sweet peach smoothie', instructions = steps, tags = ['delicious'])
	api.add_recipe(sweet_peach_smoothie)

def print_ingredient(ingredient):
	name = ingredient['name']
	q = str(ingredient['quantity'])
	unit = ingredient['unit']
	if unit != '':
		unit += '(s)'
	print name + ': ' + q + ' ' + unit

def print_f_ingredient(f_ingredient):
	name = f_ingredient['name']
	q = str(f_ingredient['quantity'])
	print name + ': ' + q

def print_recipe(recipe):
	name = recipe['name']
	i = ''
	for s in recipe['ingredients']:
		i += str(s['quantity']) + ' ' + s['name'] + '\n'
	s2 = ''
	for a in recipe['instructions']:
		s2 += a + '\n\n'
	s3 = 'Tags : '
	for t in recipe['tags']:
		s3 += t + ' '

	print name + '\n' + i + '\n' + s2 + s3

def print_can_cook(a):
	if a:
		print 'Fridgi can cook this!'
	else:
		print 'Sorry, fridgi can\'t cook that right now'


def test():
	# Create fake testing db
	a = api.database_api.DatabaseApi('test')
	fridge = api.fridge_api.FridgeApi(a)

	fake_data.reset_db(a)

	all_ingredients = a.get_all_ingredients()  	
	all_recipes = a.get_all_recipes()
	fridge_ingredients = a.get_current_ingredients('fridgi')

	raw_input("Lookup Ingredient Database?")

	print '\nINGREDIENT DATABASE\n'
	for i in all_ingredients:
		print_ingredient(i)
	print '\n'

	raw_input("Lookup Fridge?")

	print '\nFRIDGE\n'
	for i in fridge_ingredients:
		print_f_ingredient(i)
	print '\n'

	recipe = raw_input("Enter Recipe: ")

	print_can_cook(fridge.can_cook(recipe, 'fridgi'))

	i = 0
	while i < 3:
		upc = long(raw_input("Enter UPC: "))
		print upc
		ingr = a.get_ingredient_info_from_upc(upc)
		if ingr is not None:
			a.insert_ingredient(ingr['name'], 'fridgi')
			print 'Inserted ' + ingr['name']
		i += 1

	f_ingredients = a.get_current_ingredients('fridgi')
	raw_input("Lookup Fridge again?")
	print '\nFRIDGE\n'
	for i in f_ingredients:
		print_f_ingredient(i)
	print '\n'

	recipe = raw_input("Enter Recipe: ")
	print_can_cook(fridge.can_cook(recipe, 'fridgi'))
	raw_input('Want the recipe?')
	print '\nRECIPE\n'
	print_recipe(all_recipes[0])


	return

def test2():
	# Create fake testing db
	a = api.database_api.DatabaseApi('test')
	fridge = api.fridge_api.FridgeApi(a)
	fake_data.reset_db(a)

	all_ingredients = a.get_all_ingredients()  	
	all_recipes = a.get_all_recipes()
	fridge_ingredients = a.get_current_ingredients('fridgi')

	tags = raw_input('Search : ')
	recipelist = a.search_recipes(tags)
	for i in recipelist:
		print_recipe(i)

	raw_input("Next : ")

	recipe_id = recipelist[0]['_id']
	new_recipe = fridge.suggest_by_current_recipe(recipe_id, 'fridgi')
	print_recipe(new_recipe)

	#tags = raw_input('Search fridge : ')
	#recipelist2 = fridge.search_fridge_recipes(tags, 'fridgi')
	#for i in recipelist2:
	#	print_recipe(i) 

def test3():

	# Create fake testing db
	a = api.database_api.DatabaseApi('test')
	fridge = api.fridge_api.FridgeApi(a)
	fake_data.reset_db(a)

	all_ingredients = a.get_all_ingredients()  	
	all_recipes = a.get_all_recipes()
	fridge_ingredients = a.get_current_ingredients('fridgi')

	print '\nINGREDIENT DATABASE\n'
	for i in fridge_ingredients:
		print_f_ingredient(i)
	print '\n'

	raw_input("Testing recent recipes : ")
	print fridge.can_cook("chicken penne pasta", 'fridgi')
	pasta = a.get_recipe_info("chicken penne pasta")
	use_chicken = fridge.use_recipe(pasta['_id'], 'fridgi')
	
	print '\nINGREDIENT DATABASE\n'
	fridge_ingredients = a.get_current_ingredients('fridgi')
	for i in fridge_ingredients:
		print_f_ingredient(i)
	print '\n'

	print 'LAST USED'
	recent_recipes = a.get_recent_recipes('fridgi')
	for recipe in recent_recipes:
		print(recipe['name'])

	raw_input('testing duplicates:')
	smoothie = a.get_recipe_info("sweet peach smoothie")
	use_chicken = fridge.use_recipe(smoothie['_id'], 'fridgi')
	use_chicken = fridge.use_recipe(smoothie['_id'], 'fridgi')
	print 'LAST USED'
	recent_recipes = a.get_recent_recipes('fridgi')
	for recipe in recent_recipes:
		print(recipe['name'])

def test4():
	# Create fake testing db
	a = api.database_api.DatabaseApi('test')
	fridge = api.fridge_api.FridgeApi(a)
	fake_data.reset_db(a)

	all_ingredients = a.get_all_ingredients()  	
	all_recipes = a.get_all_recipes()
	fridge_ingredients = a.get_current_ingredients('fridgi')

	recent_recipes = a.get_recent_recipes('fridgi')
	for recipe in recent_recipes:
		print(recipe['name'])

def test5():
	# Create fake testing db
	a = api.database_api.DatabaseApi('test')
	fridge = api.fridge_api.FridgeApi(a)

	fake_data.reset_db(a)

	all_ingredients = a.get_all_ingredients()  	
	all_recipes = a.get_all_recipes()
	fridge_ingredients = a.get_current_ingredients('fridgi')

	print '\nFRIDGE\n'
	for i in fridge_ingredients:
		print_f_ingredient(i)
	print '\n'

	recipe = raw_input("Enter Recipe: ")

	print_can_cook(fridge.can_cook(recipe, 'fridgi'))


if __name__ == "__main__":
	#print test3()
	#print test()
	#print test4()
	print test5()







