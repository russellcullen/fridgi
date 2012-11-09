fridgi
======

How to run app locally:

    $ ./run.sh

It's that simple!

JSON Object
-----------
"Fridge Ingredient"  
{  
  name : string  
  ingredient : ObjectID  
  quantity : float  
  insert\_time : double
  count : int  
  tags : string[]  
}  

"Recipe Ingredient"  
{  
  name : string  
  ingredient : ObjectID    
  quantity : float  
}  

##Documents

#### Ingredient
upc : long  
name : string  
quantity : float  
unit : string   
shelf\_life : long  
price : int  
calories : int  
default_tags : string[]  

#### Recipe
name : string  
ingredients : "Recipe Ingredient"[]  
instructions : string[]  
rating : float  
tags : string[]  
last\_used : long  
serving\_size : float  

#### Fridge
name : string  
ingredients : "Fridge Ingredient"[]  
favorite\_recipes : Recipe[]  

Collection Names
----------------
Ingredients  
Recipes  
Fridges  






