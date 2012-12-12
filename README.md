fridgi
======

Description
-----------

Assumptions
-----------

Future
------

## Authors

* Norbert Chu
* Russell Cullen
* Jonathan Hsu
* Wilson Sa

How to Run Locally
------------------
Type into command line :

    $ ./run.sh

It's that simple!

JSON Objects and MongoDB Documents
----------------------------------
#### Fridge Ingredient
{  
  name : string  
  ingredient : ObjectID  
  quantity : float  
  unit : string  
  insert\_time : double  
  count : int  
  tags : string[]  
}  

#### Recipe Ingredient
{  
  name : string  
  ingredient : ObjectID    
  quantity : float  
  unit : string  
}  

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
grocery\_list : "Recipe Ingredient"[]  
recent\_recipes : Recipe[]  

#### Collection Names
Ingredients  
Recipes  
Fridges  






