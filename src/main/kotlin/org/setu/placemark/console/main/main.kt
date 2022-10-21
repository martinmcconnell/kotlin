package org.setu.placemark.console.main

import mu.KotlinLogging
import org.setu.placemark.console.controllers.PlacemarkController

private val logger = KotlinLogging.logger {}
private val control = PlacemarkController()
fun main(args: Array<String>){
    control.start()
}