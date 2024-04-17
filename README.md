# SOLID fizz-buzz

Fizz-buzz is a typical interview exercise for software developers.

For trivial that it is, it's quite interesting because it can be implemented in several ways, and it can
spark interesting discussion with the applicant.

In the domain of OOP, it's  interesting to think how a 10 lines of code solution with a raw for-loop can be
turned into something complying with SOLID principles, taking also in account testability of the various
parts.

## Fizz-buzz question statement

The fizz-buzz exercise is well-known, but let's recap it briefly.

Given a list of numbers from 1 to n:

- If a number is divisible by 3 print `fizz`
- If a number is divisible by 5 print `buzz`
- If a number is divisible by 3 and by 5 print `fizz buzz`
- Print the number itself otherwise

## What's SOLID?

SOLID it's an acronym for 5 valuable principles of OOP:

- **Single-responsibility principle**: there should never be more than one reason for a class to change;
  in other words, every class should have only one responsibility
- **Open–closed principle**: software entities ... should be open for extension, but closed for modification
- **Liskov substitution principle**: functions that use pointers or references to base classes must be able
  to use objects of derived classes without knowing it
- **Interface segregation principle**: clients should not be forced to depend upon interfaces that they do
  not use
- **Dependency inversion principle**: depend upon abstractions, not concretes

## Why Java?

Java has been used because it's a **strong** and **statically** typed object-oriented programming
language.

Those characteristics make the application of SOLID principles evident.

On top of that, Java is the most widespread language sporting such characteristics, so it seems a good
fit for a demonstrator.

Exploring SOLID principles with languages such as JavaScript and Python, still OOP languages but
with different implementations of the paradigm, might be subjects of dedicated demonstrations.

## Implementation plan

Let's start with a brief analysis of the request.

The problem statement talks about number divisibility: divisibility by 3 and 5 is requested, but a quick
taught might suggest that this is specific statement of a more general problem. Keeping the typical
interview scope, the interviewer might ask to add the case in which for a number divisible by 7 printing
`stomp` is requested. So, the concept of a **rule** being satisfied seems logical.

Then, the next obvious observation, is that the case of 3 and 5 printing `fizz buzz` it's a combination
of both 3 the case for 3 and the case for 5. So, a case where rules are *composable* seems logical.

Finally, there's a **fallback** case, where none of the previously stated rules is satisfied and the plain
number should be printed. This could be expressed with a rule as well.

This kind of approach with **rules**, **composite rules** and **fallback rules** seems rather flexible as:

- All of them are rules, so they can share a common interface
- Adding an additional case (such as divisible by 7 -> `stomp`) it's just a matter of adding a base
  rule to a composite
- Special cases (such as *only* for 5 -> `FIVE`) can be expressed as a chain of fallbacks

Thinking a little bit more to the implementation detail, the problem statement asks to turn condition about
numbers to **strings**. Therefore, the **rule** can be expressed in two ways:

- through a interface with a two methods approach, like `boolean match(int i)` and `String replace(int i)`
- through a single method interface using the `Optional` wrapper, like `Optional<String> apply(int i)`

Both are legit, with the latter being more concise and more versed in exploiting functional programming
with Java Streams. While this second option is a little bit more technical and somewhat more complex
to understand for less skilled developers, let's go ahead with it.

On the output side, the question is explicit about printing. Yet, printing is:

- a completely separated matter from the problem of checking rules against numbers
- it's the typical side effect that is hard to test
- might be replaced by some other kind of output

This lead to consider introducing a dedicated **result handler** interface a sensible choice. A base
implementation for it might just print to console while one dedicated to tests might keep results of the
rule iterations in memory.

To combine everything together, a **solver** should take a **rule** and a **result handler** to perform
the computation from 1 to n.

## Implementation comment

After the plan, the implementation is rather straightforward. While explicitly demonstrating SOLID
principles and allowing to express rule combination led to a relatively articulated codebase, class and
method names are expressive of their role.

A `Solver` interface was extracted just for the purpose of separating the packages; the actual `Solver`
implementation is named `StreamSolver` just because internally relies on Java Streams.

### About the single-responsibility principle

The single-responsibility principle is demonstrated by separating the three concerns of:

- Handling the general problem statement, of finding a solution for a set of conditions over a range of
  numbers and provide an output; this concern is absolved by the `StreamSolver` class, which is expected
  to change only if the general problem statement changes; further factoring of the `StreamSolver` class
  might be reasonable to further split its concerns but given the limited scope of the problem they were
  discarded
- Handling the output is left to implementations of the `ResultHandler` interface, each one handling it
  in a very specific way
- Verifying the conditions is left to `Rule` interface implementation, that operates on a very simple
  single method interface
  
### About the open–closed principle

The open–closed principle is demonstrated by using well defined interfaces that are focused,
of the right granularity and not concerned about the internals of the implementations.

In this way it's possible to **extend** classes to enrich the behavior (e.g. `FizzRule` extending
`ModuloRule`) but it's not possible to override methods to introduce hacky workarounds altering the
behavior in rather unpredictable ways.

As a note, someone might be advocating to not use class inheritance at all, just relying on interfaces
(and composition) but in that case we might have a very blurred line between the Open-closed principle
and the Liskov substitution principle.

### About the Liskov substitution principle

This is easily demonstrated by how the `Rule` interface is used in the `StreamSolver`, `CompositeRule`
and `FallbackRule` each accepting different implementations of `Rule` and using them coherently without
any change required on client-side. The rule composition and fallback features are actually entirely
based on this principle.

The same is true for the `ResultHandler` interface used by the `StreamSolver`, implemented by the
`ConsoleResultHandler` for the main code and by the `ListCollectorResultHandler` for the tests.

### About the interface segregation principle

Interface segregation principle is a little blurry in this example because the interfaces are already very
simple and there was no occasion to factor out a complex interface into several simpler ones.

Yet, if we look at the `ResultHandler` only a `void handle(String result)` is required. For the tests, a
concrete class `ListCollectorResultHandler` including public methods `String getResult(int index)` and
`int size()` was implemented: while no explicit Java interface was declared to handle this (it wasn't
necessary at all) it's clear that this addition doesn't impact in any way the actual interface of
`ConsoleResultHandler`, not being forced to implement them (as would have made no sense in there).  

### About the dependency inversion principle

The dependency inversion principle is exploded as:

- High-level modules should not import anything from low-level modules.
  Both should depend on abstractions (e.g., interfaces).
- Abstractions should not depend on details.
  Details (concrete implementations) should depend on abstractions.
  
This is clearly demonstrated by how the `StreamSolver` class, which can be seen as a higher-level element
of the example, doesn't import *anything* from the `rules` and `handlers` packages just relying on
interfaces.

Also, `Rule` interface implementations rely just on the `Rule` interface and not on each other.  

## How to run

The project is managed by Maven, mostly to manage JUnit dependency.

To build, execute:

```shell
mvn clean compile
```

To test, execute:

```shell
mvn test
```

To run

```shell
mvn exec:java
```

## Final comment

If you think that this is an overkill solution, well you're right. **Minimalism is a valuable principle**
in software development and no real-world problem as simple as fizz-buzz should be turned this complex
unless there's a real need to do so. In this case fizz-buzz was only used as a showcase of how certain
principles can be enforced through the constructs of OOP.

Just in case this was not enough for you, be sure to check out  
[FizzBuzzEnterpriseEdition](https://github.com/EnterpriseQualityCoding/FizzBuzzEnterpriseEdition) for a
laugh.
