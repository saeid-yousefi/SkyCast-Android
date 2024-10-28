package com.sy.onboarding_ui

data class OnBoardingPage(
    val step: Int,
    val imageId: Int,
    val titleId: Int,
    val descriptionId: Int
)

internal val OnBoardingPages = listOf(
    OnBoardingPage(
        step = 1,
        imageId = R.drawable.image_one,
        titleId = R.string.title_one,
        descriptionId = R.string.description_one
    ),
    OnBoardingPage(
        step = 2,
        imageId = R.drawable.image_two,
        titleId = R.string.title_two,
        descriptionId = R.string.description_two
    ),
    OnBoardingPage(
        step = 3,
        imageId = R.drawable.image_three,
        titleId = R.string.title_three,
        descriptionId = R.string.description_three
    ),
    OnBoardingPage(
        step = 4,
        imageId = R.drawable.image_four,
        titleId = R.string.title_four,
        descriptionId = R.string.description_four
    ),
)
