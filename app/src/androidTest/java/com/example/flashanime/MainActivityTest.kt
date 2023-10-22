package com.example.flashanime

import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    // set up MainActivity
    @Before
    fun setup() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // mock user
        val mockedFirebaseAuth = mock(FirebaseAuth::class.java)
        val mockedFirebaseUser = mock(FirebaseUser::class.java)

        activityScenario.onActivity { activity ->
            val viewModel = activity.viewModel
            viewModel.auth = mockedFirebaseAuth
        }

        `when`(mockedFirebaseAuth.currentUser).thenReturn(mockedFirebaseUser)
    }

    @Test
    fun checkSeasonFragmentIsLoaded() {
        // check HomeFragment is loaded
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))

        // click TabLayout by "本季新番"
        onView(withText("本季新番")).perform(click())

        // check SeasonFragment is loaded
        onView(withId(R.id.carousel_recycler_view)).check(matches(isDisplayed()))

        // wait for data loading
        Thread.sleep(1500)

        // swipeUp
        onView(withId(R.id.nestedScrollView)).perform(swipeUp())

        // check recyclerView is loaded
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

    }

    @Test
    fun checkWeekFragmentIsLoaded() {
        // check WeekFragment is loaded
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))

        // click TabLayout by "週期表"
        onView(withText("週期表")).perform(click())

        // check WeekFragment W1 exist
        onView(withId(R.id.recyclerView_W1)).check(matches(isDisplayed()))

        // wait for data loading
        Thread.sleep(1000)

        // swipeUp
        onView(withId(R.id.horizontalScrollView)).perform(swipeLeft())
        onView(withId(R.id.horizontalScrollView)).perform(swipeLeft())

        // check WeekFragment W7 exist
        onView(withId(R.id.recyclerView_W7)).check(matches(isDisplayed()))

    }

    @Test
    fun checkTabLayOutIsFunctional() {
        // check HomeFragment is loaded
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))

        // click TabLayout by "週期表"
        onView(withText("週期表")).perform(click())

        // check WeekFragment is loaded
        onView(withId(R.id.recyclerView_W1)).check(matches(isDisplayed()))

        // click TabLayout by "本季新番"
        onView(withText("本季新番")).perform(click())

        // check SeasonFragment is loaded
        onView(withId(R.id.carousel_recycler_view)).check(matches(isDisplayed()))

        // click TabLayout by "週期表"
        onView(withText("週期表")).perform(click())

        // check WeekFragment is loaded
        onView(withId(R.id.recyclerView_W1)).check(matches(isDisplayed()))

        // click TabLayout by "本季新番"
        onView(withText("本季新番")).perform(click())

        // check SeasonFragment is loaded
        onView(withId(R.id.carousel_recycler_view)).check(matches(isDisplayed()))

    }


    @Test
    fun clickAllFragmentFabButtonIsFunctional() {
        // check homeFragment is loaded
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))

        // click BottomNavigationView's second page which is AllFragment
        onView(withId(R.id.bottomNavView)).perform(navigateTo(R.id.navigation_all))

        // check AllFragment is loaded
        onView(withId(R.id.fab)).check(matches(isDisplayed()))

        // click AllFragment's fab button
        onView(withId(R.id.fab)).perform(click())

        // check CategoryDialog is loaded
        onView(withId(R.id.chip_group)).check(matches(isDisplayed()))

        // click two chips
        onView(withId(R.id.chip_school)).perform(click())
        onView(withId(R.id.chip_romance)).perform(click())

        // click send button
        onView(withId(R.id.button_send)).perform(click())

        // check AllFragment is loaded
        onView(withId(R.id.fab)).check(matches(isDisplayed()))

    }

    @Test
    fun checkCollectedFragmentIsLoaded() {
        // check HomeFragment is loaded
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))

        // click BottomNavigationView's second page which is AllFragment
        onView(withId(R.id.bottomNavView)).perform(navigateTo(R.id.navigation_collected))

        // check Collected Fragment is loaded
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkVocabularyFragmentIsLoaded() {
        // check HomeFragment is loaded
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))

        // click BottomNavigationView's second page which is AllFragment
        onView(withId(R.id.bottomNavView)).perform(navigateTo(R.id.navigation_vocabulary))

        // check Vocabulary Fragment is loaded
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkVocabularyDetailFragmentIsLoaded() {
        // check HomeFragment is loaded
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))

        // click BottomNavigationView's second page which is AllFragment
        onView(withId(R.id.bottomNavView)).perform(navigateTo(R.id.navigation_vocabulary))

        // check Vocabulary Fragment is loaded
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        // check VocabularyDetail Fragment is loaded
        onView(withId(R.id.anime_constrain)).check(matches(isDisplayed()))
    }

    @Test
    fun checkVocabularyDetailFragmentIsFunctional() {
        // check HomeFragment is loaded
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))

        // click BottomNavigationView's second page which is AllFragment
        onView(withId(R.id.bottomNavView)).perform(navigateTo(R.id.navigation_vocabulary))

        // check Vocabulary Fragment is loaded
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    2,
                    click()
                )
            )

        // check VocabularyDetail Fragment is loaded
        onView(withId(R.id.anime_constrain)).check(matches(isDisplayed()))

        try {
            // dialog will be create if there is no words in  collected list
            onView(withId(R.id.mode_collected)).perform(click())

            // check if dialog has been create
            onView(withId(R.id.dialogTitle)).check(matches(isDisplayed()))

            // dismiss dialog
            onView(withId(R.id.confirm_button)).perform(click())

            Log.i("tryCollected", "don't have any collected words")

        } catch (e: NoMatchingViewException) {
            // no collected words list, continue testing
            Log.i("tryCollected", "have some collected words")
        }

        // wait for data loading
        Thread.sleep(1000)

        // chose radiobutton
        onView(withId(R.id.mode_all)).perform(click())

        // swipeUp
        onView(withId(R.id.recyclerView)).perform(swipeUp())

        // wait for data loading
        Thread.sleep(1000)

        // navigate to wordTestFragment
        onView(withId(R.id.testButton)).perform(click())

        // check WordsTestFragment is loaded
        onView(withId(R.id.card_stack_view)).check(matches(isDisplayed()))

    }

    @Test
    fun checkWordsTestFragmentIsFunctional() {
        // check HomeFragment is loaded
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))

        // click BottomNavigationView's second page which is AllFragment
        onView(withId(R.id.bottomNavView)).perform(navigateTo(R.id.navigation_vocabulary))

        // check Vocabulary Fragment is loaded
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    2,
                    click()
                )
            )

        // check VocabularyDetail Fragment is loaded
        onView(withId(R.id.anime_constrain)).check(matches(isDisplayed()))

        // navigate to wordTestFragment
        onView(withId(R.id.testButton)).perform(click())

        // check WordsTestFragment is loaded
        onView(withId(R.id.card_stack_view)).check(matches(isDisplayed()))

        while (true) {
            try {
                onView(withId(R.id.card_stack_view)).perform(swipeLeft())
                onView(withId(R.id.card_stack_view)).perform(swipeRight())
                onView(withId(R.id.card_stack_view)).perform(swipeRight())
                // Only check visibility if the card_stack_view is no longer available.
                if (!viewIsDisplayed(R.id.add_score)) {
                    onView(withId(R.id.review_recyclerview)).check(
                        matches(
                            withEffectiveVisibility(
                                ViewMatchers.Visibility.VISIBLE
                            )
                        )
                    )
                    Log.i("viewIsDisplayed", "View is Gone.")
                    break
                }
            } catch (e: NoMatchingViewException) {
                break
            }
        }
        Log.i("viewIsDisplayed", "out")

        // swipe the review_recyclerview
        onView(withId(R.id.review_recyclerview)).perform(swipeLeft())

    }

    @Test
    fun checkProfileFragmentIsFunctional() {
        // check HomeFragment is loaded
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))

        // click BottomNavigationView's second page which is AllFragment
        onView(withId(R.id.bottomNavView)).perform(navigateTo(R.id.navigation_profile))

        // check Vocabulary Fragment is loaded
        onView(withId(R.id.artist_detail_main_pic)).check(matches(isDisplayed()))

        // check watch history is exist
        onView(withId(R.id.watch_history)).perform(click())

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        onView(withId(R.id.back)).perform(click())

        // check collected anime is exist
        onView(withId(R.id.collected_anime)).perform(click())

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        onView(withId(R.id.back)).perform(click())

        // check words collection is exist
        onView(withId(R.id.words_collection)).perform(click())

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        // wait for api info loading
        Thread.sleep(2000)

        onView(withId(R.id.episode_title)).check(matches(isDisplayed()))

        // wait for video loading
        Thread.sleep(10000)

        onView(withId(R.id.dismiss)).perform(click())

        onView(withId(R.id.back)).perform(click())

        // check words collection is exist
        onView(withId(R.id.about)).perform(click())

    }

    @Test
    fun checkDetailFragmentIsFunctional() {
        // check HomeFragment is loaded
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))

        // click TabLayout by "本季新番"
        onView(withText("本季新番")).perform(click())

        // check SeasonFragment is loaded
        onView(withId(R.id.carousel_recycler_view)).check(matches(isDisplayed()))

        // wait for data loading
        Thread.sleep(1500)

        // swipeUp
        onView(withId(R.id.nestedScrollView)).perform(swipeUp())

        // check recyclerView is loaded
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        // navigate to detail fragment
        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1, clickChildViewWithId(R.id.anime_constrain)
                )
            )

        // wait for video prepare
        Thread.sleep(4000)

        onView(withId(R.id.youtube_player_view)).perform(click())

        // wait for video play
        Thread.sleep(7000)

        onView(withId(R.id.autoTrack)).perform(click())

        // wait for video play
        Thread.sleep(3000)

        onView(withId(R.id.autoTrack)).perform(click())

        // wait for video play
        Thread.sleep(2000)

        onView(allOf(withId(R.id.back), isDisplayed())).perform(click())

        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))

    }
}



// bottom navigation
private fun navigateTo(@IdRes resId: Int) = object : ViewAction {
    override fun getConstraints(): Matcher<View> {
        return allOf(isDisplayed(), isAssignableFrom(BottomNavigationView::class.java))
    }
    override fun getDescription(): String {
        return "navigate to a specific menu item using its id"
    }
    override fun perform(uiController: UiController, view: View) {
        val bottomNav = view as BottomNavigationView
        bottomNav.selectedItemId = resId
    }
}

// check view is displayed or not
private fun viewIsDisplayed(@IdRes viewId: Int): Boolean {
    return try {
        onView(withId(viewId)).check(matches(isDisplayed()))
        true
    } catch (e: NoMatchingViewException) {
        false
    } catch (e: AssertionError) {
        false
    }
}

// click on a specific view by its id in a recyclerView
private fun clickChildViewWithId(@IdRes childViewId: Int): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return allOf(isAssignableFrom(RecyclerView::class.java), isDisplayed())
        }

        override fun getDescription(): String {
            return "Click on a child view with specified ID."
        }

        override fun perform(uiController: UiController, view: View) {
            val childView = view.findViewById<View>(childViewId)
            childView?.performClick()
        }
    }
}