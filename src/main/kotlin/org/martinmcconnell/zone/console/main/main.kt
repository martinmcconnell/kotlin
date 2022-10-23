package org.martinmcconnell.zone.console.main

import mu.KotlinLogging
import org.martinmcconnell.zone.console.controllers.MenuController

private val logger = KotlinLogging.logger {}
private val control = MenuController()
fun main(args: Array<String>){
    control.start()
}