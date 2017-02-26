import java.util.Random

class Customer() {
    private val generator = Random()
    // Each customer is initialized wanting to spend 3-5 seconds with a teller
    val timeWithTeller: Int = generator.nextInt(3) + 2
}
