---
title: Node - ToolSet
---

[ToolSet](../../index.html) / [com.zndevs.toolset.tools](../index.html) / [Node](./index.html)

# Node

`class Node<TValue>`

Object containing a value and children of the same type

### Parameters

`TValue` - Type of the value contained

### Constructors

| [&lt;init&gt;](-init-.html) | `Node()`<br>Creates a node with no value set`Node(contents: `[`TValue`](index.html#TValue)`)`<br>Creates a node with a value |

### Properties

| [value](value.html) | `var value: `[`TValue`](index.html#TValue)`?`<br>The value of this node |

### Functions

| [addChild](add-child.html) | `fun addChild(child: `[`Node`](./index.html)`<`[`TValue`](index.html#TValue)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds a node to this node's children`fun addChild(child: `[`TValue`](index.html#TValue)`): `[`Node`](./index.html)`<`[`TValue`](index.html#TValue)`>`<br>Wraps `child` in a Node instance and adds to this node's children |
| [createChild](create-child.html) | `fun createChild(): `[`Node`](./index.html)`<`[`TValue`](index.html#TValue)`>`<br>Creates a Node, adds it to the children, and returns it |
| [getChildren](get-children.html) | `fun getChildren(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Node`](./index.html)`<`[`TValue`](index.html#TValue)`>>`<br>Returns the list of children. Modifying this list will change the children of this node. |
| [removeChild](remove-child.html) | `fun removeChild(child: `[`Node`](./index.html)`<`[`TValue`](index.html#TValue)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes a node from our children. Does nothing if the node isn't already a child |

