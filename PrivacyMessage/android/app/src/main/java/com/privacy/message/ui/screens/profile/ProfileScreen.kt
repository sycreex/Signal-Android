package com.privacy.message.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Profil",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Geri"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                ProfileHeader()
            }
            
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            
            items(profileSections) { section ->
                ProfileSection(section = section)
            }
        }
    }
}

@Composable
private fun ProfileHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Avatar
        Surface(
            modifier = Modifier.size(120.dp),
            shape = MaterialTheme.shapes.circular,
            color = MaterialTheme.colorScheme.primary
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "👤",
                    fontSize = 60.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // User Info
        Text(
            text = "Kullanıcı Adı",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = "+90 5XX XXX XX XX",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Edit Profile Button
        OutlinedButton(
            onClick = { /* TODO: Edit profile */ }
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Profili Düzenle")
        }
    }
}

@Composable
private fun ProfileSection(section: ProfileSection) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Column {
            section.items.forEach { item ->
                ListItem(
                    headlineContent = {
                        Text(
                            text = item.title,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    supportingContent = item.subtitle?.let {
                        { Text(text = it) }
                    },
                    leadingContent = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    trailingContent = {
                        if (item.showArrow) {
                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                        }
                    },
                    modifier = Modifier.clickable { item.onClick?.invoke() }
                )
                
                if (item != section.items.last()) {
                    Divider(
                        modifier = Modifier.padding(start = 56.dp)
                    )
                }
            }
        }
    }
}

data class ProfileItem(
    val title: String,
    val subtitle: String? = null,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val showArrow: Boolean = true,
    val onClick: (() -> Unit)? = null
)

data class ProfileSection(
    val title: String,
    val items: List<ProfileItem>
)

private val profileSections = listOf(
    ProfileSection(
        title = "Güvenlik",
        items = listOf(
            ProfileItem(
                title = "PIN Değiştir",
                subtitle = "Hesap güvenliği için PIN değiştirin",
                icon = Icons.Default.Lock,
                onClick = { /* TODO: Change PIN */ }
            ),
            ProfileItem(
                title = "Güvenlik Kodu",
                subtitle = "QR kod ile güvenlik doğrulaması",
                icon = Icons.Default.QrCode,
                onClick = { /* TODO: Show security code */ }
            ),
            ProfileItem(
                title = "Kaybolan Mesajlar",
                subtitle = "Mesajların otomatik silinme süresi",
                icon = Icons.Default.Timer,
                onClick = { /* TODO: Disappearing messages */ }
            )
        )
    ),
    ProfileSection(
        title = "Uygulama",
        items = listOf(
            ProfileItem(
                title = "Bildirimler",
                subtitle = "Bildirim ayarlarını yönetin",
                icon = Icons.Default.Notifications,
                onClick = { /* TODO: Notifications */ }
            ),
            ProfileItem(
                title = "Tema",
                subtitle = "Açık/Koyu mod ayarları",
                icon = Icons.Default.Palette,
                onClick = { /* TODO: Theme settings */ }
            ),
            ProfileItem(
                title = "Dil",
                subtitle = "Uygulama dili",
                icon = Icons.Default.Language,
                onClick = { /* TODO: Language settings */ }
            )
        )
    ),
    ProfileSection(
        title = "Hakkında",
        items = listOf(
            ProfileItem(
                title = "Sürüm",
                subtitle = "1.0.0",
                icon = Icons.Default.Info,
                showArrow = false
            ),
            ProfileItem(
                title = "Lisans",
                subtitle = "GNU AGPLv3",
                icon = Icons.Default.Description,
                onClick = { /* TODO: Show license */ }
            ),
            ProfileItem(
                title = "Gizlilik Politikası",
                icon = Icons.Default.PrivacyTip,
                onClick = { /* TODO: Privacy policy */ }
            )
        )
    ),
    ProfileSection(
        title = "Hesap",
        items = listOf(
            ProfileItem(
                title = "Çıkış Yap",
                icon = Icons.Default.Logout,
                onClick = { /* TODO: Logout */ }
            )
        )
    )
)