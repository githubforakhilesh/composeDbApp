package com.example.newdbapp.Presenter.activity

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newdbapp.Domain.Model.CustomerItem
import com.example.newdbapp.Domain.Model.CustomerListData
import com.example.newdbapp.Presenter.ViewModel.CustomerListUiState
import com.example.newdbapp.Presenter.ViewModel.CustomerListViewModel
import com.example.newdbapp.R
import com.example.newdbapp.ui.theme.OrangeColor
import com.example.newdbapp.ui.theme.RedColor

@Composable
fun HomeScreen(viewModel: CustomerListViewModel = hiltViewModel()) {
    val uiState by viewModel.custListData.collectAsState()
    val isLoading = uiState is CustomerListUiState.LoadingState
    val context = LocalContext.current
    var customerListData by remember { mutableStateOf<CustomerListData?>(null) }
    
    LaunchedEffect(Unit) {
        viewModel.fetchUserList()
    }
    
    LaunchedEffect(uiState) {
        when(uiState) {
            is CustomerListUiState.ErrorState -> Toast.makeText(context, (uiState as CustomerListUiState.ErrorState).message, Toast.LENGTH_SHORT).show()
            CustomerListUiState.IdleState -> {}
            CustomerListUiState.LoadingState -> {}
            is CustomerListUiState.SuccessState -> {
                 (uiState as CustomerListUiState.SuccessState).customerListData?.let { customerListData = it }
            }
        }
    }
    Scaffold(
        topBar = {
            HomeTopBar()
        },
        bottomBar = {
            HomeBottomNavigation()
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
        ) {
            val deliveryItems = customerListData?.customerList?: emptyList()

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(deliveryItems) { item ->
                    DeliveryCard(item)
                }
            }
        }
        if(isLoading){
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(24.dp),
                strokeWidth = 3.dp // Slightly thinner line so it fits elegantly inside the button
            )
        }
    }
}

@Composable
fun HomeTopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(OrangeColor, RedColor)
                )
            )
            .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo
            Card(
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.size(45.dp)
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.mipmap.ic_launcher_logo),
                        contentDescription = "Logo",
                        modifier = Modifier.size(35.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // ID Text
            Column {
                Text(
                    text = "101001",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "v0.0.0",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Action Icons
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeTopBarIcon(Icons.Default.Info, onClick = {})
                HomeTopBarIcon(Icons.AutoMirrored.Filled.List, onClick = {})
                HomeTopBarIcon(Icons.Default.Search, onClick = {})
                HomeTopBarIcon(Icons.Default.Notifications, onClick = {})
                HomeTopBarIcon(Icons.Default.MoreVert, onClick = {})
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Pending",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Or -- 0 : Py -- 0",
            color = Color.White,
            fontSize = 14.sp
        )
    }
}

@Composable
fun HomeTopBarIcon(icon: ImageVector, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.2f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(20.dp)
                .clickable { onClick() }
        )
    }
}

@Composable
fun DeliveryCard(item: CustomerItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    item.username?.let {
                        Text(
                            text = it,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0D1B3E)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    item.address?.let {
                        Text(
                            text = it,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            lineHeight = 20.sp
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE8F5E9)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = null,
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            if (item.bagHangingToDoor == 1 || item.callBeforeDelivery == 1 || item.doorImageUrl?.isNotEmpty() == true || item.voiceNote?.isNotEmpty() == true) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    if (item.voiceNote?.isNotEmpty() == true)
                        ActionCircleIcon(Icons.Default.PlayArrow)
                    if (item.callBeforeDelivery == 1)
                        ActionCircleIcon(Icons.Default.Call)
                    if (item.bagHangingToDoor == 1)
                        ActionCircleIcon(Icons.Default.ShoppingCart)
                    if (item.doorImageUrl?.isNotEmpty() == true)
                        ActionCircleIcon(Icons.Default.Face)
                }
            }
        }
    }
}


@Composable
fun ActionCircleIcon(icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color(0xFFE8F5E9)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF4CAF50),
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun HomeBottomNavigation() {
    Column {
        // Tab row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFCFD8DC))
                .height(60.dp)
        ) {
            BottomTabItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color(0xFFB0BEC5)),
                icon = Icons.Default.Refresh,
                label = "Pending",
                isSelected = true
            )
            BottomTabItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                icon = Icons.Default.CheckCircle,
                label = "Delivered",
                isSelected = false
            )
            BottomTabItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                icon = Icons.Default.MoreVert,
                label = "Other",
                isSelected = false
            )
        }

        // Stats row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatIcon(Icons.Default.ThumbUp, "0", Color.Gray)
                Spacer(modifier = Modifier.width(16.dp))
                StatIcon(Icons.Default.ThumbUp, "2", Color.Yellow)
                Spacer(modifier = Modifier.width(16.dp))
                StatIcon(Icons.Default.ThumbUp, "0", Color(0xFF4CAF50))
            }

            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(24.dp)
                    .background(Color.Gray)
            )

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Using ThumbUp with 180 deg rotation for ThumbDown if missing
                StatIcon(Icons.Default.ThumbUp, "25", RedColor)
            }
        }
    }
}

@Composable
fun BottomTabItem(modifier: Modifier, icon: ImageVector, label: String, isSelected: Boolean) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isSelected) Color.Black else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = if (isSelected) Color.Black else Color.Gray,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun StatIcon(icon: ImageVector, count: String, tint: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(contentAlignment = Alignment.BottomEnd) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(24.dp)
            )
            Box(
                modifier = Modifier
                    .offset(x = 8.dp, y = (-8).dp)
                    .clip(CircleShape)
                    .background(Color(0xFF1976D2))
                    .padding(horizontal = 4.dp)
            ) {
                Text(text = count, color = Color.White, fontSize = 10.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
