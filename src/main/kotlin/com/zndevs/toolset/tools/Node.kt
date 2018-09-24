package com.zndevs.toolset.tools

import java.util.ArrayList

/**
 * Object containing a value and children of the same type
 * @param TValue Type of the value contained
 */
class Node<TValue> {
    private val children = ArrayList<Node<TValue>>()

    /**
     * The value of this node
     */
    var value: TValue? = null

    /**
     * Creates a node with no value set
     */
    constructor()

    /**
     * Creates a node with a value
     * @param contents The node's value
     */
    constructor(contents: TValue) {
        value = contents
    }

    /**
     * Adds a node to this node's children
     * @param child The child node
     */
    fun addChild(child: Node<TValue>) {
        children.add(child)
    }

    /**
     * Wraps `child` in a Node instance and adds to this node's children
     * @param child Value to create a child from
     * @return Node that was added
     */
    fun addChild(child: TValue): Node<TValue> {
        val node = Node(child)
        addChild(node)
        return node
    }

    /**
     * Creates a Node, adds it to the children, and returns it
     * @return Child node
     */
    fun createChild(): Node<TValue> {
        val child = Node<TValue>()
        addChild(child)
        return child
    }

    /**
     * Removes a node from our children. Does nothing if the node isn't already a child
     * @param child Child node to remove
     */
    fun removeChild(child: Node<TValue>) {
        children.remove(child)
    }

    /**
     * Returns the list of children. Modifying this list will change the children of this node.
     * @return List of children
     */
    fun getChildren(): List<Node<TValue>> {
        return children
    }
}
