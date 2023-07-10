package pl.kozlowski.moviedb.commons

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoField

class DateProgression(
    override val start: LocalDate,
    override val endInclusive: LocalDate,
    val stepDays: Long = 1
) :
    Iterable<LocalDate>, ClosedRange<LocalDate> {

    override fun iterator(): Iterator<LocalDate> =
        DateIterator(start, endInclusive, stepDays)

    infix fun step(days: Long) = DateProgression(start, endInclusive, days)

}

class DateIterator(
    val startDate: LocalDate,
    val endDateInclusive: LocalDate,
    val stepDays: Long
) : Iterator<LocalDate> {
    private var currentDate = startDate

    override fun hasNext() = currentDate <= endDateInclusive

    override fun next(): LocalDate {

        val next = currentDate

        currentDate = currentDate.plusDays(stepDays)

        return next

    }
}

operator fun LocalDate.rangeTo(other: LocalDate) = DateProgression(this, other)

fun LocalDateTime.startOfDay() = this.with(
    ChronoField.NANO_OF_DAY,
    LocalTime.MIN.toNanoOfDay()
)
fun LocalDateTime.endOfDay() = this.with(
    ChronoField.NANO_OF_DAY,
    LocalTime.MAX.toNanoOfDay()
)