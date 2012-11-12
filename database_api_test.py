import unittest
import api.database_api
import api.fridge_api
import api.creator
import fake_data

class database_api_test(unittest.TestCase):

	def setUp(self):
		pass

	def test_get_ingredient_info_from_name(self):
		a = api.database_api.DatabaseApi('test')
		fridge = api.fridge_api.FridgeApi(a)
		fake_data.reset_db(a)

		nothing = a.get_ingredient_info_from_name('does not exist')
		self.assertTrue(nothing == None)

		garlic = a.get_ingredient_info_from_name('garlic')
		self.assertTrue(garlic['name'] == 'garlic')
		self.assertTrue(garlic['quantity'] == 50)
		self.assertTrue(garlic['unit'] == 'clove')

	def test_get_ingredient_info_from_upc(self):
		pass

	def test_get_recipe_info(self):
		pass

	def test_get_ingredients_by_recipe(self):
		pass

	def test_get_recipes_by_ingredient(self):
		pass

	def test_get_fridge(self):
		pass

	def test_get_current_ingredients(self):
		pass

	def test_insert_ingredient(self):
		pass

	def test_update_ingredient(self):
		pass

	def test_find_recipe_by_tag(self):
		pass

	def test_search_recipes(self):
		pass


if __name__ == '__main__':
	unittest.main()