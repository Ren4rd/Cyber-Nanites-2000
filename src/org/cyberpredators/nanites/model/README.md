## org.cyberpredators.nanites.model

This package contains classes used to store and modify an instance of an automaton.

#### NanitesGrid

`NanitesGrid` is the class storing the state of an instance. Internally, only the state of a cell is stored, in a byte. The limits of the grid are represented by bytes of value `0x00`.

#### RulesApplier

`RulesApplier` is used to apply the rules of an automaton to an instance, and store the next state in another instance.

#### StateNameMap

`StateNameMap` links the byte representation of the states and their names as described by the user. The default state is represented by the byte `0x01`, as `0x00` is used for the limits of a grid.

#### Mod

A mod is simply a usable form of what the user has written. Thus, it contains a `RulesSet`, and a `StateNameMap`.

#### Game

`Game` is the class that links the other classes of the package : it contains all the objects needed to describe the evolution of an instance of an automaton.

It uses 2 `NanitesGrid`, one current and another one used as a buffer. Each time the game move on to its next state, both are swapped.
