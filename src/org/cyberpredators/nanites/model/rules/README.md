## org.cyberpredators.nanites.model.rules

This package contains the classes used to describe the rules of an automaton.

A rule is made of a condition and a new state; to know when to try applying the rule is the responsibility of the RulesSet class.

All concrete conditions implement `Condition`, an interface defining the prototype of the `isVerified` method. This method is used by a rule when deciding if it can be applied, and takes the neighborhood of the cell as an argument.

RulesSet is a class used to store all the rules of an automaton, split according to the state.

Current conditions include :

- `NumberCondition`, when a cell must have an exact number of neighbors of a particular state
- `Low/High-Min/MaxCondition`, when a cell must have at least (or at most) a number of neighbors of a particular state. Low/High prefixes are used because of a small optimization, depending of the number.
- `Conjonction` and `Disjonction`, which allow to compose conditions, with the `and` and `or` operators.