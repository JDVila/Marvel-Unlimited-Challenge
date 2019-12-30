package dev.jdvila.marvelunlimitedmark01.model

import java.io.Serializable

class Creators(var available: Int?, var collectionURI: String?, items: List<Item>, var returned: Int?) : Serializable {
    var items: List<Item>? = null

    init {
        this.items = items
    }

}