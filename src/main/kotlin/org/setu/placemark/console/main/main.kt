package org.setu.placemark.console.main

import com.github.ajalt.mordant.animation.progressAnimation
import com.github.ajalt.mordant.rendering.BorderType.Companion.SQUARE_DOUBLE_SECTION_SEPARATOR
import com.github.ajalt.mordant.rendering.TextAlign
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Spinner
import mu.KotlinLogging
import org.setu.placemark.console.models.PlacemarkModel


private val logger = KotlinLogging.logger {}
val t = Terminal()
val placemarks = ArrayList<PlacemarkModel>()
var placemark = PlacemarkModel()

fun inputBox() :Int {
    var option : Int
    var input: String? = null
    t.println(table { body {row("Enter an Integer :     \t")}})
    // Read up on print formatting to get input into the row() box
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty()) input.toInt()
    else
        -9
    return option
}
fun banner() {
    t.println(table {
        header { row("\n" +
                "████████╗███████╗░██████╗████████╗\n" +
                "╚══██╔══╝██╔════╝██╔════╝╚══██╔══╝\n" +
                "░░░██║░░░█████╗░░╚█████╗░░░░██║░░░\n" +
                "░░░██║░░░██╔══╝░░░╚═══██╗░░░██║░░░\n" +
                "░░░██║░░░███████╗██████╔╝░░░██║░░░\n" +
                "░░░╚═╝░░░╚══════╝╚═════╝░░░░╚═╝░░░\n") }
        body { row("Author: ^ ランナー▟ Martin McConnell \uD83D\uDE80 " )}
        body { row("20088021@mail.wit.ie")}
    })
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
            row("4. Hack The Planet!")
            row("5. Show the banner again")
            row("-1 Exit\n")
        }
    }
    t.println(table)
}

fun addPlacemark() {
    println("Add Placemark")
    println()
    var aPlacemark = PlacemarkModel()
    print("Enter a Title : ")
    aPlacemark.title = readLine()!!
    print("Enter a description : ")
    aPlacemark.description = readLine()!!
    if (aPlacemark.title.isNotEmpty() && aPlacemark.description.isNotEmpty()) {
        aPlacemark.id = placemarks.size.toLong()
        placemarks.add(aPlacemark.copy())
        logger.info("Placemark Added : [ $aPlacemark ]")
    }
    else
        logger.info("Placemark Not Added")
}

fun updatePlacemark() {
    println("Update Placemark")
    println()//  placemark.id++ Why does the id not increment
    listAllPlaceMarks()
    var searchId= getId()
    val aPlacemark = search(searchId)//  placemark.id++ Why does the id not increment

    if(aPlacemark != null) {
        print("Enter a new Title for [ $placemark.title ] : ")
        placemark.title = readLine()!!
        print("Enter a new Description for [ $placemark.description ] : ")
        placemark.description = readLine()!!
        if (placemark.title.isEmpty() || placemark.description.isEmpty()) {
            println("Placemanrk Not Updated..")
        } else {
            println("You updated [ $placemark.title ] for title and [ $placemark.description ] for description")
        }
    } else {
        println("Placemanrk Not Updated..")
    }
}


fun listAllPlaceMarks() {
    println("List All Placemarks")
    println()
    placemarks.forEach { logger.info("${it}") }
    println()
}

fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : PlacemarkModel? {
    var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == id }
    return foundPlacemark
}

fun searchPlacemark() {
    var searchId = getId()
    val aPlacemark = search(searchId)

    if(aPlacemark != null)
        println("Placemark Details [ $aPlacemark ]")
    else
        println("placemark Not Found...")
}

fun hackingThePlanet() {
    t.info.updateTerminalSize()
    val progress = t.progressAnimation {
        spinner(Spinner.Dots(TextColors.brightBlue))
        text("Hacking the planet")
        percentage()
        progressBar()
        completed()
        speed("B/s")
        timeRemaining()
    }
    progress.start()
    Thread.sleep(5000)
    progress.updateTotal(3_000_000_000)
    repeat(200) {
        progress.advance(15_000_000)
        Thread.sleep(100)
    }
    progress.stop()
}

fun main(args: Array<String>){
    logger.info {"Launching Placemark Console App Version 2.1"}
    banner()
    var input: Int
    do {
        menu()
        input = inputBox()
        when(input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> listAllPlaceMarks()
            4 -> hackingThePlanet()
            5 -> banner()
            -1 -> println("Exiting....")
            else -> println("Invalid option, reloading menu")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down App" }
}
