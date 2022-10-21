package org.setu.placemark.console.controllers

import mu.KotlinLogging
import org.setu.placemark.console.models.PlacemarkMemStore
import org.setu.placemark.console.models.PlacemarkModel
import org.setu.placemark.console.views.PlacemarkView

class PlacemarkController {
    val placemarks = PlacemarkMemStore()
    val placemarkView = PlacemarkView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Placemark Console App Version 2.2" }
        println("Kotlin App Version 3.0")
    }

    fun menu() { return placemarkView.menu() }

    fun input() :Int {return placemarkView.inputBox()}
    fun load() {return placemarkView.hackingThePlanet()}

    fun banner() {return placemarkView.banner()}

    fun start() {
        var input: Int

        do {
            menu()
            input = input()
            when(input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> load()
                5 -> banner()
                -1 -> println("Exiting....")
                else -> println("Invalid Option")
            }
            println()
    }   while (input !=-1)
        logger.info("Shutting Down Placemark Console App Version 3.0")
    }

    fun add() {
        val aPlacemark = PlacemarkModel()
        if (placemarkView.addPlacemarkData(aPlacemark))
            placemarks.create(aPlacemark)
        else
            logger.info("Placemark Not Added.")
    }

    fun list() {
        placemarkView.listPlacemarks(placemarks)
    }

    fun update() {

        placemarkView.listPlacemarks(placemarks)
        var searchId = placemarkView.getId()
        val aPlacemark = search(searchId)

        if(aPlacemark != null) {
            if(placemarkView.updatePlacemarkData(aPlacemark)) {
                placemarks.update(aPlacemark)
                placemarkView.showPlacemark(aPlacemark)
                logger.info("Placemark Updated : [ $aPlacemark ]")
            }
            else
                logger.info("Placemark Not Updated")
        }
        else
            println("Placemark Not Updated...")
    }

    fun search() {
        val aPlacemark = search(placemarkView.getId())!!
        placemarkView.showPlacemark(aPlacemark)
    }

    fun search(id: Long) : PlacemarkModel? {
        val foundPlacemark = placemarks.findOne(id)
            return foundPlacemark
    }



}
