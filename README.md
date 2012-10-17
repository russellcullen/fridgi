fridgi
======

JSON Object
-----------
"Fridge Ingredient"  
{  
  name : string  
  ingredient : ObjectID  
  quantity : float  
  insert\_time : long  
  count : int  
  tags : string[]  
}  

"Recipe Ingredient"  
{  
  name : string  
  ingredient : ObjectID    
  quantity : float  
}  

"Partial Recipe"  
{  
  name : string  
  recipe : ObjectID  
  ingredients\_needed : "Recipe Ingredient"[]  
} 

##Documents

#### Ingredient
upc : long  
name : string  
unit : string  
shelf\_life : long  
price : int  
calories : int  
default_tags : string[]  

#### Recipe
name : string  
ingredients : "Recipe Ingredient"[]  
rating : float  
tags : string[]  
last\_used : long  
serving\_size : float  

#### Fridge
name : string  
ingredients : "Fridge Ingredient"[]  
favorite\_recipes : Recipe[]  
current\_recipes : Recipe[]  
partial\_recipes : "Partial Recipes"[]  

Collection Names
----------------
Ingredients  
Recipes  
Fridges  






