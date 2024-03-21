package maslovat.taniachifractal.ariithmetic_problem_generator_and_verifier
/**Saves all game stats*/
class GameStats
{
    public var solvedCorrect = 0
    public var solvedIncorrect = 0
    public var minTime = 0
    public var maxTime = 0

    public fun getAverageTime():Double
    {
        return (minTime+maxTime)/2.0
    }
    public fun getProblemSolvedCount():Int
    {
        return solvedCorrect+solvedIncorrect
    }
    public fun getPercentSolvedCorrect():Double
    {
        var output =  (solvedCorrect.toDouble()/getProblemSolvedCount())
        if (output.isNaN()) output = 0.0
        return output*100
    }
    /**Update time stats*/
    public fun newTime(newTime_: Int)
    {

        if (minTime==0 && maxTime==0)
        { maxTime=newTime_;minTime=newTime_;}

        if (newTime_>=maxTime) maxTime = newTime_
        else if (newTime_<=minTime) minTime = newTime_
    }
}