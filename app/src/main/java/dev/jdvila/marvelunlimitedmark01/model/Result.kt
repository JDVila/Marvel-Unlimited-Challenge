package dev.jdvila.marvelunlimitedmark01.model

import java.io.Serializable

class Result(var id: Int?, var digitalId: Int?, var title: String?, var issueNumber: Int?, var variantDescription: String?, var description: String?, var modified: String?, var isbn: String?, var upc: String?, var diamondCode: String?, var ean: String?, var issn: String?, var format: String?, var pageCount: Int?, textObjects: List<TextObject>, var resourceURI: String?, urls: List<Url>, var series: Series?, variants: List<Any>, collections: List<Any>, collectedIssues: List<Any>, dates: List<Date>, prices: List<Price>, var thumbnail: Thumbnail?, images: List<Image>, var creators: Creators?, var characters: Characters?, var stories: Stories?, var events: Events?) : Serializable {
    var textObjects: List<TextObject>? = null
    var urls: List<Url>? = null
    var variants: List<Any>? = null
    var collections: List<Any>? = null
    var collectedIssues: List<Any>? = null
    var dates: List<Date>? = null
    var prices: List<Price>? = null
    var images: List<Image>? = null

    init {
        this.textObjects = textObjects
        this.urls = urls
        this.variants = variants
        this.collections = collections
        this.collectedIssues = collectedIssues
        this.dates = dates
        this.prices = prices
        this.images = images
    }

}