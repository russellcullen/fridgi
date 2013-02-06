# fridgi : The Smart Fridge

Fridgi is an android app that keeps track of the ingredients in your fridge to make cooking and shopping easier. Frigi can search recipes based on your current ingredients and you can even add ingredients to a grocery list to take with you on the go. 

# Run fridgi server

    $ python app.py

This will start the fridgi server which can then be pointed to by the android component. 

# API

### Objects

[Base Ingredient](#base-ingredient)  
* [Fridge Ingredient](#fridge-ingredient)  
* [Recipe Ingredient](#recipe-ingredient)  

[Recipe](#recipe)  
[Fridge](#fridge)

### Requests

All requests are GET requests

    /search?tags=<tag1>+<tag2>+....
    
Returns array of [Recipes](#recipe) that contain one or more given tags. Sorted by relevance.  

---
    /fridge/<name>
    
Returns the [Fridge](#fridge) with given name.  

---
    /fridge/<name>/search?tags=<tag1>+<tag2>
    
Identical to normal search, except these [Recipes](#recipe) includes a 'can_cook' boolean field based on the provided fridge name.

---
    /fridge/<name>/suggest?recipe=<RecipeID>
    
Returns the closest [Recipe](#recipe) to the one given that can be cooked with the ingredients in the fridge provided.

---
    /fridge/<name>/use?recipe=<RecipeID>
    
Can only be called on recipes that can be cooked with the given fridge. Uses the recipe provided. This subtracts recipe quantities from ingredients in the fridge given. 

---
    /fridge/<name>/add?ingredient=<RecipeID>&quantity=<qty>
    
Adds a [Recipe Ingredient](#recipe-ingredient) with the given base ingredient id and quantity to the grocery list of the provided fridge.  

---
    /fridge/<name>/remove?ingredient=<RecipeID>
    
Removes the [Recipe Ingredient](#recipe-ingredient) with the given id from the grocery list of the provided fridge.  

<a id="base-ingredient"></a>  
#### Base Ingredient
```JavaScript
{
  'upc' : long  
  'name' : string  
  'quantity' : float  
  'unit' : string   
  'shelf\_life' : long  
  'price' : int  
  'calories' : int 
  'default_tags' : string[]  
}
```

<a id="fridge-ingredient"></a>  
#### Fridge Ingredient
```JavaScript
{  
  'name' : string,  
  'ingredient' : ObjectID,   
  'quantity' : float,  
  'unit' : string,  
  'insert\_time' : double,  
  'count' : int,  
  'tags' : string[],  
}  
```

<a id="recipe-ingredient"></a>  
#### Recipe Ingredient
```JavaScript
{  
  'name' : string,  
  'ingredient' : ObjectID,    
  'quantity' : float,  
  'unit' : string,  
}
```

<a id="recipe"></a>  
#### Recipe
```JavaScript
{  
  'name' : string  
  'ingredients' : "Recipe Ingredient"[]  
  'instructions' : string[]  
  'rating' : float  
  'tags' : string[]  
  'last\_used' : long  
  'serving\_size' : float  
}
```

<a id="fridge"></a>  
#### Fridge
```JavaScript
{  
  'name' : string 
  'ingredients' : "Fridge Ingredient"[]   
  'grocery\_list' : "Recipe Ingredient"[]  
  'recent\_recipes' : Recipe[]  
}
``` 
