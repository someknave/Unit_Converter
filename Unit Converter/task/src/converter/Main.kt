package converter

import java.util.*
val input = Scanner(System.`in`)


enum class Units(val singular: String = "",
                      val plural: String = "", val unitType: String = "",
                 val toStandardMult: Double = 0.0, val toStandardAdd: Double = 0.0, val aliases: List<String> = emptyList()) {
    METER( "meter", "meters", "length", 1.0, 0.0, listOf("meter", "meters", "m")),
    KILOMETER( "kilometer", "kilometers", "length",1000.00,.0, listOf("kilometer", "kilometers", "km")),
    CENTIMETER( "centimeter", "centimeters", "length",0.010,.0, listOf("centimeter", "centimeters", "cm")),
    MILLIMETER("millimeter", "millimeters", "length",0.001,0.0, listOf("millimeter", "millimeters", "mm")),
    MILE( "mile", "miles", "length",1609.35,0.0, listOf("mile", "miles", "mi")),
    YARD( "yard", "yards", "length",0.9144,0.0, listOf("yard", "yards", "yd")),
    FOOT( "foot", "feet", "length",0.3048,0.0, listOf("foot", "feet", "ft")),
    INCH( "inch", "inches", "length",0.0254,0.0, listOf("inch", "inches", "in")),
    GRAM( "gram", "grams", "weight", 1.0,0.0, listOf("gram", "grams", "g")),
    KILOGRAM("kilogram", "kilograms", "weight", 1000.0,0.0, listOf("kilogram", "kilograms", "kg")),
    MILLIGRAM( "milligram", "milligrams", "weight", 0.001,0.0, listOf("milligram", "milligrams", "mg")),
    POUND( "pound", "pounds", "weight", 453.592,0.0, listOf("pound", "pounds", "lb")),
    OUNCE("ounce", "ounces", "weight", 28.3495,0.0, listOf("ounce", "ounces", "oz")),
    CELSIUS("degree Celsius", "degrees Celsius", "temperature", 1.0, 0.0, listOf("c", "dc", "celsius")),
    FAHRENHEIT("degree Fahrenheit", "degrees Fahrenheit", "temperature", 5.0/9.0, -32.0, listOf("f", "df", "fahrenheit")),
    KELVIN("kelvin", "kelvins", "temperature", 1.0, -273.15, listOf("kelvin", "kelvins", "k")),
    DERPDERP("derp");

}

fun getUnit(string: String): Units {
    for (unit in Units.values()) {
        if (string in unit.aliases) {
            return unit
        }
    }
    return Units.DERPDERP
}



fun main() {
    while (true) {
        println("Enter what you want to convert (or exit):")
        var list = input.nextLine().split(Regex("\\s+"))
        list = list.map { it.toLowerCase() }
        if (list[0] == "exit") break
        list = list.minus(listOf("", "degree", "degrees"))
        convertToUnit(list)
    }
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



fun convertToUnit(args: List<String>) {
    val input  = args[0].toDoubleOrNull()?: return println("Parse error")
    if (args.size != 4) return println("Parse error")
    val initUnit = getUnit(args[1])
    val targetUnit = getUnit(args[3])
    when {
        initUnit == Units.DERPDERP && targetUnit == Units.DERPDERP -> return println("Conversion from ??? to ??? is impossible.")
        initUnit == Units.DERPDERP -> return println("Conversion from ??? to ${targetUnit.plural} is impossible.")
        targetUnit == Units.DERPDERP -> return println("Conversion from ${initUnit.plural} to ??? is impossible.")
    }
    if (initUnit.unitType != targetUnit.unitType) {
        return println("Conversion from ${ initUnit.plural } to ${ targetUnit.plural } is impossible.")
    }
    val ans = when {
        initUnit.unitType == "temperature" -> {
            (input + initUnit.toStandardAdd) * initUnit.toStandardMult / targetUnit.toStandardMult - targetUnit.toStandardAdd
        }
        input < 0 -> return println("${initUnit.unitType.capitalize()} shouldn't be negative.")
        else -> input * initUnit.toStandardMult / targetUnit.toStandardMult
    }
    val initName = if (input == 1.0) { initUnit.singular
    } else { initUnit.plural}
    val targetName = if (ans == 1.0) { targetUnit.singular
    } else { targetUnit.plural}
    println("$input $initName is $ans $targetName")
}

