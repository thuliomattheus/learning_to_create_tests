package com.medium.tutorial.learning_to_create_tests.asserts

import io.mockk.InternalPlatformDsl.toArray
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.util.*
import java.util.stream.IntStream
import kotlin.math.sqrt

@ExtendWith(MockKExtension::class)
class SimpleAssertions {

    @Test
    fun `simple equals assertions`() {
        assertEquals(8, 15 - 7)
        assertEquals("teste", "tes${""}te")
        assertEquals(8.0, 16.0/2)
        assertEquals("43", "${40+3}")
        assertEquals(0..10, IntRange(0, 10))
    }

    @Test
    fun `simple not equals assertions`() {
        assertNotEquals(2, 45 / 9)
        assertNotEquals("3", 10 - 7)
        assertNotEquals("test", "tes t")
        assertNotEquals(0..10,10 downTo 0)
        assertNotEquals(49,49.0)
    }

    @Test
    fun `simple boolean assertions`() {
        assertTrue(true)
        assertTrue(sqrt(4.0) > 1.0)
        assertTrue(listOf("one", "two", "three").contains("two"))
        Math.random().times(100).let {
            assertTrue(it > it / 2)
        }

        assertFalse(!true)
        assertFalse(listOf("red", "blue", "yellow").contains("green"))
        assertFalse(Optional.of("not empty").isEmpty)
        assertFalse(BigDecimal.TEN < BigDecimal.ONE)
    }

    @Test
    fun `simple throw assertion`() {
        assertThrows<ArithmeticException> {
            4/0
        }
    }

    @Test
    fun `simple throw assertion with message`() {
        "Test with message exception assertion".let {
            assertThrows<IllegalArgumentException>(it) {
                throw IllegalArgumentException(it)
            }
        }
    }

    @Test
    fun `simple array assertions`() {
        val progression = IntProgression.fromClosedRange(0, 10, 1)
        val reversedProgression = 10 downTo 0

        assertArrayEquals(progression.toList().toIntArray(), reversedProgression.sorted().toIntArray())
        assertArrayEquals(progression.sortedDescending().toIntArray(), reversedProgression.toList().toIntArray())
        assertArrayEquals(arrayOf(true), arrayOf(!false))
        assertArrayEquals(arrayOf("a","b","c"), arrayOf("c","b","a").reversedArray())
        assertArrayEquals(arrayOf(10,30,50), arrayOf(20,40,60).map { it - 10 }.toTypedArray())
    }
}
