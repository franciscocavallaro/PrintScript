package ingsis.interpreter.interpretStatement

import ingsis.components.statement.If
import ingsis.components.statement.Statement
import ingsis.components.statement.StatementType
import ingsis.interpreter.PrintScriptInterpreter
import ingsis.interpreter.operatorScanner.ScanOperatorType
import ingsis.interpreter.valueAnalyzer.ValueAnalyzer
import ingsis.utils.OutputEmitter
import ingsis.utils.Result

class IfInterpreter(
    private val scanners: List<ScanOperatorType>,
    private val version: String,
    private val outputEmitter: OutputEmitter,
    private val input: Input,
) : StatementInterpreter {
    override fun canHandle(statement: Statement): Boolean = statement.getStatementType() == StatementType.IF

    override fun interpret(
        statement: Statement,
        previousState: HashMap<String, Result>,
    ): HashMap<String, Result> {
        val interpreter = PrintScriptInterpreter.createInterpreter(version, outputEmitter, input)
        val ifStatement = statement as If
        var newState = previousState
        val getElseStatement = ifStatement.getElseStatement()
        val comparison = ifStatement.getComparison()
        val ifBlock = ifStatement.getIfBlock()

        val comparisonResult = ValueAnalyzer(scanners).analyze(comparison, previousState)
        val comparisonValue = comparisonResult.getValue()
        if (comparisonValue == "true") {
            for (block in ifBlock) {
                newState = interpreter.interpret(block, previousState)
            }
        } else if (comparisonValue == "false") {
            for (block in getElseStatement) {
                newState = interpreter.interpret(block, previousState)
            }
        } else {
            throw Error("IfStatements can only have a boolean condition")
        }
        return newState
    }
}
