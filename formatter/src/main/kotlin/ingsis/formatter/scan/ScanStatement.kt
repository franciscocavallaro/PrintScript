package ingsis.formatter.scan

import ingsis.components.statement.Statement
import ingsis.formatter.utils.FormatterRule

interface ScanStatement {
    fun canHandle(statement: Statement): Boolean

    fun format(
        statement: Statement,
        ruleMap: Map<String, FormatterRule>,
    ): String
}
