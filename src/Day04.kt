fun main() {
    val testLines = readInput("Day04_test")

    val testResult1 = part1(testLines)
    println("test part 1: $testResult1")
    check(testResult1 == 2)

    val testResult2 = part2(testLines)
    println("test part 2: $testResult2")
    check(testResult2 == 4)


    val lines = readInput("Day04")

    println("part 1: ${part1(lines)}")
    println("part 2: ${part2(lines)}")
}

private fun part1(input: List<String>) = parseSections(input)
    .count { (first, second) -> first contains second }

private infix fun IntRange.contains(range: IntRange) =
    first in range && last in range || range.first in this && range.last in this

private fun part2(input: List<String>) = parseSections(input)
    .count { (first, second) -> first overlaps  second }

private infix fun IntRange.overlaps(range: IntRange) =
    first in range || last in range || range.first in this || range.last in this

private fun parseSections(input: List<String>) =
    input.map { line ->
        line.split(",").let { (first, second) -> first.toRange() to second.toRange() }
    }

private fun String.toRange() = split("-").let { (first, second) -> first.toInt()..second.toInt() }
