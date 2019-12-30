package dev.jdvila.marvelunlimitedmark01.model

import java.io.Serializable

class Events(var available: Int?, var collectionURI: String?, items: List<Any>, var returned: Int?) : Serializable {
    var items: List<Any>? = null

    init {
        this.items = items
    }

}
