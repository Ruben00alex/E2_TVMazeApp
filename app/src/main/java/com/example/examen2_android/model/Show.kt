package com.example.examen2_android.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Show(
    val id: Int,
    val name: String,
    val genres: List<String>,
    val rating: Rating?,
    val image: Image?,
    val premiered: String?,
    val language: String,
    val summary: String?,
    val network: Network?,
    @Json(name = "_embedded") val embedded: Embedded? = null
)

@JsonClass(generateAdapter = true)
data class Embedded(
    val cast: List<CastMember>
)

@JsonClass(generateAdapter = true)
data class CastMember(
    val person: Person,
    val character: Character,
    val self: Boolean,
    val voice: Boolean
)

@JsonClass(generateAdapter = true)
data class Person(
    val id: Int,
    val name: String,
    val country: Country?,
    val birthday: String?,
    val deathday: String?,
    val gender: String?,
    val image: Image?,
    @Json(name = "_links") val links: Links?
)

@JsonClass(generateAdapter = true)
data class Character(
    val id: Int,
    val name: String,
    val image: Image?,
    @Json(name = "_links") val links: Links?
)

@JsonClass(generateAdapter = true)
data class Links(
    val self: SelfLink
)

@JsonClass(generateAdapter = true)
data class SelfLink(
    val href: String
)

@JsonClass(generateAdapter = true)
data class ShowSearchResponse(
    val show: Show
)


@JsonClass(generateAdapter = true)
data class Rating(
    val average: Double?
)

@JsonClass(generateAdapter = true)
data class Image(
    val medium: String?,
    val original: String?
)

@JsonClass(generateAdapter = true)
data class Network(
    val name: String,
    val country: Country?
)

@JsonClass(generateAdapter = true)
data class Country(
    val name: String,
    val code: String,
    val timezone: String
)
