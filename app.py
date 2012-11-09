import tornado.ioloop
import tornado.web
import os
import fake_data
import database_api
from bson.json_util import dumps

class MainHandler(tornado.web.RequestHandler):
	def get(self):
		self.write("Hello Fridgi")

class IngredientHandler(tornado.web.RequestHandler):
	def get(self):
		self.write(dumps(api.get_all_ingredients()))

class RecipeHandler(tornado.web.RequestHandler):
	def get(self):
		self.write(dumps(api.get_all_recipes()))

class SearchRecipeHandler(tornado.web.RequestHandler):
	def get(self):
		self.write(dumps(api.search_recipes(str(self.get_argument('tags')))))

class FridgeHandler(tornado.web.RequestHandler):
	def get(self, slug):
		self.write(dumps(api.get_fridge(slug)))

application = tornado.web.Application([
	(r"/", MainHandler),
    (r"/ingredients", IngredientHandler),
    (r"/recipes", RecipeHandler),
    (r"/fridge/([^/]+)", FridgeHandler),
    (r"/search", SearchRecipeHandler)
])

if __name__ == "__main__":
	# Create fake testing db
	api = database_api.DatabaseApi()
	fake_data.reset_db(api)
	
	port = int(os.environ.get('PORT', 5000))
	application.listen(port)
	tornado.ioloop.IOLoop.instance().start()
