## Updating the global ingredients and recipes database

# input  : ingredient object
def insert_ingredient_all(ingredient):
  # creates base ingredient and places in Ingredients collection

# input  : recipe object
def insert_recipe_all(recipe):
  # creates base recipe and places in Recipes collection


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
  # create fridge ingredient JSON object
  # insert into ingredients array of fridge
  # look in partial_recipes array of fridge, update ingredients_needed of each partial recipe
  # if partial_recipe's ingredients_needed is empty, place recipe in current_recipes, remove from partial_recipes

# input  : ingredient name, quantity, fridge name
def update_ingredient(ingredient, quantity, fridge):

# input  : recipe name as string
# return : boolean representing if fridge has necessary ingredients
def can_cook(recipe, fridge):
  # check if recipe in recipes collection
  # look through Recipe Ingredient objects, compare to Fridge Ingredients
  # if all Recipe Ingredients are accounted for, return true
  # else, return false

# input  : recipe name as string
# return : recipe to be displayed as JSON object
def display_recipe(recipe):
  # get recipe object
  # convert into JSON










