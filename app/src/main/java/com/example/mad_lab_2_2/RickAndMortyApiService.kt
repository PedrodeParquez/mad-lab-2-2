package com.example.mad_lab_2_2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RickAndMortyApiService {
    @GET("character")
    suspend fun getCharacters(): Results

    companion object {
        fun create(): RickAndMortyApiService {
            return Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RickAndMortyApiService::class.java)
        }
    }
}