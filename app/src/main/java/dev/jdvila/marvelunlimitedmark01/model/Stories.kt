package dev.jdvila.marvelunlimitedmark01.model

import java.io.Serializable

class Stories(var available: Int?, var collectionURI: String?, items: List<Item_>, var returned: Int?) : Serializable {
    var items: List<Item_>? = null

    init {
        this.items = items
    }

}