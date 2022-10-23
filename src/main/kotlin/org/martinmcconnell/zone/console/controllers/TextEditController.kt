package org.martinmcconnell.zone.console.controllers

var filename = ""

class TextEditController {
    fun edit() {
        println("Enter a filename to edit")
        filename = readLine()!!
        println("Editing $filename")
    }
    fun save() {
        println("Saving $filename")
    }
    fun new() {
        println("Enter a filename to create")
        filename = readLine()!!
        println("Creating $filename")
    }
    fun delete() {
        println("Enter a filename to delete")
        filename = readLine()!!
        println("Deleting $filename")
    }
    fun quit() {
        println("Quitting")
    }
}