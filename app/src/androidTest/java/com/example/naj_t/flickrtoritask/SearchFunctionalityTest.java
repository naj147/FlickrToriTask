package com.example.naj_t.flickrtoritask;


import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.example.naj_t.flickrtoritask.view.MainActivity;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchFunctionalityTest {
    String mSearchString;
    @Rule
    public MyCustomRule<MainActivity> mActivityRule = new MyCustomRule<>(
            MainActivity.class);
    SearchView searchView;

    @Before
    public void initValidString() {
        // Specify a valid string.
        mSearchString = "Espresso";
        searchView = mActivityRule.getActivity().findViewById(R.id.search_tab);
        }

    @Test
    public void searchForAnImage(){
        onView(withId(R.id.search_tab))
                .perform(typeSearchViewText(mSearchString), closeSoftKeyboard());
        MatcherAssert.assertThat(searchView.getQuery().toString(), is(mSearchString));
    }
    public static ViewAction typeSearchViewText(final String text){
        return new ViewAction(){
            @Override
            public Matcher<View> getConstraints() {
                //Ensure that only apply if it is a SearchView and if it is visible.
                return allOf(isDisplayed(), isAssignableFrom(SearchView.class));
            }

            @Override
            public String getDescription() {
                return "Change view text";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((SearchView) view).setQuery(text,false);
            }
        };
    }
}
