import java.util.Random

class Teller {
    private val generator = Random()
    /*
     * When each teller is initialized, they are not available as they are
     * currently attending a customer.
     */
    var available: Boolean = false // if teller is available for next customer
    var totalTimeWithCustomers: Int = 0
        private set // prevents setting totalTimeWithCustomers externally
    var numberOfCustomersHelped: Int = 0
        private set // prevents setting numberOfCustomersHelped externally
    var remainingTimeWithCurrentCustomer: Int = generator.nextInt(4) + 2

    fun incrementTotalTimeWithCustomers() {
        totalTimeWithCustomers++
    }

    fun incrementNumberOfCustomersHelped() {
        numberOfCustomersHelped++
    }

    fun decrementRemainingTimeWithCurrentCustomer() {
        remainingTimeWithCurrentCustomer--
    }
}
