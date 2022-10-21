package org.setu.placemark.console.views

import com.github.ajalt.mordant.animation.progressAnimation
import com.github.ajalt.mordant.rendering.BorderType
import com.github.ajalt.mordant.rendering.TextAlign
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Spinner
import org.setu.placemark.console.models.PlacemarkMemStore
import org.setu.placemark.console.models.PlacemarkModel


class PlacemarkView {
    val t = Terminal()

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
            borderType = BorderType.SQUARE_DOUBLE_SECTION_SEPARATOR
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

    fun addPlacemarkData(placemark : PlacemarkModel) : Boolean {
        print("Enter a Title : ")
        placemark.title = readLine()!!
        print("Enter a description : ")
        placemark.description = readLine()!!

        return placemark.title.isNotEmpty() && placemark.description.isNotEmpty()
    }

    fun updatePlacemarkData(placemark : PlacemarkModel) : Boolean {

        val tempTitle: String?
        val tempDescription: String?

        if(placemark != null) {
            print("Enter a new Title for [ $placemark.title ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ $placemark.description ] : ")
            tempDescription = readLine()!!
            if (tempTitle.isEmpty() && tempDescription.isEmpty()) {
                placemark.title = tempTitle
                placemark.description = tempDescription
                return true
            }
        }
        return false
    }

    fun listPlacemarks(placemarks: PlacemarkMemStore) {
        t.println(table {
            borderType = BorderType.SQUARE_DOUBLE_SECTION_SEPARATOR
            header {
                align = TextAlign.CENTER
                row("Placemarks")
            }
            body {
//                for (placemark in placemarks) {
//                    row("${placemark.id}. ${placemark.title}")
                placemarks.logAll()
                }
        })
    }

    fun showPlacemark(aPlacemark: PlacemarkModel) {
        if(aPlacemark != null)
            t.println(table {
                header { row("Placemark Details") }
                body { row("Title : ${aPlacemark.title}") }
                body { row("Description : ${aPlacemark.description}") }
            })
        else
            t.println(TextColors.red("Placemark Not Found..."))

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

}