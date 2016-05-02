package sr.business.stfs.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sr.business.stfs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductOverviewFragment extends Fragment {


    public ProductOverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_overview,container,false);
        return view;
    }

}
