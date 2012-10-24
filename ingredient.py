class ObjectCreator:

  def create_ingredient(upc = 0, name = '', unit = '', shelf_life = 0, price = 0, calories = 0, default_tags = []):
    """ Takes in values and returns an ingredient dictionary """
    ingredient = {}
    ingredient['upc'] = upc
    ingredient['name'] = name
    ingredient['unit'] = unit
    ingredient['shelf_life'] = shelf_life
    ingredient['price'] = price
    ingredient['calories'] = calories
    ingredient['default_tags'] = default_tags
    return ingredient

