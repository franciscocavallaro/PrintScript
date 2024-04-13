package ingsis.parser.error

import components.Position
import components.Token

class ParserError(message: String, private val token: Token) : Exception(message) {
    fun getTokenPosition(): Position {
        return token.getPosition()
    }

    override fun getLocalizedMessage(): String {
        return message!!
    }
}