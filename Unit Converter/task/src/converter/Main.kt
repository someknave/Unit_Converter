package converter

import java.util.*
val input = Scanner(System.`in`)
enum class UnitLength(val unit: String = "", val singular: String = "",
                      val plural: String = "", val toMeter: Double = 0.0) {
    METER("m", "meter", "meters", 1.0),
    KILOMETER("km", "kilometer", "kilometers", 1000.0),
    CENTIMETER("cm", "centimeter", "centimeters", 0.01),
    MILLIMETER("mm", "millimeter", "millimeters", 0.001),
    MILE("mi", "mile", "miles", 1609.35),
    YARD("yd", "yard", "yards", 0.9144),
    FOOT("ft", "foot", "feet", 0.3048),
    INCH("in", "inch", "inches", 0.0254);

}
fun getUnitLength(string: String): UnitLength? {
    for (unit in UnitLength.values()) {
        if (string in listOf(unit.unit, unit.singular, unit.plural)){
            return unit
        }
    }
    return null
}


fun main() {
    convertToMeter()

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

fun convertToMeter() {
    print("Enter a number and a measure of length:")
    val amount = input.nextDouble()
    val unit = input.next().toLowerCase()
    val unitLength = getUnitLength(unit)
    if(unitLength == null) {
        println("Invalid Unit")
        return
    }
    val output = amount.toDouble() * unitLength.toMeter
    val meterForm = if (output == 1.0) {
        UnitLength.METER.singular
    } else UnitLength.METER.plural
    val unitForm = if (amount == 1.0) {
        unitLength.singular
    } else unitLength.plural
    println("$amount $unitForm is $output $meterForm")
}