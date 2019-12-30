package dev.jdvila.marvelunlimitedmark01;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public final ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void shouldBeAbleToLoadLoadingDialogOnAppOpen() {
        onView(withText("Loading...")).check(matches(isDisplayed()));
    }

    @Test
    public void shouldBeAbleToLoadComicBookTitleAfterOneSecond() throws InterruptedException {
        Thread.sleep(1000);
        onView(withId(R.id.comicTitleTextView)).noActivity().check(matches(withText("Ant-Man (2003) #1")));
    }

    @Test
    public void shouldBeAbleToLoadComicBookCoverImageAfterOneSecond() throws InterruptedException {
        Thread.sleep(1000);
        onView(withId(R.id.coverImageView)).check(matches(isDisplayed()));
    }
}
