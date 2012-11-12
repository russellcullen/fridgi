import punchfork

client = punchfork.Client('7f626435f88daa4f')

search_results = client.search('macaroni n cheese')

for recipe in search_results.recipes:
    print "Recipe: ", recipe.title
    print "Source URL:", recipe.source_url
    print "Punchfork URL:", recipe.pf_url
    print "Ingredients: ", recipe.source_ingred
    print recipe
    print "-------------------------------"
