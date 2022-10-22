package org.martinmcconnell.zone.console.main

import mu.KotlinLogging
import org.martinmcconnell.zone.console.controllers.PlacemarkController

private val logger = KotlinLogging.logger {}
private val control = PlacemarkController()
fun main(args: Array<String>){
    control.start()
}