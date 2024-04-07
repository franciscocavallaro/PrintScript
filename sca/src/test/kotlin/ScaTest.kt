import components.Position
import components.Token
import components.TokenType
import components.ast.AST
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import scaRules.IntegerAndStringOperationsRule
import scaRules.IntegerOperationsRule
import scaRules.StringOperationsRule
import scaRules.TypeValueRule

class ScaTest {
    @Test
    fun testRuleMultiplyTwoIntegerVariables() {
        val sca = Sca(arrayListOf(IntegerOperationsRule()))
        val position = Position()
        val ast =
            AST(
                Token(position, "*", TokenType.OPERATOR),
                listOf(AST(Token(position, "5", TokenType.INTEGER)), AST(Token(position, "3", TokenType.INTEGER))),
            )
        assertTrue(sca.analyze(ast))
    }

    @Test
    fun testRuleSumTwoIntegerVariables() {
        val sca = Sca(arrayListOf(IntegerOperationsRule()))
        val position = Position()
        val ast =
            AST(
                Token(position, "+", TokenType.OPERATOR),
                listOf(AST(Token(position, "5", TokenType.INTEGER)), AST(Token(position, "3", TokenType.INTEGER))),
            )
        assertTrue(sca.analyze(ast))
    }

    @Test
    fun testRuleSubtractTwoIntegerVariables() {
        val sca = Sca(arrayListOf(IntegerOperationsRule()))
        val position = Position()
        val ast =
            AST(
                Token(position, "-", TokenType.OPERATOR),
                listOf(AST(Token(position, "5", TokenType.INTEGER)), AST(Token(position, "3", TokenType.INTEGER))),
            )
        assertTrue(sca.analyze(ast))
    }

    @Test
    fun testRuleDivideTwoIntegerVariables() {
        val sca = Sca(arrayListOf(IntegerOperationsRule()))
        val position = Position()
        val ast =
            AST(
                Token(position, "/", TokenType.OPERATOR),
                listOf(AST(Token(position, "5", TokenType.INTEGER)), AST(Token(position, "3", TokenType.INTEGER))),
            )
        assertTrue(sca.analyze(ast))
    }

    @Test
    fun testRuleMultiplyIntegerVariableAndStringVariable() {
        val sca = Sca(arrayListOf(IntegerOperationsRule()))
        val position = Position()
        val ast =
            AST(
                Token(position, "*", TokenType.OPERATOR),
                listOf(AST(Token(position, "5", TokenType.INTEGER)), AST(Token(position, "hello", TokenType.STRING))),
            )
        assertFalse(sca.analyze(ast))
    }

    @Test
    fun testRuleSumIntegerVariableAndStringVariable() {
        val sca = Sca(arrayListOf(IntegerOperationsRule()))
        val position = Position()
        val ast =
            AST(
                Token(position, "+", TokenType.OPERATOR),
                listOf(AST(Token(position, "5", TokenType.INTEGER)), AST(Token(position, "hello", TokenType.STRING))),
            )
        assertFalse(sca.analyze(ast))
    }

    @Test
    fun testRuleSubtractIntegerVariableAndStringVariable() {
        val sca = Sca(arrayListOf(IntegerOperationsRule()))
        val position = Position()
        val ast =
            AST(
                Token(position, "-", TokenType.OPERATOR),
                listOf(AST(Token(position, "5", TokenType.INTEGER)), AST(Token(position, "hello", TokenType.STRING))),
            )
        assertFalse(sca.analyze(ast))
    }

    @Test
    fun testRuleDivideIntegerVariableAndStringVariable() {
        val sca = Sca(arrayListOf(IntegerOperationsRule()))
        val position = Position()
        val ast =
            AST(
                Token(position, "/", TokenType.OPERATOR),
                listOf(AST(Token(position, "5", TokenType.INTEGER)), AST(Token(position, "hello", TokenType.STRING))),
            )
        assertFalse(sca.analyze(ast))
    }

    @Test
    fun testRuleDeclarationAndAssignationWithTwoIntegerVariables() {
        val sca = Sca(arrayListOf(IntegerOperationsRule()))
        val position = Position()
        val leftAst =
            AST(
                Token(position, "let", TokenType.DECLARATION),
                listOf(
                    AST(Token(position, "a", TokenType.IDENTIFIER)),
                    AST(Token(position, "number", TokenType.INTEGER)),
                ),
            )
        val rightAst =
            AST(
                Token(position, "*", TokenType.OPERATOR),
                listOf(AST(Token(position, "5", TokenType.INTEGER)), AST(Token(position, "3", TokenType.INTEGER))),
            )
        val ast = AST(Token(position, "=", TokenType.ASSIGNATION), listOf(leftAst, rightAst))
        assertTrue(sca.analyze(ast))
    }

