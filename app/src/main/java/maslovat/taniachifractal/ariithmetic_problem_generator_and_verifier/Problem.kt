package maslovat.taniachifractal.ariithmetic_problem_generator_and_verifier

import java.lang.Math.abs
import kotlin.random.Random

/**A problem that has 2 operands, 1 operator and a correct/incorrect solution*/
public class Problem(firstNum_: Int, operator_: Int, secondNum_: Int, correct_: Boolean)
{
    var solution = 0.0

    var firstNum = 0
    var operator = PLUS_ID
    var secondNum = 0
    var correct = false
    /**Set solution if correct; Generate solution if incorrect*/
    init
    {
        firstNum = firstNum_
        secondNum = secondNum_
        operator = operator_
        correct = correct_

        solution = when(operator)
        {
            PLUS_ID -> (firstNum + secondNum).toDouble()
            MINUS_ID -> (firstNum - secondNum).toDouble()
            MULTIPLY_ID -> (firstNum * secondNum).toDouble()
            DIVIDE_ID -> (firstNum / secondNum).toDouble()
            else -> 0.0
        }
        if (!correct)
        {
            var deltaIncrement = (solution/100).toInt()
            deltaIncrement = abs(deltaIncrement)+1
            var wrongDelta = Random.nextInt(deltaIncrement*10, deltaIncrement*50)
            if (Random.nextBoolean()) wrongDelta *= -1
            solution += wrongDelta
        }
    }

    public fun getFirstNum(): String
    {
        return firstNum.toString()
    }
    public fun getSecondNum():String
    {
        return secondNum.toString()
    }
    public fun getOperator():String
    {
        return when(operator) {
            PLUS_ID -> "+"
            MINUS_ID -> "-"
            MULTIPLY_ID -> "*"
            DIVIDE_ID -> "/"
            else -> "!"
        }
    }
    public fun getSolution():String{
        return solution.toString()
    }
}