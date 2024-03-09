package com.example.mad_lab_2_2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mad_lab_2_2.Results
import com.example.mad_lab_2_2.RickAndMortyApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class RickAndMortyViewModel : ViewModel() {
    var charactersData = MutableLiveData<Results>()

    suspend fun retrofitAPI() {
        if (charactersData.value != null) {
            return
        }

        CoroutineScope(Dispatchers.IO).async {
            try {
                val characterRetrofit = RickAndMortyApiService.create().getCharacters()
                charactersData.postValue(characterRetrofit)
            } catch (ex : Exception) { }
        }.await()
    }
}