package io.github.nuarz71.todolist.android.ui.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

val LocalNavigatorProvider = staticCompositionLocalOf<TodoListNavigator> { error("No Navigator") }

class TodoListNavigator(
    val navController: NavController
) {
    fun toListScreen(
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        navController.navigate(
            route = ScreenRoute.ListScreen.ROUTE,
            navOptions = navOptions,
            navigatorExtras = navigatorExtras
        )
    }
    
    fun toAddEditTaskScreen(
        id: Long? = null,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        navController.navigate(
            route = ScreenRoute.AddEditScreen.ROUTE.let { route ->
                if (id != null) {
                    route.plus("?")
                        .plus(ScreenRoute.AddEditScreen.ARG_ID)
                        .plus("=$id")
                } else {
                    route
                }
            },
            navOptions = navOptions,
            navigatorExtras = navigatorExtras
        )
    }
    
    fun popBackStack() {
        navController.popBackStack()
    }
    
    fun popBackStack(route: String, inclusive: Boolean = false, saveState: Boolean = false) {
        navController.popBackStack(route = route, inclusive = inclusive, saveState = saveState)
    }
}

object ScreenRoute {
    object ListScreen {
        const val ROUTE = "list"
    }
    
    object AddEditScreen {
        const val ROUTE = "task"
        const val ARG_ID = "id"
        const val ROUTE_WITH_ARGS = "task?$ARG_ID={$ARG_ID}"
    }
}