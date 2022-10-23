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
        var foundFile: FileListModel? = fileList.find { p -> p.id == id }
        return foundFile
    }

    override fun create(placemark: FileListModel) {
        placemark.id = generateRandomId()
        fileList.add(placemark)
        serialize()
    }

    override fun update(placemark: FileListModel) {
        var foundFile = findOne(placemark.id!!)
        if (foundFile != null) {
            foundFile.title = placemark.title
            foundFile.description = placemark.description
        }
        serialize()
    }

    override fun delete(file: FileListModel) {
        fileList.remove(file)
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