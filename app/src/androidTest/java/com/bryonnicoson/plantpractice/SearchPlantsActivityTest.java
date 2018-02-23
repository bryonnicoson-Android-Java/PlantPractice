package com.bryonnicoson.plantpractice;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.bryonnicoson.plantpractice.dto.PlantDTO;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.*;

/**
 * Created by bryon on 2/23/18.
 */
public class SearchPlantsActivityTest {

    @Rule
    public ActivityTestRule<SearchPlantsActivity> activityRule = new ActivityTestRule<SearchPlantsActivity>(SearchPlantsActivity.class);

    @Test
    public void searchForRedbudShouldReturnAtLeastOneResult() {
        Context context = InstrumentationRegistry.getContext();

        onView(withId(R.id.actPlantName)).perform(typeText("Redbud"));
        onView(withId(R.id.btnPlantName)).perform(click());

        List<PlantDTO> plants = activityRule.getActivity().getPlants();
        assertThat(plants, not(empty()));

    }
}