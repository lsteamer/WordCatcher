package lsteamer.elmexicano.com.wordcatcher.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.util.Random;

import lsteamer.elmexicano.com.wordcatcher.model.WordModel;

import static android.support.v4.util.Preconditions.checkNotNull;

public class Utils {


    public static WordModel readJsonFile(Context context, int resourceJson) {
        InputStreamReader inputStreamReader = new InputStreamReader(context.getResources().openRawResource(resourceJson));

        return new Gson().fromJson(inputStreamReader, WordModel.class);
    }


    // Static utility methods
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    //Decide if it will
    public static boolean coinFlip(Random rand) {

        return rand.nextBoolean();
    }

    public static int getRandomNumber(Random rand, int range) {

        return rand.nextInt(range);
    }

    public static int getRandomNumber(Random rand, int range, int number) {
        //A different random number
        int randInt = rand.nextInt(range);
        while (randInt == number) {
            randInt = rand.nextInt(range);
        }
        return randInt;
    }


}
