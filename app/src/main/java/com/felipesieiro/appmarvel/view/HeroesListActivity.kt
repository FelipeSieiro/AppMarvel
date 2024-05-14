package com.felipesieiro.appmarvel.view



import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felipesieiro.appmarvel.HeroesAdapter
import com.felipesieiro.appmarvel.R
import com.felipesieiro.appmarvel.api.ApiService
import com.felipesieiro.appmarvel.api.RetrofitClient
import com.felipesieiro.appmarvel.model.Heroes
import com.felipesieiro.appmarvel.model.HeroesResponse
import com.felipesieiro.appmarvel.util.Config
import javax.security.auth.callback.Callback


class HeroesListActivity : AppCompatActivity(), HeroesAdapter.OnMovieClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HeroesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = HeroesAdapter(emptyList(), this) // Passa a instância da Activity como listener

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fetchMovies()
    }

    override fun onMovieClick(heroes: Heroes) {
        // Abre a tela de detalhes passando o filme clicado
        val intent = Intent(this, HeroesDetailsActivity::class.java)
        intent.putExtra("heroes", heroes)
        startActivity(intent)
    }

    private fun fetchMovies() {
        val apiService = RetrofitClient.getClient().create(ApiService::class.java)
        apiService.getPopularMovies(Config.HEROES_DB_API_KEY)
            .enqueue(object : Callback<HeroesResponse> {
                override fun onResponse(
                    call: Call<HeroesResponse>,
                    response: Response<HeroesResponse>
                ) {
                    if (response.isSuccessful) {
                        val heroes = response.body()?.heroes
                        heroes?.let {
                            adapter.setData(it) // Passa a lista de herois para o adaptador
                            Log.d("HeroesListActivity", "Heroes fetched successfully: $heroes")
                        }
                    } else {
                        Log.e("HeroesListActivity", "Error fetching heroes: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<HeroesResponse>, t: Throwable) {
                    Log.e("HeroesListActivity", "Error fetching heroes: ${t.message}")
                }
            })
    }
}