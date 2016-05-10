## org.cyberpredators.nanites.parser

This package contains classes used to create a mod from a yaml file.

#### YamlParser

This class contains a static method, which opens the file, read it, and uses the yamlbeans library to parse it.

#### YamlAdapter

As yamlbeans returns a very generic map, this class is used to offer a more suited interface to what we need here. It gives a set of methods that return typed values, or throw a `ModFactoryException` if the key was not found in the map.

#### YamlModFactory

This class contains static methods for creating a `Mod` from the `model` package, along with a method to instantiate the rules of an automaton (using `YamlConditionFactory`). A `ModFactoryException` is thrown when something goes wrong.

#### YamlConditionFactory

This class contains static methods used to create `Condition`s, and is used by `YamlModFactory`.