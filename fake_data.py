### Used to populate our DB ###

import api.database_api
import api.creator

count = 0

def d_i(api, c, upc, name, quantity, unit):
	ingredient = c.create_ingredient(upc = upc, name = name, quantity = quantity, unit = unit)
	api.add_ingredient(ingredient)

def r_i(api, name, quantity):
	ingredient = {}
	ingredient['name'] = name
	ingredient['ingredient'] = api.get_ingredient_info_from_name(name)['_id']
	ingredient ['quantity'] = quantity
	ingredient['unit'] = api.get_ingredient_info_from_name(name)['unit']
	return ingredient

def f_i(api, name, number):
	i = 0
	while i < number:
		api.insert_ingredient(name, 'fridgi')
		i += 1


def all_i(api, c, name, quantity, unit, number_to_insert_into_fridge):
	global count
	count += 1
	d_i(api, c, count, name, quantity, unit)
	f_i(api, name, number_to_insert_into_fridge)
	return r_i(api, name, quantity)



def insert_ingredients_into_fridge(api):
	# f_i(api, 'chicken breast')
	pass

def insert_some_recipes(api, c):
	
	api.add_fridge('fridgi')
	cup = 'cup'
	oz = 'ounce'
	na = ''
	tsp = 'teaspoon'
	tbsp = 'tablespoon'
	clove = 'clove'
	lb = 'pound'
	t = True
	f = False

	chicken = all_i(api, c, 'chicken breast', 5, cup, 2)
	onion = all_i(api, c, 'onion', 2, na, 1)
	penne = all_i(api, c, 'penne', 12, oz, 1)
	tomatoes = all_i(api, c, 'cherry tomato', 50, cup, 1)
	arugula = all_i(api, c, 'arugula leaves', 20, cup, 1)
	feta = all_i(api, c, 'feta cheese', 10, cup, 1)
	olive_oil = all_i(api, c, 'olive oil', 8, tsp, 2)
	garlic = all_i(api, c, 'garlic', 50, clove, 2)
	crushed_red_pepper = all_i(api, c, 'crushed red pepper', 20, tsp, 1)
	apple_juice = all_i(api, c, 'apple juice', 10, cup, 1)
	peach = all_i(api, c, 'peach', 1, na, 1)
	banana = all_i(api, c, 'banana', 1, na, 1)
	vanilla_yogurt = all_i(api, c, 'vanilla yogurt', 10, tsp, 1)
	ice_cubes = all_i(api, c, 'ice cube', 1, na, 1)
	honey = all_i(api, c, 'honey', 2, tsp, 1)
	flaxseed_oil = all_i(api, c, 'flaxseed oil', 2, tsp, 1)
	porcini_mushroom = all_i(api, c, 'porcini mushroom', 0.5, oz, 1)
	butter = all_i(api, c, 'butter', 6, tsp, 3)
	shiitake_mushroom = all_i(api, c, 'shiitake mushroom', 6, oz, 1)
	flour = all_i(api, c, 'flour', 2.5, tbsp, 1)
	whole_milk = all_i(api, c, 'whole milk', 2.5, cup, 10)
	parmesan_cheese = all_i(api, c, 'parmesan cheese', 0.75, cup, 1)
	chives = all_i(api, c, 'chives', 0.5, cup, 1)
	breadcrumbs = all_i(api, c, 'breadcrumbs', 0.5, cup, 1)
	baguette = all_i(api, c, 'baguette', 1, na, 1)
	egg = all_i(api, c, 'egg', 1, na, 24)
	lemon_juice = all_i(api, c, 'lemon juice', 2, tbsp, 10)
	anchovy_paste = all_i(api, c, 'anchovy_paste', 1, tbsp, 0)
	basil = all_i(api, c, 'basil leaves', 1, cup, 1)
	parsley = all_i(api, c, 'parsley', 0.5, cup, 1)
	romaine = all_i(api, c, 'romaine hearts', 1, lb, 1)
	parmigiano_reggiano = all_i(api, c, 'parmigiano-reggiano', 1, cup, 0)

	potato = all_i(api, c, 'potato', 2, lb, 1)
	salt = all_i(api, c, 'salt', 1.5, tsp, 20)
	white_pepper = all_i(api, c, 'white pepper', 0.25, tsp, 30)


	# chicken penne pasta
	ingredients = [tomatoes, olive_oil, garlic, crushed_red_pepper, chicken, penne, arugula, feta]
	step1 = "Preheat oven to 475F. Mix cherry tomatoes, oil, garlic, and crushed red pepper on rimmed baking sheet. Sprinkle with salt and pepper. Bake until tomatoes are soft and beginning to brown in spots, stirring occasionally, about 20 minutes. Transfer tomato mixture, including any juices, from sheet to large skillet. Add chicken to skillet and simmer until heated through, about 5 minutes."
	step2 = "Meanwhile, cook pasta in large pot of boiling salted water until just tender but still firm to bite, stirring occasionally. Ladle out 1/4 cup pasta cooking water and reserve. Drain pasta; return to pot."
	step3 = "Add tomato mixture, arugula, and reserved 1/4 cup pasta cooking water to pasta; toss over medium heat just until arugula begins to wilt, about 30 seconds. Season to taste with salt and pepper. Transfer pasta to bowl. Sprinkle with feta cheese and serve."
	steps = [step1, step2, step3]
	chicken_pasta = c.create_recipe(name = 'chicken penne pasta', ingredients = ingredients, instructions = steps, tags = ['delicious'], serving_size = 4)
	api.add_recipe(chicken_pasta)

	# sweet peach smoothie
	ingredients = [apple_juice, peach, banana, vanilla_yogurt, ice_cubes, honey, flaxseed_oil]
	step1 = "Combine the apple juice, peach, banana, yogurt, and ice in a blender and puree until smooth. Add the honey and flaxseed oil and puree briefly to incorporate."
	step2 = "Pour into glasses and serve right away."
	steps = [step1, step2]
	sweet_peach_smoothie = c.create_recipe(name = 'sweet peach smoothie', ingredients = ingredients, instructions = steps, tags = ['delicious'])
	api.add_recipe(sweet_peach_smoothie)

	# baked mushroom penne pasta
	ingredients = [porcini_mushroom, butter, shiitake_mushroom, flour, whole_milk, parmesan_cheese, chives, breadcrumbs]
	step1 = "Place porcini in small bowl. Add 2 cups hot water and let soften 20 minutes. Using slotted spoon, remove porcini. Reserve liquid. Coarsely chop porcini."
	step2 = "Melt 3 tablespoons butter in heavy large skillet over medium-high heat. Add onions and saute until soft, about 5 minutes. Add button and shiitake mushrooms and saute 5 minutes. Mix in porcini and reserved soaking liquid, leaving any sediment behind in bowl. Simmer over medium-high heat 10 minutes."
	step3 = "Melt remaining 3 tablespoons butter in heavy medium saucepan over medium heat. Add flour and stir constantly until golden, about 2 minutes. Gradually whisk in milk. Stir until sauce thickens and boils, about 3 minutes. Stir into mushroom mixture. Simmer 2 minutes, stirring occasionally. Stir in 1/2 cup Parmesan cheese and chives. Season sauce to taste with salt and pepper. Set aside. (Sauce can be prepared 1 day ahead. Cover and refrigerate. Rewarm over medium-low heat before continuing.)"
	step4 = "Preheat oven to 425F. Butter 13 x 9 x 2-inch glass baking dish. Cook pasta in large pot of boiling salted water until tender but still firm to bite. Drain well. Return to pot. Add mushroom sauce and toss well to coat. Transfer to prepared baking dish. Sprinkle breadcrumbs and remaining 1/4 cup Parmesan over. Bake casserole until heated through and light golden, about 25 minutes."
	steps = [step1, step2, step3, step4]
	baked_mushroom_penne_pasta = c.create_recipe(name = 'baked mushroom penne pasta', ingredients = ingredients, instructions = steps)
	api.add_recipe(baked_mushroom_penne_pasta)

	# basil caesar salad
	ingredients = [baguette, egg, lemon_juice, anchovy_paste, basil, parsley, romaine, parmigiano_reggiano]
	step1 = "Preheat oven to 375F with rack in middle."
	step2 = "Toss bread with 2 tablespoons oil and 1/4 teaspoon each of salt and pepper in a large 4-sided sheet pan, then spread out in 1 layer. Toast in oven, stirring halfway through, until golden, 12 to 15 minutes."
	step3 = " Meanwhile, with motor running, drop garlic into a food processor and finely chop. Add egg, lemon juice, anchovy paste, and 1/2 teaspoon pepper and pulse until combined. With motor running, add remaining 6 tablespoons oil in a slow stream, blending until emulsified. Add herbs and blend until dressing turns green and herbs are finely chopped."
	step4 = "Toss romaine with dressing, croutons, and half of cheese in a large bowl. Sprinkle with remaining cheese."
	steps = [step1, step2, step3, step4]
	basil_caesar_salad = c.create_recipe(name = 'basil caesar salad', ingredients = ingredients, instructions = steps)
	api.add_recipe(basil_caesar_salad)

	# light and silky mashed potatoes
	ingredients = [potato, salt, whole_milk, butter, white_pepper]
	s1 = "Peel potatoes and cut into 1-inch cubes. Transfer to a 3-quart heavy saucepan and add 5 cups cold water and 1 teaspoon salt, then bring to a boil. Reduce heat and simmer, partially covered, until potatoes are tender, 10 to 15 minutes."
	s2 = "Drain potatoes in a colander, then return to pan and cook over moderate heat, shaking pan occasionally, until dry, 1 to 2 minutes. Transfer potatoes to a bowl and keep hot, covered. Heat milk, butter, white pepper, and remaining 1/2 teaspoon salt in saucepan over moderate heat until butter is melted."
	s3 = "Force potatoes through food mill or ricer into hot milk mixture in pan and gently stir with a large heatproof rubber spatula just until combined."
	steps = [s1, s2, s3]
	light_and_silky_mashed_potatoes = c.create_recipe(name = 'light and silky mashed potatoes', ingredients = ingredients, instructions = steps)
	api.add_recipe(light_and_silky_mashed_potatoes)


def reset_db(apiInstance):
	c = api.creator.ObjectCreator()
	# Clear previous test data
	apiInstance.clear_db()
	# Put tests here
	# insert_some_ingredients(apiInstance, c)
	insert_some_recipes(apiInstance, c)
	insert_ingredients_into_fridge(apiInstance)


