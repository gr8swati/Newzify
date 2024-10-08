// VolleySingleton.kt
import android.content.Context
import retrofit.GsonConverterFactory
import retrofit.Retrofit


object retrofitInstance  {
    private const val BASE_URL = "https://newsapi.org/v2/"
    val api: NewsApiService by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }
}
