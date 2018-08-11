package com.example.naj_t.flickrtoritask;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.example.naj_t.flickrtoritask.view.MainActivity;
import com.example.naj_t.flickrtoritask.view.PhotoDetailsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class RecycleViewFunctionalitiesTest {
    @Rule
    public IntentsTestRule<MainActivity> mIntentsRule =
            new IntentsTestRule<>(MainActivity.class);
RecyclerView recyclerView;
    @Before
    public void setUp(){
        recyclerView = mIntentsRule.getActivity().findViewById(R.id.my_recycler_view);
    }
    @Test
    public void recycleViewTest(){
        //Easier than Threading
        if(recyclerView.getChildCount()<1) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            onView(withId(R.id.my_recycler_view))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
            intended(allOf(
                    hasComponent(hasClassName(PhotoDetailsActivity.class.getName())),
                    hasExtraWithKey(MainActivity.PHOTO_FARM),
                    hasExtraWithKey(MainActivity.PHOTO_SERVER),
                    hasExtraWithKey(MainActivity.PHOTO_SECRET),
                    hasExtraWithKey(MainActivity.PHOTO_OWNER),
                    hasExtraWithKey(MainActivity.PHOTO_ID),
                    hasExtraWithKey(MainActivity.PHOTO_TITLE)
            ));



    }



}
