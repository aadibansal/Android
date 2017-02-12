package com.example.aditya.end_to_end_login_flow_testing;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;

import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.aditya.end_to_end_login_flow_testing", appContext.getPackageName());
    }

    @Test
    public void testResultTextViewIsDisplayed() {
        onView(withId(R.id.usernameEditText)).perform(typeText("Edureka"));
        onView(withId(R.id.passwordEditText)).perform(typeText("Edureka123"));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.resultTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void testResultTextViewDisplaysCorrectMessage() {
        onView(withId(R.id.usernameEditText)).perform(typeText("Edureka"));
        onView(withId(R.id.passwordEditText)).perform(typeText("Edureka123"));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.resultTextView)).check(matches(withText(R.string.success)));

        onView(withId(R.id.usernameEditText)).perform(clearText());
        onView(withId(R.id.passwordEditText)).perform(clearText());

        onView(withId(R.id.usernameEditText)).perform(typeText("Edureka"));
        onView(withId(R.id.passwordEditText)).perform(typeText("Edureka"));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.resultTextView)).check(matches(withText(R.string.failure)));
    }

    @Test
    public void testCorrectBackgroundColorChange() {
        onView(withId(R.id.usernameEditText)).perform(typeText("Edureka"));
        onView(withId(R.id.passwordEditText)).perform(typeText("Edureka123"));
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.activity_main)).check(matches(new BoundedMatcher<View, ViewGroup>(ViewGroup.class) {
            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(ViewGroup viewGroup) {
                int bgColor = ((ColorDrawable) viewGroup.getBackground()).getColor();
                return bgColor == Color.GREEN;
            }
        }));

        onView(withId(R.id.usernameEditText)).perform(clearText());
        onView(withId(R.id.passwordEditText)).perform(clearText());

        onView(withId(R.id.usernameEditText)).perform(typeText("Edureka"));
        onView(withId(R.id.passwordEditText)).perform(typeText("Edureka"));
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.activity_main)).check(matches(new BoundedMatcher<View, ViewGroup>(ViewGroup.class) {
            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(ViewGroup viewGroup) {
                int bgColor = ((ColorDrawable) viewGroup.getBackground()).getColor();
                return bgColor == Color.RED;
            }
        }));
    }
}
