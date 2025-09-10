package id.testing.list_user.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.testing.list_user.model.ApiResponseItem
import id.testing.list_user.repository.UserRepository
import id.testing.list_user.util.AppException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){

    private val _users = MutableStateFlow<List<ApiResponseItem>>(emptyList())
    val users : StateFlow<List<ApiResponseItem>> = _users.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading : StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()


    fun fetchData() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _users.value = userRepository.fetchUsers()
            } catch (e: AppException) {
                _error.value = e.msg ?: "Something went wrong"
            }
            _isLoading.value = false
        }
    }

}