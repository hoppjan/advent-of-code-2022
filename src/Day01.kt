fun main() {
    val testLines = readInput("Day01_test")

    val testResult1 = part1(testLines)
    check(testResult1 == 24_000)
    println("test part 1: $testResult1")

    val testResult2 = part2(testLines)
    check(testResult2 == 45_000)
    println("test part 2: $testResult2")


    val lines = readInput("Day01")

    println("part 1: ${part1(lines)}")
    println("part 2: ${part2(lines)}")
}

private fun part1(input: List<String>) = parseCalories(input)
    .maxOf { list ->
        list.sumOf { it.toInt() }
    }

private fun part2(input: List<String>) = parseCalories(input)
    .map { list -> list.sumOf { it.toInt() } }
    .sorted()
    .takeLast(3)
    .sumOf { it }

private fun parseCalories(input: List<String>) =
    input.joinToString(separator = "\n")
        .trim()
        .split("\n\n")
        .map { lines -> lines.split("\n").filter { it.isNotBlank() } }
