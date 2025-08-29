package com.privacy.message.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.privacy.message.ui.theme.PrivacyMessageTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrivacyMessageTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PrivacyMessageDemo()
                }
            }
        }
    }
}

@Composable
fun PrivacyMessageDemo() {
    var currentScreen by remember { mutableStateOf("Splash") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "PrivacyMessage Demo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        
        Text(
            text = "Güvenli ve Gizli Mesajlaşma Uygulaması",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Navigation Buttons
        Button(
            onClick = { currentScreen = "Splash" },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Splash Screen")
        }
        
        Button(
            onClick = { currentScreen = "Login" },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login Screen")
        }
        
        Button(
            onClick = { currentScreen = "Conversations" },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Conversations")
        }
        
        Button(
            onClick = { currentScreen = "Chat" },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Chat Screen")
        }
        
        Button(
            onClick = { currentScreen = "Profile" },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Profile Screen")
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Current Screen Display
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Current Screen:",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = currentScreen,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        // Features List
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Özellikler:",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("• Uçtan uca şifreleme (E2EE)", color = MaterialTheme.colorScheme.onPrimaryContainer)
                Text("• Signal protokolü", color = MaterialTheme.colorScheme.onPrimaryContainer)
                Text("• Modern UI (Material Design 3)", color = MaterialTheme.colorScheme.onPrimaryContainer)
                Text("• Güvenlik odaklı tasarım", color = MaterialTheme.colorScheme.onPrimaryContainer)
                Text("• Minimum veri toplama", color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
        }
    }
}