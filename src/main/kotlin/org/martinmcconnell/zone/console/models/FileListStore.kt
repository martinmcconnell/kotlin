package org.martinmcconnell.zone.console.models

interface FileListStore {
    fun findAll(): List<FileListModel>
    fun findOne(id: Long): FileListModel?
    fun create(placemark: FileListModel)
    fun update(placemark: FileListModel)

    fun delete(placemark: FileListModel)
}

