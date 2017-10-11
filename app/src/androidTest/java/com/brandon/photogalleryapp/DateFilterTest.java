package com.brandon.photogalleryapp;

/**
 * Created by Brandon on 2017-10-05.
 */

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ListView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.brandon.photogalleryapp.R.styleable.View;


@RunWith(AndroidJUnit4.class)
public class DateFilterTest {

    @Rule
    public ActivityTestRule<SearchActivity> mActivityRule =
            new ActivityTestRule<>(SearchActivity.class);

    @Test
    public void ensureTextChangesWork() {
        onView(withId(R.id.search_fromDate)).perform(typeText("01/10/17"), closeSoftKeyboard());
        onView(withId(R.id.search_toDate)).perform(typeText("02/10/17"), closeSoftKeyboard());
        onView(withId(R.id.search_search)).perform(click());
        onView(withId(R.id.filtered_testtext)).check(matches(withText("Filtered")));
        //onView(withId(R.id.searched_photo_list)).check(h);
        //onView(withId(R.id.searched_photo_list))
        Matchers m = new Matchers();
        onView(withId(R.id.searched_photo_list)).check(ViewAssertions.matches(m.withListSize(2)));
    }

    public static class Matchers {
        public Matcher<android.view.View> withListSize (final int size) {
            return new TypeSafeMatcher<View>() {
                @Override public boolean matchesSafely (final View view) {
                    return ((GridView) view).getCount () == size;
                }

                @Override public void describeTo (final Description description) {
                    description.appendText ("ListView should have " + size + " items");
                }
            };
        }
    }
}