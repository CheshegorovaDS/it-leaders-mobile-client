package com.penguins.educationmultiplatform.android.di

import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.google.android.gms.location.LocationServices
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel.CoursesViewModel
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel.DetailCourseViewModel
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel.SharedViewModel
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel.VideoItemViewModel
import com.penguins.educationmultiplatform.android.data.location.DefaultLocationTracker
import com.penguins.educationmultiplatform.android.data.navigation.DestinationController
import com.penguins.educationmultiplatform.android.domain.location.LocationTracker
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.domain.useCases.GetSchoolsFromRepository
import com.penguins.educationmultiplatform.android.domain.usecases.tests.GetTestCaseUseCase

import com.penguins.educationmultiplatform.android.domain.usecases.GetNewsByCategoryUseCase
import com.penguins.educationmultiplatform.android.domain.usecases.GetNewsByParamsUseCase
import com.penguins.educationmultiplatform.android.domain.usecases.GetNewsListUseCase
import com.penguins.educationmultiplatform.android.domain.useCases.GetVideoCoursesUseCase

import com.penguins.educationmultiplatform.android.mapScreen.viewModel.YandexMapViewModel
import com.penguins.educationmultiplatform.android.navigation.navigation.NewsNavigation
import com.penguins.educationmultiplatform.android.newsScreen.headingNews.viewModel.HeadingNewsViewModel
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.viewModel.NewsListViewModel
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.viewModel.CategoryViewModel
import com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.viewModel.NewsViewModel
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.viewModel.SearchNewsViewModel
import com.penguins.educationmultiplatform.android.profileScreen.viewModel.ProfileScreenViewModel
import com.penguins.educationmultiplatform.android.testsScreen.viewModel.TestCaseViewModel
import com.penguins.educationmultiplatform.android.testsScreen.viewModel.TestCategoriesViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    single  {
        HttpClient(Android) {
            expectSuccess = true
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    single { GetVideoCoursesUseCase() }
    single<Player> { ExoPlayer.Builder(androidContext()).build() }

    //viewModels
    viewModel { YandexMapViewModel(get(), get()) }
    viewModel { CategoryViewModel(get(), get()) }
    viewModel { NewsListViewModel(get(), get()) }
    viewModel { ProfileScreenViewModel(get(), get()) }
    viewModel { NewsViewModel(get()) }
    viewModel { SearchNewsViewModel(get(), get()) }
    viewModel { TestCategoriesViewModel(get()) }
    viewModel { TestCaseViewModel(get(), get()) }
    viewModel { HeadingNewsViewModel(get(), get()) }
    viewModel { CoursesViewModel(get()) }


    single { SharedViewModel() }
    viewModel { DetailCourseViewModel(get(), get()) }
    viewModel { VideoItemViewModel(get(),get(), get()) }


    //location
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }
    single <LocationTracker> { DefaultLocationTracker(get(),androidContext()) }

    //useCases
    single { GetSchoolsFromRepository() }
    single <AppNavigation> { DestinationController() }
    single { NewsNavigation() }
    single { GetTestCaseUseCase() }

    single { GetNewsListUseCase(get()) }
    single { GetNewsByCategoryUseCase(get()) }
    single { GetNewsByParamsUseCase(get()) }
}