    @Test
    fun testRuleDeclarationAndAssignationWithOneIntegerVariableAndAStringVariable() {
        val sca = Sca(arrayListOf(IntegerOperationsRule()))
        val position = Position()
        val leftAst =
            AST(
                Token(position, "let", TokenType.DECLARATION),
                listOf(
                    AST(Token(position, "a", TokenType.IDENTIFIER)),
                    AST(Token(position, "number", TokenType.INTEGER)),
                ),
            )
        val rightAst =
            AST(
                Token(position, "*", TokenType.OPERATOR),
                listOf(AST(Token(position, "hello", TokenType.STRING)), AST(Token(position, "3", TokenType.INTEGER))),
            )
        val ast = AST(Token(position, "=", TokenType.ASSIGNATION), listOf(leftAst, rightAst))
        assertFalse(sca.analyze(ast))
    }

    @Test
    fun testRuleMultiplyIntegerAndStringVariables() {
        val sca = Sca(arrayListOf(IntegerOperationsRule(), IntegerAndStringOperationsRule()))
        val position = Position()
        val ast =
            AST(
                Token(position, "*", TokenType.OPERATOR),
                listOf(AST(Token(position, "5", TokenType.INTEGER)), AST(Token(position, "hello", TokenType.STRING))),
            )
        assertFalse(sca.analyze(ast))
    }

    @Test
    fun testRuleSumIntegerAndStringVariables() {
        val sca = Sca(arrayListOf(IntegerOperationsRule(), IntegerAndStringOperationsRule()))
        val position = Position()
        val ast =
            AST(
                Token(position, "+", TokenType.OPERATOR),
                listOf(AST(Token(position, "5", TokenType.INTEGER)), AST(Token(position, "hello", TokenType.STRING))),
            )
        assertTrue(sca.analyze(ast))
    }

    @Test
    fun testRuleSubtractIntegerAndStringVariables() {
        val sca = Sca(arrayListOf(IntegerOperationsRule(), IntegerAndStringOperationsRule()))
        val position = Position()
        val ast =
            AST(
                Token(position, "-", TokenType.OPERATOR),
                listOf(AST(Token(position, "5", TokenType.INTEGER)), AST(Token(position, "hello", TokenType.STRING))),
            )
        assertFalse(sca.analyze(ast))
    }

    @Test
    fun testRuleDivideIntegerAndStringVariables() {
        val sca = Sca(arrayListOf(IntegerOperationsRule(), IntegerAndStringOperationsRule()))
        val position = Position()
        val ast =
            AST(
                Token(position, "/", TokenType.OPERATOR),
                listOf(AST(Token(position, "5", TokenType.INTEGER)), AST(Token(position, "hello", TokenType.STRING))),
            )
        assertFalse(sca.analyze(ast))
    }

    @Test
    fun testRuleTypeAndValueWithAnIntegerVariable() {
        val sca = Sca(arrayListOf(TypeValueRule()))
        val position = Position()
        val leftAst =
            AST(
                Token(position, "let", TokenType.DECLARATION),
                listOf(
                    AST(Token(position, "a", TokenType.IDENTIFIER)),
                    AST(Token(position, "number", TokenType.TYPE)),
                ),
            )
        val rightAst =
            AST(
                Token(position, "3", TokenType.INTEGER),
            )
        val ast = AST(Token(position, "=", TokenType.ASSIGNATION), listOf(leftAst, rightAst))
        assertTrue(sca.analyze(ast))
    }

    @Test
    fun testRuleAndTypeWithAStrongVariable() {
        val sca = Sca(arrayListOf(TypeValueRule()))
        val position = Position()
        val leftAst =
            AST(
                Token(position, "let", TokenType.DECLARATION),
                listOf(
                    AST(Token(position, "a", TokenType.IDENTIFIER)),
                    AST(Token(position, "string", TokenType.TYPE)),
                ),
            )
        val rightAst =
            AST(
                Token(position, "hello", TokenType.STRING),
            )
        val ast = AST(Token(position, "=", TokenType.ASSIGNATION), listOf(leftAst, rightAst))
        assertTrue(sca.analyze(ast))
    }

    @Test
    fun testRuleTypeAndValueWithTwoIntegers() {
        val sca = Sca(arrayListOf(TypeValueRule()))
        val position = Position()
        val leftAst =
            AST(
                Token(position, "let", TokenType.DECLARATION),
                listOf(
                    AST(Token(position, "a", TokenType.IDENTIFIER)),
                    AST(Token(position, "number", TokenType.TYPE)),
                ),
            )
        val rightAst =
            AST(
                Token(position, "+", TokenType.OPERATOR),
                listOf(AST(Token(position, "5", TokenType.INTEGER)), AST(Token(position, "3", TokenType.INTEGER))),
            )
        val ast = AST(Token(position, "=", TokenType.ASSIGNATION), listOf(leftAst, rightAst))
        assertTrue(sca.analyze(ast))
    }

