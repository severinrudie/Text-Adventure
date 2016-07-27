package com.rudie.severin.textadventure;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.rudie.severin.textadventure.FragmentClasses.CharacterSelectFragment;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by erikrudie on 7/21/16.
 */
public class CharacterSelectFragmentUnitTest {

    CharacterSelectFragment frag = new CharacterSelectFragment();

    @Test
    public void movingSkillButtons_Works() {
        assert(frag.generateRandomName() instanceof String);
    }

    @Test
    public void swapSkills_swaps() {
        TextView tv1 = new TextView(frag.getContext());
        TextView tv2 = new TextView(frag.getContext());
        tv1.setText("tv1");
        tv2.setText("tv2");
        String text1 = tv1.getText().toString();
        String text2 = tv2.getText().toString();

        frag.swapSkills(tv1, tv2);
        assertEquals(tv1.getText().toString(), text2);
        assertEquals(tv2.getText().toString(), text1);
    }

}
