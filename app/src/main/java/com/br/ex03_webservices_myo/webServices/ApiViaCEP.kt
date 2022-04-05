package com.br.ex03_webservices_myo.webServices

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiViaCEP {
    @GET("{CEP}/json/")
    fun getEnderecoByCEP(@Path("CEP") CEP : String) : Call<CEP>
}