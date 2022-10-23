package org.martinmcconnell.zone.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.martinmcconnell.zone.console.helpers.exists
import org.martinmcconnell.zone.console.helpers.read
import org.martinmcconnell.zone.console.helpers.write
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "file-list.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<FileListModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class FileListJSONStore : FileListStore {

    var fileList = mutableListOf<FileListModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }


    override fun findAll(): MutableList<FileListModel> {
        return fileList
    }

    override fun findOne(id: Long) : FileListModel? {
        var foundPlacemark: FileListModel? = fileList.find { p -> p.id == id }
        return foundPlacemark
    }

    override fun create(placemark: FileListModel) {
        placemark.id = generateRandomId()
        fileList.add(placemark)
        serialize()
    }

    override fun update(placemark: FileListModel) {
        var foundPlacemark = findOne(placemark.id!!)
        if (foundPlacemark != null) {
            foundPlacemark.title = placemark.title
            foundPlacemark.description = placemark.description
        }
        serialize()
    }

    override fun delete(placemark: FileListModel) {
        fileList.remove(placemark)
        serialize()
    }

    internal fun logAll() {
        fileList.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(fileList, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        fileList = Gson().fromJson(jsonString, listType)
    }
}