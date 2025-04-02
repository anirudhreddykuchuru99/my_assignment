package com.action.myassignment.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.action.myassignment.ui.viewmodel.UserViewModel
import com.action.myassignment.util.Result

@Composable
fun UserScreen(navController: NavController, viewModel: UserViewModel = hiltViewModel()) {
    val state = viewModel.userState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(8.dp)
    ) {
        when (val result = state.value) {
            is Result.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            is Result.Success -> {
                result.data.data.forEach { user ->
                    UserCard(user = user, navController = navController)
                }
            }
            is Result.Error -> {
                Text("Error: ${result.exception.message}", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun UserCard(user: com.action.myassignment.data.entity.Data, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("userDetails/${user.id}")
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = user.avatar,
                contentDescription = "User Avatar",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${user.first_name} ${user.last_name}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(text = "Email: ${user.email}")
        }
    }
}

@Composable
fun UserDetailsScreen(viewModel: UserViewModel = hiltViewModel(), userId: Int) {
    val state = viewModel.userState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center) {
        when (val result = state.value) {
            is Result.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            is Result.Success -> {
                val user = result.data.data.find { it.id == userId }
                user?.let {
                    AsyncImage(
                        model = it.avatar,
                        contentDescription = "User Avatar",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Name: ${it.first_name} ${it.last_name}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("Email: ${it.email}", fontSize = 16.sp)
                    Text("Id: ${it.id}", fontSize = 16.sp)
                } ?: Text("User not found.")
            }
            is Result.Error -> {
                Text("Error: ${result.exception.message}", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}