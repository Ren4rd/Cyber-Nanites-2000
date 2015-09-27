## org.cyberpredators.nanites.model.rules

This package contains the classes used to describe the rules of an automaton.

All rule classes inherits from `AbstractRule`, an abstract class defining the prototype of the `canBeApplied` method. This method is used when deciding what the next state of a cell will be, and takes the neighborhood of the cell as an argument.

RulesSet is a class used to store all the rules of an automaton, split according to the state.
