package com.sy.common_ui.graphs

sealed class RootGraphs(val route: String) {
    data object OnBoarding : RootGraphs("onboarding_graph")
}