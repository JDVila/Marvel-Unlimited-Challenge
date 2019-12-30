package dev.jdvila.marvelunlimitedmark01.model

import java.io.Serializable

class MarvelResponse(var code: Int?, var status: String?, var copyright: String?, var attributionText: String?, var attributionHTML: String?, var etag: String?, var data: Data?) : Serializable

