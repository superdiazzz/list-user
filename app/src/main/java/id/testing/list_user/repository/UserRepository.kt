package id.testing.list_user.repository

import id.testing.list_user.model.ApiResponseItem
import id.testing.list_user.network.ApiService
import id.testing.list_user.util.SafeApiRequest
import javax.inject.Inject

interface UserRepository {

    suspend fun fetchUsers(): List<ApiResponseItem>
}

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository, SafeApiRequest() {

    override suspend fun fetchUsers(): List<ApiResponseItem> {
        return apiRequest {
            apiService.getUsers()
        }
    }
}