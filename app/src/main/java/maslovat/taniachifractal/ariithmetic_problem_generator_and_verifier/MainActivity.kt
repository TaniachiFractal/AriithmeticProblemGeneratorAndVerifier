package maslovat.taniachifractal.ariithmetic_problem_generator_and_verifier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import maslovat.taniachifractal.ariithmetic_problem_generator_and_verifier.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    /** Link to the main layout activity_main.xml; "fld" means "field"*/
    private lateinit var fld: ActivityMainBinding
    /**The problem that is shown*/
    private lateinit var problem: Problem
    /**Game Stats*/
    private lateinit var gameStats: GameStats
    /**Load*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // convert xml elements to widgets kotlin can work with
        fld = ActivityMainBinding.inflate(layoutInflater)
        setContentView(fld.root)

        fld.btStart.setOnClickListener{generateProblem();loadProblem()}

        gameStats = GameStats()
        loadStats()

    }
    /**Put stat data to text boxes*/
    fun loadStats()
    {
        fld.tbMaxTime.text = gameStats.maxTime.toString()
        fld.tbMinTime.text = gameStats.minTime.toString()
        fld.tbAvgTime.text = gameStats.getAverageTime().toString()
        fld.tbPercentSolvedCorrectly.text = gameStats.getPercentSolvedCorrect().toString()
        fld.tbProblemsSolvedCount.text = gameStats.getProblemSolvedCount().toString()
        fld.tbSolvedCorrectly.text = gameStats.solvedCorrect.toString()
        fld.tbSolvedIncorrectly.text = gameStats.solvedIncorrect.toString()
    }
    /**Init problem data*/
    fun generateProblem()
    {
        problem = Problem(Random.nextInt(10,99),
            Random.nextInt(PLUS_ID, DIVIDE_ID+1),
            Random.nextInt(10,99),
            Random.nextBoolean())
    }
    /**Put problem data to text boxes*/
    fun loadProblem()
    {
        fld.firstNum.text = problem.getFirstNum()
        fld.secondNum.text = problem.getSecondNum()
        fld.operator.text = problem.getOperator()
        fld.solutionNum.text = problem.getSolution()
    }
}