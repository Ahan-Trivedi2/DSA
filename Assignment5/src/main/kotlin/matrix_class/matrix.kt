package matrix_class

/**
 * Represents a square matrix of size n x n with basic operations such as addition,
 * subtraction, multiplication, default Strassen multiplication and Strassen's optimized multiplication algorithm.
 *
 * @property n the dimension of the square matrix
 */
class Matrix(val n: Int) {
    // Internal 2d array to store matrix values
    private val data: Array<DoubleArray> = Array(n) { DoubleArray(n) }
    /**
     * Sets the value at the specified row and column in the matrix.
     *
     * @param i the row index
     * @param j the column index
     * @param value the value to set
     */
    fun set(i: Int, j: Int, value: Double) {
        data[i][j] = value
    }
    /**
     * Returns the value at the specified row and column in the matrix.
     *
     * @param i the row index
     * @param j the column index
     * @return the value at position (i, j)
     */
    fun get(i: Int, j: Int): Double {
        return data[i][j]
    }
    /**
     * Converts the matrix into a readable string representation.
     *
     * Each row is formatted with values rounded to two decimal places
     * and separated by tabs for easy printing.
     *
     * @return a string representation of the matrix
     */
    override fun toString(): String {
        val rowStrings = mutableListOf<String>()
        for (row in data) {
            // formattedRow is a list of strings where each string is a double of 2 decimal places
            val formattedRow = row.map { "%2.f".format(it) }.joinToString("\t")
            rowStrings.add(formattedRow)
        }
        return rowStrings.joinToString("\n")
    }
    /**
     * Splits the matrix into four equal-sized quadrants.
     *
     * The matrix size must be even for this operation.
     * Returns the submatrices in the order: [A11, A12, A21, A22].
     *
     * @return a list containing the four submatrices
     * @throws IllegalArgumentException if the matrix size is not even
     */
    fun split(): List<Matrix> {
        // ensure that we are only splitting even matrices
        require(n % 2 ==0) {"Matrix size must be even to split"}
        // get the value of the new matrix size (aka mid)
        val mid = n / 2
        // initialize 4 matrices of size mid
        val a11 = Matrix(mid)
        val a12 = Matrix(mid)
        val a21 = Matrix(mid)
        val a22 = Matrix(mid)
        // Logic to fill up these submatrices with
        // loop over all the rows
        for (i in 0 until mid) {
            // loop over all the columns
            for (j in 0 until mid) {
                // fill up all 4 of the matrices 'data' with correct values from the data
                // of the parent matrix
                a11.set(i, j, this.get(i,j))
                a12.set(i, j, this.get(i,j + mid))
                a21.set(i,j,this.get(i+mid, j))
                a22.set(i,j,this.get(i+mid,j+mid))
            }
        }
        return listOf(a11, a12, a21, a22)
    }
    /**
     * Combines four smaller submatrices into one larger matrix.
     *
     * The submatrices are placed in the following order:
     * [c11 c12]
     * [c21 c22]
     *
     * @param c11 the top-left submatrix
     * @param c12 the top-right submatrix
     * @param c21 the bottom-left submatrix
     * @param c22 the bottom-right submatrix
     * @return a new matrix formed by combining the four submatrices
     */
    fun combine(c11: Matrix, c12: Matrix, c21: Matrix, c22: Matrix): Matrix {
        // get the size the sub matrix
        val mid: Int = c11.n
        // using this find the size that our combined matrix has to be
        val n: Int = mid * 2
        // instantiate a Matrix object of that size
        val result: Matrix = Matrix(n)
        // logic to actually fill all of this up
        for (i in 0 until mid) {
            for (j in 0 until mid) {
                result.set(i,j, c11.get(i,j))
                result.set(i,j+mid, c12.get(i,j))
                result.set(i+mid,j,c21.get(i,j))
                result.set(i+mid,j+mid,c22.get(i,j))
            }
        }
        return result
    }
    /**
     * Adds this matrix to another matrix of the same size.
     *
     * @param other the matrix to add
     * @return a new matrix representing the element-wise sum
     * @throws IllegalArgumentException if the matrix sizes do not match
     */
    operator fun plus(other: Matrix) : Matrix {
        // ensure the sizes are the same
        require(this.n == other.n) {"Matrix sizes must match for addition"}
        // instantiate a Matrix object to hold the matrix we are going to return
        val result = Matrix(n)
        // loop through every index of our result matrix, and fill it up
        for (i in 0 until n) {
            for (j in 0 until n) {
                result.set(i,j, this.get(i,j) + other.get(i,j))
            }
        }
        return result
    }
    /**
     * Subtracts another matrix from this matrix.
     *
     * @param other the matrix to subtract
     * @return a new matrix representing the element-wise difference
     * @throws IllegalArgumentException if the matrix sizes do not match
     */
    operator fun minus(other: Matrix) : Matrix {
        // ensure the matrix sizes are the same
        require(this.n == other.n) {"Matrix sizes must match for subtraction"}
        // instantiate a Matrix object to hold the matrix we are going to return
        val result = Matrix(n)
        // loop through every index of our result matrix, and fill it up
        for (i in 0 until n) {
            for (j in 0 until n) {
                result.set(i,j, this.get(i,j) - other.get(i,j))
            }
        }
        return result
    }
    /**
     * Performs standard matrix multiplication (O(n³)).
     *
     * @param other the matrix to multiply with
     * @return a new matrix representing the product of the two matrices
     * @throws IllegalArgumentException if the matrix sizes do not match
     */
    operator fun times(other: Matrix) : Matrix {
        // ensure the matrix sizes are the same
        require(this.n == other.n) {"Matrix sizes must match for multiplication"}
        // instantiate a Matrix object to hold the matrix we are going to return
        val result = Matrix(n)
        // loop over every index of our result matrix, and fill it up
        for (i in 0 until n){
            for (j in 0 until n){
                var sum: Double = 0.0
                for (k in 0 until n) {
                    sum += this.get(i,k) * other.get(k,j)
                }
                result.set(i,j, sum)
            }
        }
        return result
    }
    /**
     * Multiplies two matrices using Strassen’s algorithm.
     *
     * Strassen’s algorithm reduces the number of recursive multiplications
     * from 8 to 7, improving performance for large matrices.
     * The matrix size must be even for this method.
     *
     * @param other the matrix to multiply with
     * @return a new matrix representing the product
     * @throws IllegalArgumentException if sizes do not match or are not even
     */
    fun strassenMultiply(other: Matrix): Matrix {
        require(this.n == other.n) {"Matrix sizes must match for the Strassen multiplication"}
        // base case 1x1 matrix
        if (n==1) {
            val result = Matrix(1)
            result.set(0,0, this.get(0,0) * other.get(0,0))
            return result
        }
        require(n % 2 == 0) {"Matrix size must be even for Strassen multiplication"}
        // Split both matrices into four submatrices
        val (A11, A12, A21, A22) = this.split()
        val (B11, B12, B21, B22) = other.split()
        // Strassen's 7 products (following Wikipedia notation)
        val M1 = (A11 + A22).strassenMultiply(B11 + B22)       // M1 = (A11 + A22)(B11 + B22)
        val M2 = (A21 + A22).strassenMultiply(B11)             // M2 = (A21 + A22)B11
        val M3 = A11.strassenMultiply(B12 - B22)               // M3 = A11(B12 - B22)
        val M4 = A22.strassenMultiply(B21 - B11)               // M4 = A22(B21 - B11)
        val M5 = (A11 + A12).strassenMultiply(B22)             // M5 = (A11 + A12)B22
        val M6 = (A21 - A11).strassenMultiply(B11 + B12)       // M6 = (A21 - A11)(B11 + B12)
        val M7 = (A12 - A22).strassenMultiply(B21 + B22)       // M7 = (A12 - A22)(B21 + B22)
        // Final 4 quadrants
        val C11 = M1 + M4 - M5 + M7
        val C12 = M3 + M5
        val C21 = M2 + M4
        val C22 = M1 - M2 + M3 + M6
        // Combine four submatrices into the final matrix w/ combine
        return combine(C11, C12, C21, C22)
    }
    /**
     * Multiplies two matrices using a hybrid approach that combines
     * Strassen’s algorithm and standard matrix multiplication.
     *
     * Uses Strassen’s algorithm for large matrices and switches to
     * regular O(n³) multiplication when the matrix size is below
     * a given threshold for efficiency.
     *
     * @param other the matrix to multiply with
     * @param threshold the size below which standard multiplication is used (default: 64)
     * @return a new matrix representing the product
     * @throws IllegalArgumentException if matrix sizes do not match or are not even
     */
    fun hybridStrassenMultiply(other: Matrix, threshold: Int = 64): Matrix {
        require(this.n == other.n) { "Matrix sizes must match for Strassen multiplication" }
        // use normal multiplication for small matrices to prevent recursing down
        if (n <= threshold) {
            return this * other
        }
        // Base case: 1x1 multiply
        if (n == 1) {
            val result = Matrix(1)
            result.set(0, 0, this.get(0, 0) * other.get(0, 0))
            return result
        }
        require(n % 2 == 0) { "Matrix size must be even for Strassen multiplication (pad if needed)" }
        // Split both matrices into four submatrices
        val (A11, A12, A21, A22) = this.split()
        val (B11, B12, B21, B22) = other.split()
        // Strassen's 7 products (Wikipedia pattern)
        val M1 = (A11 + A22).hybridStrassenMultiply(B11 + B22, threshold)
        val M2 = (A21 + A22).hybridStrassenMultiply(B11, threshold)
        val M3 = A11.hybridStrassenMultiply(B12 - B22, threshold)
        val M4 = A22.hybridStrassenMultiply(B21 - B11, threshold)
        val M5 = (A11 + A12).hybridStrassenMultiply(B22, threshold)
        val M6 = (A21 - A11).hybridStrassenMultiply(B11 + B12, threshold)
        val M7 = (A12 - A22).hybridStrassenMultiply(B21 + B22, threshold)
        // Combine results
        val C11 = M1 + M4 - M5 + M7
        val C12 = M3 + M5
        val C21 = M2 + M4
        val C22 = M1 - M2 + M3 + M6
        // return these results
        return combine(C11, C12, C21, C22)
    }
}
