package org.setu.zone.console.main

import mu.KotlinLogging
import org.setu.zone.console.controllers.PlacemarkController

private val logger = KotlinLogging.logger {}
private val control = PlacemarkController()
fun main(args: Array<String>){
    control.start()
}