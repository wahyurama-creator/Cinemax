package com.way.cinemax.data.model

data class ApiPopularResponse(
    val page: Int,
    val movies: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)