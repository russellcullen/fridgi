import tornado.ioloop
import tornado.web
import os
import db_test
import creator
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

application = tornado.web.Application([
	(r"/", MainHandler),
    (r"/ingredients", IngredientHandler),
    (r"/recipies", RecipeHandler)
])

if __name__ == "__main__":
	# Create fake testing db
	api = database_api.DatabaseApi('heroku_app8911714')
	c = creator.ObjectCreator()
	# Clear previous test data
	api.clear_db()

	db_test.insert_some_ingredients(api, c)
	db_test.insert_some_recipes(api, c)
	db_test.insert_ingredients_into_fridge(api)
	port = int(os.environ.get('PORT', 5000))
	application.listen(port)
	tornado.ioloop.IOLoop.instance().start()
