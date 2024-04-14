package ingsis.interpreter.operatorScanner

import com.sun.jdi.InvalidTypeException
import ingsis.components.Position
import ingsis.components.Token
import ingsis.components.TokenType
import ingsis.components.statement.SingleValue
import ingsis.components.statement.Value
import ingsis.utils.Result
import ingsis.utils.checkIfVariableDefined

class ScanDivOperator : ScanOperatorType {
    override fun canHandle(operator: String): Boolean {
        return operator == "/"
    }

    override fun analyze(
        left: SingleValue,
        right: SingleValue,
        operatorPosition: Position,
        map: Map<String, Result>,
    ): Value {
        val firstValue = checkIfVariableDefined(left, map)
        val secondValue = checkIfVariableDefined(right, map)

        if (firstValue.getType().getValue() != TokenType.INTEGER ||
            secondValue.getType().getValue() != TokenType.INTEGER
        ) {
            throw InvalidTypeException(
                "Can't do division using no integer types in line " +
                    operatorPosition.startLine + " at position " +
                    operatorPosition.startColumn,
            )
        }
        val finalValue = firstValue.getValue()!!.toInt() / secondValue.getValue()!!.toInt()
        return SingleValue(token = Token(Position(), finalValue.toString(), TokenType.INTEGER))
    }
}