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
# return : array of Recipes that can be created
def get_current_recipes(fridge):

# input  : fridge ID or name
# return : array of Recipes that need some ingredients
def get_partial_recipes(fridge):

# input  : fridge ID or name
# return : array of current Ingredients
def get_current_ingredients(fridge):


# input  : ingredient name, fridge ID
def insert_ingredient(ingredient, fridge):
  # create fridge ingredient JSON object
  # insert into ingredients array of fridge
  # look in partial_recipes array of fridge, update ingredients_needed of each partial recipe
  # if partial_recipe's ingredients_needed is empty, place recipe in current_recipes, remove from partial_recipes


def insert_ingredient_all(ingredient):

def insert_recipe_all(recipe):






