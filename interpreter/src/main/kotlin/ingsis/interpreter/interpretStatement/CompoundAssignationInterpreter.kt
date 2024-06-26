package ingsis.interpreter.interpretStatement

import ingsis.components.statement.*
import ingsis.interpreter.operatorScanner.ScanOperatorType
import ingsis.interpreter.valueAnalyzer.ValueAnalyzer
import ingsis.utils.Result

class CompoundAssignationInterpreter(private val scanners: List<ScanOperatorType>) : StatementInterpreter {
    override fun canHandle(statement: Statement): Boolean = statement.getStatementType() == StatementType.COMPOUND_ASSIGNATION

    override fun interpret(
        statement: Statement,
        previousState: HashMap<String, Result>,
    ): HashMap<String, Result> {
        val compoundAssignation = statement as CompoundAssignation
        val variable = compoundAssignation.getDeclaration().getVariable()
        val variableModifier = compoundAssignation.getDeclaration().getKeyword().getModifier()
        val value = compoundAssignation.getValue()
        var result = ValueAnalyzer(scanners).analyze(value, previousState)
        result = result.updateModifier(variableModifier)
        if (checkIfNewValueTypeMatchesType(compoundAssignation.getDeclaration(), result)) {
            previousState[variable.getName()] = result
        } else {
            throw Exception("Type mismatch")
        }

        return previousState
    }

    private fun checkIfNewValueTypeMatchesType(
        declaration: Declaration,
        result: Result,
    ): Boolean {
        return declaration.getType().getValue() == result.getType().getValue()
    }
}
