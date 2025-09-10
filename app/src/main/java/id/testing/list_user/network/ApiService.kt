package id.testing.list_user.network

import id.testing.list_user.model.ApiResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/users")
    suspend fun getUsers() : Response<List<ApiResponseItem>>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}