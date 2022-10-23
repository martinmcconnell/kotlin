package org.martinmcconnell.zone.console.controllers

import mu.KotlinLogging
import org.martinmcconnell.zone.console.models.FileListJSONStore
import org.martinmcconnell.zone.console.models.FileListModel
import org.martinmcconnell.zone.console.views.MenuControlView

class MenuController {
    val files = FileListJSONStore()
    val menuControlView = MenuControlView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Zone Console App Version 2.2" }
        println("Kotlin App Version Die Hard 4.0")
    }

    fun menu() { return menuControlView.menu() }

    fun input() :Int {return menuControlView.inputBox()}

    fun load() {return menuControlView.hackingThePlanet()}

    fun reader() { return menuControlView.reader()}

    fun banner() {return menuControlView.banner()}

    fun start() {
        banner()
        var input: Int
        menu()
        do {
            input = input()
            when(input) {
                1 -> add()
                2 -> update()
                3 -> delete()
                4 -> list()
                5 -> load()
                6 -> reader()
                7 -> menu()
                8 -> banner()
                -1 -> println("Exiting....")
                else -> println("Invalid Option")
            }
            println()
    }   while (input !=-1)
        logger.info("Shutting Down Zone Console App Version 3.0")
    }

    fun add() {
        val aFile = FileListModel()
        if (menuControlView.addFileData(aFile))
            files.create(aFile)
        else
            logger.info("File Not Added.")
    }

    fun list() {
        menuControlView.listFiles(files)
    }

    fun update() {

        menuControlView.listFiles(files)
        var searchId = menuControlView.getId()
        val aFile = search(searchId)

        if(aFile != null) {
            if(menuControlView.updateFileListData(aFile)) {
                files.update(aFile)
                menuControlView.showFileDetails(aFile)
                logger.info("File Updated : [ $aFile ]")
            }
            else
                logger.info("File Not Updated")
        }
        else
            println("File Not Updated...")
    }

    fun delete() {
        menuControlView.listFiles(files)
        var searchId = menuControlView.getId()
        val aFile = search(searchId)

        if(aFile != null) {
            files.delete(aFile)
            println("File Deleted...")
            menuControlView.listFiles(files)
        }
        else
            println("File Not Deleted...")
    }

    fun search() {
        val aFile = search(menuControlView.getId())!!
        menuControlView.showFileDetails(aFile)
    }

    fun search(id: Long) : FileListModel? {
        val foundFile = files.findOne(id)
            return foundFile
    }



}
