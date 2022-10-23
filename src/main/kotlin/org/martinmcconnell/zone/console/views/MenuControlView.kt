package org.martinmcconnell.zone.console.views

import com.github.ajalt.mordant.animation.progressAnimation
import com.github.ajalt.mordant.rendering.BorderType
import com.github.ajalt.mordant.rendering.TextAlign
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.Borders
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Spinner
import org.martinmcconnell.zone.console.models.FileListJSONStore
import org.martinmcconnell.zone.console.models.FileListModel
import java.io.File


class MenuControlView {
    val t = Terminal()

    fun inputBox() :Int {
        var option : Int = 0
        val input = t.prompt("Enter a Menu Option Number: ")
        // Read up on print formatting to get input into the row() box
        if (input != null) {
            option = if (input.toIntOrNull() != null && !input.isEmpty()) input.toInt()
            else
                -9
        }
        return option
    }

    fun banner() {
        t.println(table {
            header { row("\n" +
                    "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
                    "▒     ▒   ▒▒▒▒   ▒▒▒▒▒   ▒   ▒▒▒▒▒▒   ▒▒▒▒\n" +
                    "▓▓▓▓▓▓   ▓▓▓   ▓▓   ▓▓▓   ▓▓   ▓▓  ▓▓▓   ▓\n" +
                    "▓▓▓▓   ▓▓▓▓   ▓▓▓▓   ▓▓   ▓▓   ▓         ▓\n" +
                    "▓▓▓   ▓▓▓▓▓▓   ▓▓   ▓▓▓   ▓▓   ▓  ▓▓▓▓▓▓▓▓\n" +
                    "█         ████   █████    ██   ███     ███\n" +
                    "██████████████████████████████████████████\n" +
                    "█ ▒█▀▄░▄▀▀░▄▀▀     ▀█▀░▀▄▀░▀█▀░░▒██▀░█▀▄ █\n" +
                    "█ ░█▀▄▒▄██▒▄██    ░▒█▒░█▒█░▒█▒▒░░█▄▄▒█▄▀ █\n" +
                    "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n")}
            body { row("Author: Martin McConnell \uD83D\uDE80 " )}
            body { row("20088021@mail.wit.ie")}
        })
    }

    fun menu() {
        val table = table{
            borderType = BorderType.SQUARE_DOUBLE_SECTION_SEPARATOR

            column(2){
            }
            header {
                align = TextAlign.CENTER
                row("MAIN MENU")
            }
            body {
                row("1. add","2. update","3. delete","4. list all")
                row("5. load !", "6. reader","7. main menu","8. banner")
                row("-1 Exit\n")
            }
        }
        t.println(table)
    }

    fun reader(file: FileListModel?) {
//        print("Select a file to read : ")
//        val input = readLine()!!
//        val file = File(input)
//        println("${file.path}")
        if (file != null) {
            t.println(File("${file.path}").readText())
        }
    }

    fun addFileData(file : FileListModel) : Boolean {
        print("Enter a Title : ")
        file.title = readLine()!!
        print("Enter a description : ")
        file.description = readLine()!!
        print("File location (requires exact path) : ")
        file.path = readLine()!!

        return file.title.isNotEmpty() && file.description.isNotEmpty()
    }

    fun updateFileListData(file : FileListModel) : Boolean {

        val tempTitle: String?
        val tempDescription: String?
        val tempPath: String?

        if(file != null) {
            print("Enter a new Title for [ $file.title ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ $file.description ] : ")
            tempDescription = readLine()!!
            print("Where is the updated location of the file : ")
            tempPath = readLine()!!
            if (tempTitle.isEmpty() && tempDescription.isEmpty() && tempPath.isEmpty()) {
                file.title = tempTitle
                file.description = tempDescription
                file.path = tempPath
                return true
            }
        }
        return false
    }

    fun listFiles(file: FileListJSONStore) {
        t.println(table {
            borderType = BorderType.SQUARE_DOUBLE_SECTION_SEPARATOR
            column(4) {
                cellBorders = Borders.ALL
            }
            header {
                align = TextAlign.CENTER
                row("ID", "Title", "Description", "Location")
            }
            body {
                for (file in file.fileList) {
                    row("${file.id}", "${file.title}"," ${file.description}", "${file.path}")
                }
            }
        })
    }

    fun showFileDetails(aFile: FileListModel) {
        if(aFile != null)
            t.println(table {
                header { row("File Details") }
                body { row("Title : ${aFile.title}") }
                body { row("Description : ${aFile.description}") }
                body { row("File path: ${aFile.path}") }
            })
        else
            t.println(TextColors.red("File Not Found..."))
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id of file : ")
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