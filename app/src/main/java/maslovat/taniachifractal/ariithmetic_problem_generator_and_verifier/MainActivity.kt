package maslovat.taniachifractal.ariithmetic_problem_generator_and_verifier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import maslovat.taniachifractal.ariithmetic_problem_generator_and_verifier.databinding.ActivityMainBinding
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    /** Link to the main layout activity_main.xml; "fld" means "field"*/
    private lateinit var fld: ActivityMainBinding
    /**The problem that is shown*/
    private lateinit var problem: Problem
    /**Game Stats*/
    private lateinit var gameStats: GameStats

    /**Timer object*/
    private lateinit var timer: Timer

    /**The current time*/
    var currTime = 0

    /**Load*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // convert xml elements to widgets kotlin can work with
        fld = ActivityMainBinding.inflate(layoutInflater)
        setContentView(fld.root)

        fld.btStart.setOnClickListener{btStart_Click()}
        fld.btCorrect.setOnClickListener{checkButtons_click(true)}
        fld.btIncorrect.setOnClickListener{checkButtons_click(false)}

        gameStats = GameStats()
        loadStats()

        timer = Timer()
    }
    /**Save data*/
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // save problem
        outState.putInt("1st",problem.firstNum)
        outState.putInt("op",problem.operator)
        outState.putInt("2nd",problem.secondNum)
        outState.putBoolean("cor",problem.getWhetherCorrect())
        // save stats
        outState.putInt("crt",gameStats.solvedCorrect)
        outState.putInt("inc",gameStats.solvedIncorrect)
        outState.putInt("min",gameStats.minTime)
        outState.putInt("max",gameStats.maxTime)
    }
    /**Restore data*/
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // load problem
        problem = Problem(
            savedInstanceState.getInt("1st"),
            savedInstanceState.getInt("op"),
            savedInstanceState.getInt("2nd"),
            savedInstanceState.getBoolean("cor")
        )
        // load stats
        gameStats = GameStats()
        gameStats.solvedCorrect = savedInstanceState.getInt("crt")
        gameStats.solvedIncorrect = savedInstanceState.getInt("inc")
        gameStats.minTime = savedInstanceState.getInt("min")
        gameStats.maxTime = savedInstanceState.getInt("max")

        loadStats()
        loadProblem()
    }

    /**Put stat data to text boxes*/
    private fun loadStats()
    {
        fld.tbMaxTime.text = gameStats.maxTime.toString()
        fld.tbMinTime.text = gameStats.minTime.toString()
        fld.tbAvgTime.text = gameStats.getAverageTime().toString()
        fld.tbPercentSolvedCorrectly.text = String.format("%.1f",gameStats.getPercentSolvedCorrect())
        fld.tbProblemsSolvedCount.text = gameStats.getProblemSolvedCount().toString()
        fld.tbSolvedCorrectly.text = gameStats.solvedCorrect.toString()
        fld.tbSolvedIncorrectly.text = gameStats.solvedIncorrect.toString()
    }
    /**Init problem data*/
    private fun generateProblem()
    {
        problem = Problem(Random.nextInt(10,99),
            Random.nextInt(PLUS_ID, DIVIDE_ID+1),
            Random.nextInt(10,99),
            Random.nextBoolean())
    }
    /**Put problem data to text boxes*/
    private fun loadProblem()
    {
        fld.firstNum.text = problem.getFirstNum()
        fld.secondNum.text = problem.getSecondNum()
        fld.operator.text = problem.getOperator()
        fld.solutionNum.text = problem.getSolution()
        fld.btStart.text = problem.getWhetherCorrect().toString()
    }
    /**Init game fields*/
    private fun initGameFields()
    {
        fld.btCorrect.isEnabled = true
        fld.btIncorrect.isEnabled = true
        fld.btStart.isEnabled = false
    }

    /** Increment curr time and put in into a text box; Every second */
    private fun timerTick()
    {
        currTime+=1
    }
    private fun startTimer()
    {
        timer = Timer()
        timer.schedule(object: TimerTask(){
            override fun run()
            {
                timerTick()
            }
        },0,1000)
    }
    private fun endTimer()
    {
        timer.cancel()
        gameStats.newTime(currTime-1); loadStats();
        currTime=0
    }

    /**Start game*/
    private fun btStart_Click()
    {
        generateProblem();loadProblem();initGameFields()
        startTimer()
    }
    /**Increment solved wrong/right counters and stop game*/
    private fun checkButtons_click(correct: Boolean)
    {
        endTimer()
        fld.btStart.isEnabled = true; fld.btStart.text = "СТАРТ"
        fld.btCorrect.isEnabled = false
        fld.btIncorrect.isEnabled = false

        if (correct==problem.getWhetherCorrect())
        {
            gameStats.solvedCorrect++
        }
        else
        {
            gameStats.solvedIncorrect++
        }
        loadStats()
    }
}