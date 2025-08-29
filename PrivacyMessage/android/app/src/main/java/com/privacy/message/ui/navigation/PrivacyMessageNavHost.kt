package com.privacy.message.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.privacy.message.ui.screens.auth.LoginScreen
import com.privacy.message.ui.screens.auth.RegistrationScreen
import com.privacy.message.ui.screens.chat.ChatScreen
import com.privacy.message.ui.screens.conversation.ConversationListScreen
import com.privacy.message.ui.screens.profile.ProfileScreen
import com.privacy.message.ui.screens.splash.SplashScreen

@Composable
fun PrivacyMessageNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToMain = {
                    navController.navigate(Screen.ConversationList.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegistration = {
                    navController.navigate(Screen.Registration.route)
                },
                onNavigateToMain = {
                    navController.navigate(Screen.ConversationList.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Registration.route) {
            RegistrationScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onRegistrationComplete = {
                    navController.navigate(Screen.ConversationList.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.ConversationList.route) {
            ConversationListScreen(
                onNavigateToChat = { conversationId ->
                    navController.navigate(Screen.Chat.createRoute(conversationId))
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }
        
        composable(
            route = Screen.Chat.route,
            arguments = Screen.Chat.arguments
        ) {
            ChatScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}