package org.martinmcconnell.zone.console.models

data class FileListModel(
    var id: Long = 0,
    var title: String = "",
    var description: String = "",
    var path: String = ""
)