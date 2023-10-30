package ru.tim.recipes.features.recipes.ui

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.raywenderlich.rwnews.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tim.recipes.MainActivity
import ru.tim.recipes.R
import ru.tim.recipes.RecipeApplication

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class RecipesListFragmentTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

//    @Test
//    fun testRecipesIsDisplayed() {
//        launchFragmentInHiltContainer<RecipesListFragment>()
//        onView(withId(R.id.recipeImage)).check(matches(isDisplayed()))
//    }

}