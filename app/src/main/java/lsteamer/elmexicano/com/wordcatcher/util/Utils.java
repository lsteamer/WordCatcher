package lsteamer.elmexicano.com.wordcatcher.util;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

import lsteamer.elmexicano.com.wordcatcher.R;
import lsteamer.elmexicano.com.wordcatcher.model.WordWrapper;
import lsteamer.elmexicano.com.wordcatcher.model.Words;

import static android.support.v4.util.Preconditions.checkNotNull;

public class Utils {

    public class GetWordsAsync extends AsyncTask<Context, Void, List<Words>>{

        private Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Words> doInBackground(Context... params) {
            context = params[0];

            InputStreamReader inputStreamReader = new InputStreamReader(context.getResources().openRawResource(R.raw.words_v3));

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            final Gson gson = new Gson();

            WordWrapper wordWrapper = gson.fromJson(bufferedReader, WordWrapper.class);

            List<Words> wrdList = wordWrapper.getWordsList();

            return wrdList;
        }
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

    //Json Parser Helper
    public static String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
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
        while(randInt == number){
            randInt = rand.nextInt(range);
        }
        return randInt;
    }


}
