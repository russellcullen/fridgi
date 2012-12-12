## Takes input from stdout (barcode scanner) and inserts into database ##

import urllib, urllib2

def getJSONResponse(url):
	'''Returns dict representation of the json.'''
	try:
		return urllib2.urlopen(url).read()
	except:
		raise Exception("Error with api call")

class Api():
	''' Class for making the api calls '''
	url = "http://fridgi.herokuapp.com/fridge/fridgi/insert?upc="

	def insert(self, upc):
		return getJSONResponse(self.url + str(upc))


a = Api()
while (1):
	upc = raw_input("UPC: ")
	if (upc == "quit"):
		break
	print a.insert(int(upc))