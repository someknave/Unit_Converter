package converter

import java.util.*
val input = Scanner(System.`in`)

fun main() {
    kmToMeters()

}

fun printConversions() {
    println("145 centimeters is 1.45 meters")
    println("2 miles is 3.2187 kilometers")
    println("5.5 inches is 139.7 millimeters")
    println("12 degrees Celsius is 53.6 degrees Fahrenheit")
    println("3 pounds is 1.360776 kilograms")
}
fun kmToMeters() {
    print("Enter a number of kilometers:")
    val km = input.nextInt()
    println("$km kilometers is ${km * 1000} meters")
}