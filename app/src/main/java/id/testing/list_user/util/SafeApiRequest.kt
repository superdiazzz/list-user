package id.testing.list_user.util

import retrofit2.Response
import java.io.IOException

class AppException(val msg: String?, val code: Int?): Exception(msg)

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                response.body() ?: throw AppException("Empty body", response.code())
            } else {
                val errorBody = response.errorBody()?.string()
                throw AppException(errorBody ?: "Unknown error", response.code())
            }
        } catch (e: IOException) {
            // 👈 covers no internet, timeouts, DNS issues
            throw AppException("No Internet Connection", null)
        } catch (e: Exception) {
            throw AppException(e.message ?: "Unknown error", null)
        }
    }
}

data class ErrorResponse(
    val message: String?,
    val error: String?,
    val statusCode: Int?
)