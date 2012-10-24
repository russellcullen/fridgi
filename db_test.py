import database_api
import creator

def test():
	# Create fake testing db
	api = database_api.DatabaseApi('test')
	# Clear previous test data
	api.clear_db()
	# Put tests here
	return True

print test()