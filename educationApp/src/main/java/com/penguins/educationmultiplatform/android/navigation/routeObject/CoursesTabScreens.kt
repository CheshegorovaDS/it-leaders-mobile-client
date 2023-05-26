package com.penguins.educationmultiplatform.android.navigation.routeObject

import androidx.navigation.NavOptions

const val coursesGraphRoute = "coursesGraphRoute"
const val coursesScreenRoute = "coursesScreenRoute"
const val detailCourseListScreenRoute = "detailCourseListScreenRoute"
const val detailCourseScreenRoute = "detailCourseScreenRoute"
const val mapScreenRoute = "mapScreenRoute"
const val courseId = "courseId"

sealed class CoursesTapScreens(
    var route: String,
    val options: NavOptions? = null,
    val inclusive: Boolean = false,
    val saveState: Boolean = false,
    val popTargetRoute: String = ""
) {
    object CoursesScreenRoute : CoursesTapScreens(
        route = coursesScreenRoute,
        options = NavOptions.Builder().setPopUpTo(0, inclusive = false).build()
    )

    object DetailCourseListScreenRoute : CoursesTapScreens(route = detailCourseListScreenRoute)
    object DetailCourseScreenRoute : CoursesTapScreens(route = "$detailCourseScreenRoute?$courseId={$courseId}") {
        fun createRoute(id: Int) : String {
            return "$detailCourseScreenRoute?$courseId=$id"
        }
    }

    object MapScreenRoute : CoursesTapScreens(
        route = mapScreenRoute,
        options = NavOptions.Builder().setPopUpTo(0, inclusive = false).build()
    )
}