import urllib2
from bs4 import BeautifulSoup

# IGNORE EVERYTHING



# use punchfork api










url = urllib2.urlopen('http://www.epicurious.com/recipes/food/views/Alsace-Onion-Tart-109199')
data = url.read()

ingredients = data[data.rfind('<h2>Ingredients</h2>'):]
i = ingredients[:ingredients.find('<br />')]

soup = BeautifulSoup(i)

ingredients = soup.find_all('li')

ingredients.split('<li class=\"ingredient>\">')

print(ingredients)

#for i in ingredients:
#	i.replace('<li class=\"ingredient>\">', '')
#	i.replace('<li>', '')

#print(ingredients)

#print(url.read())




def scrape():
	pass