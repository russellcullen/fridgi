## Updating the global ingredients and recipes database

# input  : ingredient object
def add_ingredient(ingredient):
  # Adds ingredient to Ingredients collection

# input  : recipe object
def add_recipe(recipe):
  # Adds recipe to Recipes collection


# input  : name of ingredient (as string)
# return : Ingredient Object
def get_ingredient_info(ingredient):

# input  : name of recipe (as string)
# return : Recipe Object
def get_recipe_info(recipe):

# input  : name of recipe (as string)
# return : array of required Ingredients
def get_ingredients_by_recipe(recipe):

# input  : name of ingredient (as string)
# return : array of Recipes that use ingredient input
def get_recipes_by_ingredients(ingredient):

# return : entire Ingredients collection
def get_all_ingredients():

# return : entire Recipes collection
def get_all_recipes():

# input  : fridge ID or name
# return : array of current Ingredients
def get_current_ingredients(fridge):


# input  : ingredient name, fridge name
def insert_ingredient(ingredient, fridge):

# input  : ingredient name, quantity, fridge name
def update_ingredient(ingredient, quantity, fridge):

# input  : recipe name as string
# return : boolean representing if fridge has necessary ingredients
def can_cook(recipe, fridge):
  # check if recipe in recipes collection
  # look through Recipe Ingredient objects, compare to Fridge Ingredients
  # if all Recipe Ingredients are accounted for, return true
  # else, return false
