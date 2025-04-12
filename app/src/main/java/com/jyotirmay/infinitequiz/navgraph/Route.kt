package com.jyotirmay.infinitequiz.navgraph

sealed class Route(val route: String) {
    data object HomeScreen : Route(route = "home_screen")
    data object QuizScreen : Route(route = "quiz_screen")
    data object BookmarkScreen : Route(route = "bookmark_screen")
}

