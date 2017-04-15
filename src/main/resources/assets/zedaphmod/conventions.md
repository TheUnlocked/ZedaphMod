Conventions
============
As a general rule, if you aren't sure how something should be done, follow the example of the source code.
If you want to do something that is not listed here and which a variation of it has not been done in the mod, ask me for details.
## Java
### Naming
Classes and Enums use `UpperCamelCase`  
Interfaces use `IUpperCamelCase` (like classes but prefixed with 'I')  
Variables use `lowerCamelCase`  
Constants use `UPPERCASE` (therefore Enum constants should also use `UPPERCASE`)  
Name variables and functions things that convey the meaning of the variable or function.

### Loops
_The following rules are written in order of priority:_  
Use foreach loops (also known as enhanced for loops) instead of traditional for loops when using a traditional for loop would not provide any benefit over a foreach loop. The name of the variable should describe a singular element of the list, as that is what the variable will be.  
Always use `x`, `y`, and `z` when looping through positions.  
If necessary for clarity, use any other variable name for iterators. Otherwise,  
Always use `i`, `j`, `k`, `l`, etc. **in that order** when using other for loops.

### Classes versus Interfaces
Classes should be used for anything which needs a direct instance of it made (most things).  

### If/else/switch/case
Use `if`, `else`, and `else if` as necessary. Do not use expanded else if clauses such as:
```java
if (x == 1){
    // Do something
}
else{
    if(x > 1){
        // Do something else
    }
}
```
Could be rewritten (per convention) as:
```java
if (x == 1){
    // Do something
}
else if (x > 1){
    // Do something else
}
```
Note that this only applies if the clause could be rewritten with `else if` without losing any functionality.

`switch` and `case` may be used **only** when the argument is an Enum.
## General Naming
All new classes should be named according to their package.  
For example, an obsidian sword item that's placed in `unlocked.zedaphmod.item` should be called `ItemObsidianSword`.
If it were placed in `unlocked.zedaphmod.item.weapon` it would be called `WeaponObsidianSword` or `ItemWeaponObsidianSword`.
## Assets
All assets file names, as per Minecraft Forge standards, **must** be entirely lowercase.
Failure to comply with this requirement will result in any pull request or other submission method being immediately denied.

## Registries
### Naming
Registry names may not be changed after a build is pushed.  
All registry names must be `lowercase_with_underscores` delimiting words.

### Items
Items must be constants initialized into `public static final Item` variables within the `ModItems` class.
Object creation should be on one line, and setting names should be on a second line.  
All items that have been made should be registered by inserting the constant as an additional argument into the `registry.registerAll` method in `registerItems` in `RegistrationHandler`.  

### Blocks
Blocks must be constants initialized into `public static final` variables within the `ModBlocks` class. The variable must be of the type of the class which the block came from.  
Object creation should be on one line, and setting names should be on a second line.  
All items that have been made should be registered by inserting the constant as an additional argument into the `registry.registerAll` method in `registerBlocks` in `RegistrationHandler`.  
Each block must have its `ItemBlock` registered by adding a `new ItemBlock()` to `items` in `registerItemBlocks` in `RegistrationHandler` where the argument to the `ItemBlock` constructor is the constant representing the block.

### Entities
#### General
In `registerEntities` in `CommonProxy`, create a `ResourceLocation` called the name of the entity.
On the next line, use `EntityRegistry.registerModEntity` to register the entity.

#### Spawn Eggs
Use `EntityRegistry.registerEgg` on the line after the line in which your entity was registered to register an egg.

## Rendering
### Items
Register all item models with `ModelLoader.setCustomModelResourceLocation` in `registerRenderItems` in `ClientProxy`.  
Standard item models should child `zedaphmod:item/default_item`.

### Blocks
Blocks do not need to be specially rendered with additional code, however they need their own block model.  
All blocks also need an item model, which should be named the same thing as the block model file. The item model should contain the following:
```json
{
	"parent": "zedaphmod:block/block_name"
}
```
where `block_name` is replaced with the name (and possibly location) of the block model.

### Entities
If your entity has a special method of rendering (any rendering not automatically and exactly derived from a superclass), the custom renderer must be registered using `RenderingRegistry.registerEntityRenderingHandler` in `registerRenderEntities` in `ClientProxy`.

## Events
Register all non-static events in `registerEvents` in `CommonProxy` using `MinecraftForge.EVENT_BUS.register`. Static events should be registered automatically with the `@Mod.EventBusSubscriber` annotation.

## Miscellaneous
### Crafting Recipes
Register crafting recipes with their appropriate method in `registerRecipes` in `CommonProxy`. For shaped crafting, use a `.` to denote blank spaces when relevant.  
Any custom crafting solution should use an interface and registry to allow other modders to insert their own recipes using the custom crafting solution.

### Loot Tables
Loot tables should be stored as a `static final ResourceLocation` and then that should be referenced when needed.  
Entity loot tables should be contained within the associated entity class and should be called `LOOT_TABLE`.