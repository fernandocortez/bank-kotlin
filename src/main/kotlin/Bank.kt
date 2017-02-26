import java.util.*

/*
 * PURPOSE
 * The purpose of this program is to create a simulation of a bank in which
 * customers will enter and add to a queue after a randomly generated amount
 * of time. The tellers will help as many customers as they are able to in the
 * allotted 2 minutes time.
 */
val RUNTIME = 120 // 120 sec = 2 min
val TELLERCOUNT = 5

fun main(args: Array<String>) {
    val generator = Random()
    val customerQueue = LinkedList<Customer>() // queue for arriving Customers
    val tellerQueue = LinkedList<Teller>() // queue for available Tellers
    val presentTellers = Array(TELLERCOUNT) { Teller() } // array of Tellers present

    var nextCustomerEta = generator.nextInt(4) + 2

    for (i in 1..RUNTIME) {
        // When the nextCustomerETA reaches zero, a new customer arrives
        // and is added to the queue. The timer is then set again to a
        // value between 2 and 6, inclusive. Else, the time is reduced by 1
        if (nextCustomerEta < 1) {
            customerQueue.add(Customer())
            nextCustomerEta = generator.nextInt(4) + 2
        } else {
            nextCustomerEta--
        }

        for (teller in presentTellers) {
            if (teller.available) {
                tellerQueue.add(teller)
            } else if (teller.remainingTimeWithCurrentCustomer < 1) {
                teller.available = true
                teller.incrementNumberOfCustomersHelped()
            } else {
                teller.decrementRemainingTimeWithCurrentCustomer()
                teller.incrementTotalTimeWithCustomers()
            }
        }

        while (tellerQueue.isNotEmpty() && customerQueue.isNotEmpty()) {
            val customer = customerQueue.removeFirst()
            val teller = tellerQueue.removeFirst()
            teller.available = false
            teller.remainingTimeWithCurrentCustomer = customer.timeWithTeller
        }
    }

    println("NAME \t\t Number of Customers Helped \t Total Time with Customers")
    for ((index, teller) in presentTellers.withIndex()) {
        println("Teller $index \t\t\t\t\t\t\t ${teller.numberOfCustomersHelped} \t\t\t\t\t\t\t\t ${teller.totalTimeWithCustomers}")
    }
}