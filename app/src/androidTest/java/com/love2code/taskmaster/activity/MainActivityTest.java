package com.love2code.taskmaster.activity;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.love2code.taskmaster.R;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction button = onView(
                allOf(withId(R.id.allTasksBtn), withText("All Tasks"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.btnSetting), withText("Setting"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.addTaskBtn), withText("Add Task"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.TableLayout.class))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.usernameTxt), withText("Username: No Username"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView.check(matches(withText("Username: No Username")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.taskFragmentTextView), withText("0. title1"),
                        withParent(withParent(withId(R.id.taskListRecyclerView))),
                        isDisplayed()));
        textView2.check(matches(withText("0. title1")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.taskFragmentTextView), withText("1. title2"),
                        withParent(withParent(withId(R.id.taskListRecyclerView))),
                        isDisplayed()));
        textView3.check(matches(withText("1. title2")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.taskFragmentTextView), withText("1. title2"),
                        withParent(withParent(withId(R.id.taskListRecyclerView))),
                        isDisplayed()));
        textView4.check(matches(withText("1. title2")));
    }
}
