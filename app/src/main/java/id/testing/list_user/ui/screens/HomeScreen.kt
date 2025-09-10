package id.testing.list_user.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.testing.list_user.model.ApiResponseItem
import id.testing.list_user.ui.screens.components.TopAppBar
import id.testing.list_user.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()){

    val users by viewModel.users.collectAsState()
    val loading by viewModel.isLoading.collectAsState()

    LaunchedEffect (Unit){
        viewModel.fetchData()
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = "List User",
                showBack = false,
                onBackPress = {}
            )
        }
    ){ innerPadding ->

        Column (modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ){
            if(loading){
                Row (
                    modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp),
                    horizontalArrangement = Arrangement.Center){
                    CircularProgressIndicator()
                }
            }else{
                Text("Daftar User")
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ){
                    items(users){ item ->
                        UserItem(item)
                    }
                }
            }
        }
    }
}

@Composable
fun UserItem(item: ApiResponseItem) {
    Column {
        Text("${item.name}", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("${item.email}", style = MaterialTheme.typography.bodySmall)
    }
}

