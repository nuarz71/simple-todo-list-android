package io.github.nuarz71.todolist.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun TodoListNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
) {
    val navigator = TodoListNavigator(navController = navHostController)
    CompositionLocalProvider(LocalNavigatorProvider provides navigator) {
        NavHost(
            navController = navHostController,
            startDestination = ScreenRoute.ListScreen.ROUTE,
            modifier = modifier
        ) {
            composable(route = ScreenRoute.ListScreen.ROUTE) {
                //TODO: implementation screen
            }
            composable(
                route = ScreenRoute.AddEditScreen.ROUTE_WITH_ARGS,
                arguments = listOf(
                    navArgument(name = ScreenRoute.AddEditScreen.ARG_ID) {
                        type = NavType.LongType
                        defaultValue = 0
                    }
                )
            ) {
                //TODO: implementation screen
            }
        }
    }
}