package ingsis.parser.scan

import ingsis.components.Position
import ingsis.components.Token
import ingsis.components.TokenType
import ingsis.components.statement.*
import ingsis.parser.error.ParserError

class ScanDeclaration : ScanStatement {
    private val declarationTypes = listOf(TokenType.KEYWORD, TokenType.IDENTIFIER, TokenType.DECLARATION, TokenType.TYPE)

    override fun canHandle(tokens: List<Token>): Boolean {
        if (checkIfThereIsNoDelimiter(tokens)) {
            throw ParserError("error: ';' expected  " + tokens.last().getPosition(), tokens.last())
        }

        val tokWODelimiter = tokens.subList(0, tokens.size - 1)
        return canHandleWODelimiter(tokWODelimiter)
    }

    override fun canHandleWODelimiter(tokens: List<Token>): Boolean =
        if (declarationTypes == getTokenTypes(tokens)) {
            true
        } else {
            checkIfDeclarationTypesMissing(tokens)
        }

    override fun makeAST(tokens: List<Token>): Statement {
        val keyword: Keyword = getKeyword(tokens[0])
        val variable: Variable = getVariable(tokens[1])
        val declPosition: Position = getPosition(tokens[2])
        val type: Type = getType(tokens[3])
        return Declaration(keyword, variable, type, declPosition)
    }

    private fun checkIfDeclarationTypesMissing(tokens: List<Token>): Boolean {
        val declarationTypesPresent = declarationTypes.intersect(getTokenTypes(tokens).toSet())
        if (checkIfDeclarationTypesMissing(declarationTypesPresent, tokens)) {
            throw ParserError(
                "error: to declare a variable, it's expected to do it by 'let <name of the variable>: <type of the variable>'",
                tokens[0],
            )
        }

        return false
    }

    private fun checkIfDeclarationTypesMissing(
        declarationTypesPresent: Set<TokenType>,
        tokens: List<Token>,
    ) = declarationTypesPresent.size >= 2 && tokens.size == declarationTypesPresent.size

    private fun getTokenTypes(tokens: List<Token>): List<TokenType> = tokens.map { it.getType() }

    private fun getKeyword(token: Token): Keyword {
        val modifier: Modifier = Modifier.MUTABLE
        if (token.getValue() != "let") throw ParserError("error: keyword not found", token)
        return Keyword(modifier, token.getValue(), token.getPosition())
    }

    private fun getType(token: Token): Type {
        val tokenType =
            when (token.getValue()) {
                "string" -> TokenType.STRING
                "number" -> TokenType.INTEGER
                else -> throw ParserError("error: invalid token", token)
            }
        return Type(tokenType, token.getPosition())
    }

    private fun getPosition(token: Token): Position = token.getPosition()

    private fun getVariable(token: Token): Variable = Variable(token.getValue(), token.getPosition())

    private fun checkIfThereIsNoDelimiter(tokens: List<Token>) = tokens.last().getType() != TokenType.SEMICOLON
}
