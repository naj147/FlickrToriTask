package com.example.naj_t.flickrtoritask;

import com.example.naj_t.flickrtoritask.view.activities.PhotoDetailsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;

public class PhotoDetailsTest {
    @Rule
    public MyCustomRule<PhotoDetailsActivity> mActivityRule = new MyCustomRule<>(
            PhotoDetailsActivity.class);
    @Before
    public void setup(){

    }
    @Test
    public  void TestPhotoDetailsActivity(){

        onView(withId(R.id.cancel)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(mActivityRule.getActivity().isDestroyed(),is(true));
    }

}
