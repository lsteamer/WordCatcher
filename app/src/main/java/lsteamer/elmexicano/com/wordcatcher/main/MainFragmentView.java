package lsteamer.elmexicano.com.wordcatcher.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lsteamer.elmexicano.com.wordcatcher.R;

public class MainFragmentView extends Fragment implements MainContract.ViewLayer {


    public MainFragmentView(){

    }

    public static MainFragmentView newInstance(){ return new MainFragmentView(); }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_view, container, false);

        return view;
    }
}
