package id.testing.list_user.model


import com.google.gson.annotations.SerializedName

data class ApiResponseItem(
    val id: Int,
    val name: String,
    val email: String
)
