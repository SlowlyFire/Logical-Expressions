# Logical-Expressions
Logical Expressions, Nand and Nor Logic and Logical Simplification  

**In this project, we implement a system that can represent nested logical expressions that include variables, evaluate their values for specific variable assignments, convert them, and simplify the results.**  

**in order to compile and run the program, click here**.  

## Logical Expressions:  
**Our goal is to represent logical expressions such as:**    

~((T & (x | y))^x)  

Where T is a value of "True", the ~,|,&,^, symbols denotes the "not","or","and" and "xor" operators respectively, and x and y are variables.  

Note that this somewhat **complicated expression is composed of atomic expressions which are either binary or unary**, arranged in a tree structure. The expression itself is the root of the tree.  

**The unary expressions are:**    

Var("x") indicating that x is a variable.  
Not(x) indicating the negation of the value of x.  

**The binary expressions are:**    

Or(x,y) indicating the "or" of x, y.  
And(x,y) indicating the "and" of x, y.  
Xor(x,y) indicating the "xor" of x, y.  

We also have a Val(F) expression indicating the logical value "False".  

**Assuming we represent each of the atomic expressions as a Class of the same name that takes its arguments in the constructor, we can create the expression above in java using:**    

<img src="https://user-images.githubusercontent.com/83518959/193431728-57ffe935-4234-473d-9a83-890a93bcdc45.png" width="470" height="400" />   

**The tree is given below:**    

<img src="https://user-images.githubusercontent.com/83518959/193431738-fe390a72-aeb3-4fbd-9e9e-91c0f016ec65.png" width="400" height="450" />  

**Note that all the nodes in the tree are expressions (according to the Expression interface):**    

<img src="https://user-images.githubusercontent.com/83518959/193431752-f23764f1-44ef-4c7f-bafd-0239724f51f1.png" width="400" height="450" />  

**Similarly, we could represent (x & y) ^ T as:**    
![image](https://user-images.githubusercontent.com/83518959/193431787-de38309e-cb6c-4c48-bcd7-fa24176f2f94.png)  

**The string representations are as follows:**   

And(x,y) = (x & y)  
Or(x,y) = (x | y)  
Xor(x,y) = (x ^ y)  
Nand(x,y) = (x A y)  
Nor(x,y) = (x V y)  
Xnor(x,y) = (x # y)  
Not(x) = ~(x)  

## Class Hierarchy:  
<img src="https://user-images.githubusercontent.com/83518959/193432356-69f5772d-9b24-411e-98f0-f04de5bd0e3a.png" width="700" height="450" /> 

## Simplification:  

Logical expression can be quite messy and contain many "redundant" parts. For example:   
![image](https://user-images.githubusercontent.com/83518959/193432438-bdca9de0-2376-405c-ba68-1ea1ef119c16.png)  

This is correct, but can be really hard to read. We need to **"simplify"** the expression to make it more friendly to humans.  
**In order to support that method, we use the following simplifications:**    

x & 1 = x  

x & 0 = 0  

x & x = x  

x | 1 = 1  

x | 0 = x  

x | x = x  

x ^ 1 = ~(x)  

x ^ 0 = x  

x ^ x = 0  

x A 1 = ~(x)  

x A 0 = 1  

x A x = ~(x)  

x V 1 = 0  

x V 0 = ~(x)  

x V x = ~(x)  

x # x = 1  

an expression without variables evaluates to its result. ((T & T) | F) ^ T => F.  

Note that X here stands for **any expression**, not just a variable.  

