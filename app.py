import tornado.ioloop
import tornado.web
import os
import fake_data
import api.database_api
import api.fridge_api
from bson.json_util import dumps
from bson.objectid import ObjectId

class MainHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("Hello Fridgi")

class IngredientHandler(tornado.web.RequestHandler):
	def get(self):
		self.write(dumps(apiObj.get_all_ingredients()))

class RecipeHandler(tornado.web.RequestHandler):
	def get(self):
		self.write(dumps(apiObj.get_all_recipes()))

class SearchRecipeHandler(tornado.web.RequestHandler):
	def get(self):
		self.write(dumps(apiObj.search_recipes(str(self.get_argument('tags')))))

class FridgeHandler(tornado.web.RequestHandler):
	def get(self, slug):
		self.write(dumps(apiObj.get_fridge(slug)))

class SuggestRecipeHandler(tornado.web.RequestHandler):
	def get(self, slug):
		self.write(dumps(fridgeObj.suggest_by_current_recipe(ObjectId(self.get_argument('recipe')), slug)))

application = tornado.web.Application([
	(r"/", MainHandler),
    (r"/ingredients", IngredientHandler),
    (r"/recipes", RecipeHandler),
    (r"/fridge/([^/]+)", FridgeHandler),
    (r"/search", SearchRecipeHandler),
    (r"/fridge/([^/]+)/suggest", SuggestRecipeHandler)
])

if __name__ == "__main__":
	# Create fake testing db
	apiObj = api.database_api.DatabaseApi()
	fridgeObj = api.fridge_api.FridgeApi(apiObj)
	fake_data.reset_db(apiObj)
	
	port = int(os.environ.get('PORT', 5000))
	application.listen(port)
	tornado.ioloop.IOLoop.instance().start()
