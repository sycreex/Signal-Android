package com.privacy.message.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Registration : Screen("registration")
    object ConversationList : Screen("conversation_list")
    object Chat : Screen("chat/{conversationId}") {
        val arguments = listOf(
            navArgument("conversationId") {
                type = NavType.StringType
            }
        )
        
        fun createRoute(conversationId: String) = "chat/$conversationId"
    }
    object Profile : Screen("profile")
}