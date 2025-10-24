package matrix_class

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Unit tests for the [Matrix] class.
 *
 * Verifies correctness of:
 * - Standard matrix multiplication using [Matrix.times]
 * - Strassen’s recursive matrix multiplication using [Matrix.strassenMultiply]
 *
 * Tests include:
 * 1. Basic 2x2 multiplication
 * 2. Multiplication with the identity matrix
 * 3. Larger 4x4 matrix multiplication
 */
class MatrixTest {
    /**
     * Tests the standard matrix multiplication operator [Matrix.times].
     *
     * Verifies:
     * - Correctness of 2x2 multiplication results.
     * - Multiplication with the identity matrix returns the same matrix.
     * - Accuracy of larger 4x4 matrix multiplication.
     */
    @Test
    fun testTimes() {
        // ---- Test 1: 2x2 basic multiply ----
        val A1 = Matrix(2)
        val B1 = Matrix(2)
        A1.set(0,0,1.0); A1.set(0,1,2.0)
        A1.set(1,0,3.0); A1.set(1,1,4.0)
        B1.set(0,0,5.0); B1.set(0,1,6.0)
        B1.set(1,0,7.0); B1.set(1,1,8.0)
        val R1 = A1 * B1
        assertEquals(19.0, R1.get(0,0))
        assertEquals(22.0, R1.get(0,1))
        assertEquals(43.0, R1.get(1,0))
        assertEquals(50.0, R1.get(1,1))
        // ---- Test 2: 2x2 with identity ----
        val I = Matrix(2)
        I.set(0,0,1.0); I.set(1,1,1.0)
        val R2 = A1 * I
        assertEquals(A1.get(0,0), R2.get(0,0))
        assertEquals(A1.get(0,1), R2.get(0,1))
        assertEquals(A1.get(1,0), R2.get(1,0))
        assertEquals(A1.get(1,1), R2.get(1,1))
        // ---- Test 3: 4x4 multiply ----
        val A3 = Matrix(4)
        val B3 = Matrix(4)
        var count = 1.0
        for (i in 0 until 4)
            for (j in 0 until 4)
                A3.set(i, j, count++)
        count = 1.0
        for (i in 0 until 4)
            for (j in 0 until 4)
                B3.set(i, j, count++)
        val R3 = A3 * B3
        assertEquals(90.0, R3.get(0,0))
        assertEquals(100.0, R3.get(0,1))
        assertEquals(600.0, R3.get(3,3))
    }
    /**
     * Tests the [Matrix.strassenMultiply] function.
     *
     * Verifies:
     * - Correctness of Strassen’s algorithm for 2x2 matrices.
     * - Multiplication with the identity matrix produces the same result.
     * - Accuracy of larger 4x4 matrix multiplication using recursive Strassen steps.
     */
    @Test
    fun testStrassenMultiply() {
        // ---- Test 1: 2x2 basic multiply ----
        val A1 = Matrix(2)
        val B1 = Matrix(2)
        A1.set(0,0,1.0); A1.set(0,1,2.0)
        A1.set(1,0,3.0); A1.set(1,1,4.0)
        B1.set(0,0,5.0); B1.set(0,1,6.0)
        B1.set(1,0,7.0); B1.set(1,1,8.0)
        val R1 = A1.strassenMultiply(B1)
        assertEquals(19.0, R1.get(0,0))
        assertEquals(22.0, R1.get(0,1))
        assertEquals(43.0, R1.get(1,0))
        assertEquals(50.0, R1.get(1,1))
        // ---- Test 2: 2x2 identity ----
        val I = Matrix(2)
        I.set(0,0,1.0); I.set(1,1,1.0)
        val R2 = A1.strassenMultiply(I)
        assertEquals(A1.get(0,0), R2.get(0,0))
        assertEquals(A1.get(0,1), R2.get(0,1))
        assertEquals(A1.get(1,0), R2.get(1,0))
        assertEquals(A1.get(1,1), R2.get(1,1))
        // ---- Test 3: 4x4 multiply ----
        val A3 = Matrix(4)
        val B3 = Matrix(4)
        var count = 1.0
        for (i in 0 until 4)
            for (j in 0 until 4)
                A3.set(i, j, count++)
        count = 1.0
        for (i in 0 until 4)
            for (j in 0 until 4)
                B3.set(i, j, count++)
        val R3 = A3.strassenMultiply(B3)
        assertEquals(90.0, R3.get(0,0))
        assertEquals(100.0, R3.get(0,1))
        assertEquals(600.0, R3.get(3,3))
    }
}
