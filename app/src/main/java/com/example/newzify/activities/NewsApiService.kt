import models.NewsResponse
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Query

interface NewsApiService {
    // This defines the endpoint to fetch top headlines.
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>
}