    @Test
    fun testRuleTypeAndValueSumWithLeftIntegerAndRightStringVariables() {
        val sca = Sca(arrayListOf(TypeValueRule()))
        val position = Position()
        val leftAst =
            AST(
                Token(position, "let", TokenType.DECLARATION),
                listOf(
                    AST(Token(position, "a", TokenType.IDENTIFIER)),
                    AST(Token(position, "string", TokenType.TYPE)),
                ),
            )
        val rightAst =
            AST(
                Token(position, "+", TokenType.OPERATOR),
                listOf(AST(Token(position, "5", TokenType.INTEGER)), AST(Token(position, "hello", TokenType.STRING))),
            )
        val ast = AST(Token(position, "=", TokenType.ASSIGNATION), listOf(leftAst, rightAst))
        assertTrue(sca.analyze(ast))
    }

    @Test
    fun testRuleTypeAndValueSumWithRightIntegerAndLeftStringVariables() {
        val sca = Sca(arrayListOf(TypeValueRule()))
        val position = Position()
        val leftAst =
            AST(
                Token(position, "let", TokenType.DECLARATION),
                listOf(
                    AST(Token(position, "a", TokenType.IDENTIFIER)),
                    AST(Token(position, "string", TokenType.TYPE)),
                ),
            )
        val rightAst =
            AST(
                Token(position, "+", TokenType.OPERATOR),
                listOf(AST(Token(position, "hello", TokenType.STRING)), AST(Token(position, "3", TokenType.INTEGER))),
            )
        val ast = AST(Token(position, "=", TokenType.ASSIGNATION), listOf(leftAst, rightAst))
        assertTrue(sca.analyze(ast))
    }

    @Test
    fun testSumWithTwoStringVariables() {
        val sca = Sca(arrayListOf(StringOperationsRule()))
        val position = Position()
        val leftAst =
            AST(
                Token(position, "let", TokenType.DECLARATION),
                listOf(
                    AST(Token(position, "a", TokenType.IDENTIFIER)),
                    AST(Token(position, "string", TokenType.TYPE)),
                ),
            )
        val rightAst =
            AST(
                Token(position, "+", TokenType.OPERATOR),
                listOf(AST(Token(position, "hello", TokenType.STRING)), AST(Token(position, "world", TokenType.STRING))),
            )
        val ast = AST(Token(position, "=", TokenType.ASSIGNATION), listOf(leftAst, rightAst))
        assertTrue(sca.analyze(ast))
    }

    @Test
    fun testDivideTwoStringVariables() {
        val sca = Sca(arrayListOf(StringOperationsRule()))
        val position = Position()
        val leftAst =
            AST(
                Token(position, "let", TokenType.DECLARATION),
                listOf(
                    AST(Token(position, "a", TokenType.IDENTIFIER)),
                    AST(Token(position, "string", TokenType.TYPE)),
                ),
            )
        val rightAst =
            AST(
                Token(position, "/", TokenType.OPERATOR),
                listOf(AST(Token(position, "hello", TokenType.STRING)), AST(Token(position, "world", TokenType.STRING))),
            )
        val ast = AST(Token(position, "=", TokenType.ASSIGNATION), listOf(leftAst, rightAst))
        assertFalse(sca.analyze(ast))
    }

    @Test
    fun testMultiplyTwoStringVariables() {
        val sca = Sca(arrayListOf(StringOperationsRule()))
        val position = Position()
        val leftAst =
            AST(
                Token(position, "let", TokenType.DECLARATION),
                listOf(
                    AST(Token(position, "a", TokenType.IDENTIFIER)),
                    AST(Token(position, "string", TokenType.TYPE)),
                ),
            )
        val rightAst =
            AST(
                Token(position, "*", TokenType.OPERATOR),
                listOf(AST(Token(position, "hello", TokenType.STRING)), AST(Token(position, "world", TokenType.STRING))),
            )
        val ast = AST(Token(position, "=", TokenType.ASSIGNATION), listOf(leftAst, rightAst))
        assertFalse(sca.analyze(ast))
    }

    @Test
    fun testSubtractTwoStringVariables() {
        val sca = Sca(arrayListOf(StringOperationsRule()))
        val position = Position()
        val leftAst =
            AST(
                Token(position, "let", TokenType.DECLARATION),
                listOf(
                    AST(Token(position, "a", TokenType.IDENTIFIER)),
                    AST(Token(position, "string", TokenType.TYPE)),
                ),
            )
        val rightAst =
            AST(
                Token(position, "-", TokenType.OPERATOR),
                listOf(AST(Token(position, "hello", TokenType.STRING)), AST(Token(position, "world", TokenType.STRING))),
            )
        val ast = AST(Token(position, "=", TokenType.ASSIGNATION), listOf(leftAst, rightAst))
        assertFalse(sca.analyze(ast))
    }
}
