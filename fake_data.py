### Used to populate our DB ###

import database_api
import creator

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

def insert_some_recipes(api, c):
	tomatoes = {'name' :'cherry tomato', 'ingredient' : api.get_ingredient_info_from_name('cherry tomato')['_id'],'quantity' : 36}
	olive_oil = {'name' :'olive oil', 'ingredient' : api.get_ingredient_info_from_name('olive oil')['_id'],'quantity' : 2}
	garlic = {'name' :'garlic', 'ingredient' : api.get_ingredient_info_from_name('garlic')['_id'],'quantity' : 5}
	pepper = {'name' :'crushed red pepper', 'ingredient' : api.get_ingredient_info_from_name('crushed red pepper')['_id'],'quantity' : 0.75}
	chicken = {'name' :'chicken breast', 'ingredient' : api.get_ingredient_info_from_name('chicken breast')['_id'],'quantity' : 2}
	penne = {'name' :'penne', 'ingredient' : api.get_ingredient_info_from_name('penne')['_id'],'quantity' : 8}
	arugula = {'name' :'arugula leaves', 'ingredient' : api.get_ingredient_info_from_name('arugula leaves')['_id'],'quantity' : 6}
	feta = {'name' :'feta cheese', 'ingredient' : api.get_ingredient_info_from_name('feta cheese')['_id'],'quantity' : 0.5}

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

def reset_db(api):
	c = creator.ObjectCreator()
	# Clear previous test data
	api.clear_db()
	# Put tests here
	insert_some_ingredients(api, c)
	insert_some_recipes(api, c)
	insert_ingredients_into_fridge(api)


