package com.junior.cinephile.entities

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("status") var status: String? = null,
    @SerializedName("copyright") var copyright: String? = null,
    @SerializedName("has_more") var hasMore: Boolean? = null,
    @SerializedName("num_results") var numResults: Int? = null,
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()
)

data class Link(
    @SerializedName("type") var type: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("suggested_link_text") var suggestedLinkText: String? = null
)

data class Multimedia(
    @SerializedName("type") var type: String? = null,
    @SerializedName("src") var src: String? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("width") var width: Int? = null
)

data class Results(
    @SerializedName("display_title") var displayTitle: String? = null,
    @SerializedName("mpaa_rating") var mpaaRating: String? = null,
    @SerializedName("critics_pick") var criticsPick: Int? = null,
    @SerializedName("byline") var byline: String? = null,
    @SerializedName("headline") var headline: String? = null,
    @SerializedName("summary_short") var summaryShort: String? = null,
    @SerializedName("publication_date") var publicationDate: String? = null,
    @SerializedName("opening_date") var openingDate: String? = null,
    @SerializedName("date_updated") var dateUpdated: String? = null,
    @SerializedName("link") var link: Link? = Link(),
    @SerializedName("multimedia") var multimedia: Multimedia? = Multimedia()
)