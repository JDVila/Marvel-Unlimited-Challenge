package dev.jdvila.marvelunlimitedmark01.model

import java.io.Serializable

class Data(var offset: Int?, var limit: Int?, var total: Int?, var count: Int?, results: List<Result>) : Serializable {
    var results: List<Result>? = null

    init {
        this.results = results
    }

}
