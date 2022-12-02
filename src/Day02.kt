import RPS.*
import Tactic.*

fun main() {
    val testLines = readInput("Day02_test")

    val testResult1 = part1(testLines)
    println("test part 1: $testResult1")
    check(testResult1 == 15)

    val testResult2 = part2(testLines)
    println("test part 2: $testResult2")
    check(testResult2 == 12)


    val lines = readInput("Day02")

    println("part 1: ${part1(lines)}")
    println("part 2: ${part2(lines)}")
}

private fun part1(input: List<String>) = parseRockPaperScissors(input)
    .sumOf { match -> match.myScore() }

private fun part2(input: List<String>): Int = parseRockPaperScissors(input)
    .sumOf { match -> match.copy(myHand = match.tacticalMe()).myScore() }

private fun Match.tacticalMe() =
    when (tactic) {
        Win -> beat(opponent)
        Tie -> opponent
        Loss -> giveInAgainst(opponent)
    }
private fun beat(opponent: RPS) = when (opponent) {
    Rock -> Paper
    Paper -> Scissors
    Scissors -> Rock
}

private fun giveInAgainst(opponent: RPS) = when (opponent) {
    Rock -> Scissors
    Paper -> Rock
    Scissors -> Paper
}

private fun parseRockPaperScissors(input: List<String>) =
    input.map { line ->
        line.split(" ").let { (first, second) -> Match(first, second) }
    }

private enum class RPS {
    Rock, Paper, Scissors;
    val score = ordinal + 1
}

private enum class Tactic {
    Loss, Tie, Win;
    val score = ordinal * 3
}

private fun Match.myScore(): Int = myHand.score + winningScore()

private data class Match(val opponent: RPS, val myHand: RPS) {
    constructor(opponent: String, me: String): this(opponent.toRPS(), me.toRPS())
    val tactic = myHand.toTactic()
}

private fun String.toRPS() = when (this) {
    "A", "X" -> Rock
    "B", "Y" -> Paper
    "C", "Z" -> Scissors
    else -> throw Exception("Do you even understand what we are playing?")
}

private fun RPS.toTactic() = when (this) {
    Rock -> Loss
    Paper -> Tie
    Scissors -> Win
}

private fun Match.winningScore(): Int =
    when (opponent) {
        myHand -> Tie
        Rock -> myHand.winsWith(Paper)
        Paper -> myHand.winsWith(Scissors)
        Scissors -> myHand.winsWith(Rock)
    }.score

private fun RPS.winsWith(value: RPS) = if (this == value) Win else Loss
