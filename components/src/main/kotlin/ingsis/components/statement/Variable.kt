package ingsis.components.statement

import ingsis.components.Position

class Variable(private val name: String, private val position: Position) {
    fun getName(): String = name

    fun getPosition(): Position = position

    override fun toString(): String {
        return "Variable(name='$name')"
    }
}
