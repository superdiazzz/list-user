package id.testing.list_user.repository

import id.testing.list_user.network.ApiService
import javax.inject.Inject

interface UserRepository {
}

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {



}