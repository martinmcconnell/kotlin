package org.setu.placemark.console.main

import com.github.ajalt.mordant.rendering.BorderType.Companion.SQUARE_DOUBLE_SECTION_SEPARATOR
import com.github.ajalt.mordant.rendering.TextAlign
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import mu.KotlinLogging
import org.setu.placemark.console.models.PlacemarkModel


private val logger = KotlinLogging.logger {}
val t = Terminal()
var placemark = PlacemarkModel()

fun inputBox() :Int {
    var option : Int
    var input: String? = null
    println("Enter an Integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty()) input.toInt()
    else
        -9
    return option
}

fun menu() {
    val table = table{
        borderType = SQUARE_DOUBLE_SECTION_SEPARATOR
        header {
            align = TextAlign.CENTER
            row("MAIN MENU")
        }
        body {
            row("1. Add Placemark")
            row("2. Update Placemark")
            row("3. List all placemarks")
            row("-1 Exit\n")
        }
    }
    t.println(table)
}

fun addPlacemark() {
    println("Add Placemark")
    println()
    print("Enter a Title : ")
    placemark.title = readLine()!!
    println("You entered $placemark.title for title")
}

fun updatePlacemark() {
    println("Update Placemark")
    println()
    print("Enter a new Title for [ $placemark.title ] : ")
    placemark.title = readLine()!!
    print("Enter a new Description for [ $placemark.description ] : ")
    placemark.description = readLine()!!
    println("You updated [ $placemark.title ] for title and [ $placemark.description ] for description")
}

fun listAllPlaceMarks() {
    println("Listing all placemarks")
}

fun main(args: Array<String>){
    logger.info {"Launching Placemark Console App Version 2.1"}
    t.println(table {
        body { row("Author: ^ ランナー▟ Martin McConnell \uD83D\uDE80 " )}
    })
    var input: Int
    do {
        menu()
        input = inputBox()
        when(input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> listAllPlaceMarks()
            -1 -> println("Exiting....")
            else -> println("Invalid option, reloading menu")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down App" }
}
