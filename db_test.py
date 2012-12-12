### Various Tests ###

import api.database_api
import api.fridge_api
import fake_data

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
	print test()
	print test2()
	print test3()
	print test4()
	print test5()







