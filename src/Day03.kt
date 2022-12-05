fun main() {
    val testLines = readInput("Day03_test")

    val testResult1 = part1(testLines)
    println("test part 1: $testResult1")
    check(testResult1 == 157)

    val testResult2 = part2(testLines)
    println("test part 2: $testResult2")
    check(testResult2 == 70)


    val lines = readInput("Day03")

    println("part 1: ${part1(lines)}")
    println("part 2: ${part2(lines)}")
}

private fun part1(input: List<String>) = input.sumOf { line ->
    line.toCompartments().findCommon().toPriority()
}

private fun String.toCompartments() = substring(0, length / 2) to substring(length / 2)

private fun Pair<String, String>.findCommon() = first.first { char -> second.contains(char) }
private fun Triple<String, String, String>.findCommon() =
    first.first { char -> second.contains(char) && third.contains(char) }

private fun Char.toPriority() = (('a'..'z') + ('A'..'Z')).indexOf(this) + 1

private fun part2(input: List<String>) = input
    .windowed(size = 3, step = 3) { (first, second, third) ->
        Triple(first, second, third).findCommon().toPriority()
    }.sum()

private fun Iterable<Int>.sum() = sumOf { it }
