package com.jyotirmay.infinitequiz.navgraph

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jyotirmay.infinitequiz.presentation.home.HomeViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.jyotirmay.infinitequiz.presentation.home.HomeScreen
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jyotirmay.infinitequiz.data.remote.model.QuizResponse
import com.jyotirmay.infinitequiz.data.remote.model.Solution
import com.jyotirmay.infinitequiz.presentation.quiz.QuizScreen
import com.jyotirmay.infinitequiz.presentation.quiz.QuizViewModel
import com.jyotirmay.infinitequiz.util.StringCompress
import org.json.JSONArray

/**
 * Defines the navigation graph for the application.
 * This function sets up the navigation routes and their corresponding composable screens.
 *
 * @param startDestination The initial route to be displayed when the app starts.
 */
@Composable
fun NavGraph(startDestination: String) {

    // Creates a NavController to manage app navigation
    val navController = rememberNavController()

    // Defines the navigation host with the start destination
    NavHost(navController = navController, startDestination = startDestination) {
        // Composable for the Home screen
        composable(route = Route.HomeScreen.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            viewModel.fetchCountry()

            var selectedCountry by remember { mutableStateOf("India") }

            HomeScreen(
                navController = navController,
                selectedCountry = selectedCountry,
                countries = viewModel.state.value,
                onCountrySelected = {
                    selectedCountry = it
                }
            )
        }
        // Composable for the Quiz screen
        composable(route = Route.QuizScreen.route) {
            val viewModel: QuizViewModel = hiltViewModel()
            LaunchedEffect(Unit) {
                viewModel.fetchQuestionList()
            }

            val quizList = viewModel.question.collectAsState()
            if (quizList.value.isNotEmpty()) {
                QuizScreen(
                    navController = navController,
                    isBookmark = false,
                    quizViewModel = viewModel,
                    quizList = quizList.value
                )
            } else {
                // Displays a loading indicator while questions are being fetched
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF1E1E1E)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Loading questions", color = Color.White)
                    }
                }
            }
        }

        // Composable for the Bookmark screen
        composable(route = Route.BookmarkScreen.route) {
            val viewModel: QuizViewModel = hiltViewModel()
            LaunchedEffect(Unit) {
                viewModel.getBookmarked()
            }

            val quizList = viewModel.bookmarked.collectAsState()
            if (quizList.value.isNotEmpty()) {
                val questionList = mutableListOf<QuizResponse>()
                quizList.value.forEach {bookmarked ->
                    val decompress = StringCompress.decompressString(bookmarked.solution)

                    Log.d("test", decompress)
                    val type = object : TypeToken<List<Solution>>() {}.type
                    val solutionList: List<Solution> = Gson().fromJson(decompress, type)

                    questionList.add(QuizResponse(
                        bookmarked.uuidIdentifier,
                        bookmarked.questionType,
                        bookmarked.question,
                        bookmarked.option1,
                        bookmarked.option2,
                        bookmarked.option3,
                        bookmarked.option4,
                        bookmarked.correctOption,
                        bookmarked.sort,
                        solutionList
                    ))
                }

                QuizScreen(
                    navController = navController,
                    isBookmark = true,
                    quizViewModel = viewModel,
                    quizList = questionList
                )
            } else {
                val bookMarkedStatus = viewModel.noBookmarked.collectAsState()
                if (bookMarkedStatus.value) {
                    // Displays a message if no questions are bookmarked
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF1E1E1E)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No question bookmarked",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                    }
                } else {
                    // Displays a loading indicator while bookmarks are being fetched
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF1E1E1E)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}