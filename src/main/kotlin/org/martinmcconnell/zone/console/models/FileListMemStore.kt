package org.martinmcconnell.zone.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class FileListMemStore : FileListStore {
    val placemarks = ArrayList<FileListModel>()

    override fun findAll(): List<FileListModel> {
        return placemarks
    }

    override fun findOne(id: Long): FileListModel? {
        var foundPlacemark: FileListModel? = placemarks.find { p -> p.id == id }
        return foundPlacemark
    }

    override fun create(placemark: FileListModel) {
        placemark.id = getId()
        placemarks.add(placemark)
        logAll()
    }

    override fun update(placemark: FileListModel) {
        var foundPlacemark = findOne(placemark.id!!)
        if (foundPlacemark != null) {
            foundPlacemark.title = placemark.title
            foundPlacemark.description = placemark.description
        }
    }

    override fun delete(placemark: FileListModel) {
        placemarks.remove(placemark)
    }

    internal fun logAll() {
        placemarks.forEach { logger.info("$(it)") }
    }
}
