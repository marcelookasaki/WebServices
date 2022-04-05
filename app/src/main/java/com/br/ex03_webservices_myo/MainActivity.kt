package com.br.ex03_webservices_myo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.br.ex03_webservices_myo.databinding.ActivityMainBinding
import com.br.ex03_webservices_myo.webServices.CEP
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnBusca.setOnClickListener {

          RetrofitInstance.retrofit.getEnderecoByCEP(binding.txtBuscaCEP.text.toString())
              .enqueue(object : Callback<CEP>{
                override fun onResponse(call: Call<CEP>, response: Response<CEP>) {
                    response.let {
                    val CEPs: CEP? = it.body()

                        if (CEPs == null) {
                            binding.txtRelatorio.text = "Cep inválido!"

                        } else {
                            binding.txtRelatorio.text =
                                "Pesquisa CEP - VIACEP: " + "\n" +
                                        "Logradouro: " + CEPs.logradouro + "\n" +
                                        "Bairro: " + CEPs.bairro + "\n" +
                                        "Localiade: " + CEPs.localidade + "\n" +
                                        "UF: " + CEPs.uf + "\n" +
                                        "Cep: " + CEPs.cep
                        }
                    }
                }
                /* Caso ocorra uma falha na resposta lançamos um erro no log */
                override fun onFailure(call: Call<CEP>?, t: Throwable?) {
                    Log.e("MYTAG", "ERRO Genérico!" )
                }
            })
        }
    }
}
