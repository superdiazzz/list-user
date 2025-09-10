package id.testing.list_user.util

import android.util.Log
import com.google.gson.Gson
import retrofit2.Response
import java.io.EOFException

class AppException(val msg: String?, val code: Int?): Exception(msg)

abstract class SafeApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T {

        try{

            val response = call.invoke()
            if (response.isSuccessful) {
                return response.body() ?: emptyList<T>() as T
            } else {
                throw AppException("Error: ${response.code()}", response.code())
            }


        }catch (e: EOFException){
            throw AppException(e.message.toString(), 444)
        }
    }

    private fun parseError(response: Response<*>): ErrorResponse {
        try {
            val errorBody = response.errorBody()?.string()
            return if(errorBody != null){
                Gson().fromJson(errorBody, ErrorResponse::class.java)
            }else{
                ErrorResponse(response.body().toString(), response.body().toString(), 499)
            }
        } catch (e: Exception) {
            return ErrorResponse("${e.message}", "${e.message}", 499)
        }
    }
}

data class ErrorResponse(
    val message: String?,
    val error: String?,
    val statusCode: Int?
)