fridgi
======

Objects
---------
## Ingredient
upc : long  
name : string
quantity : float  
unit : string  
shelf\_life : long  
insert\_time : long  
price : int  
calories : int  
count : int  
tags : string[]  

## Compact Ingredient
ingredient : Ingredient  
quantity : float  
unit : string 

## Recipe
name : string  
ingredients : CompactIngredient[]  
rating : float  
tags : string[]  
last\_used : long  
serving\_size : float 
