package sr.business.stfs.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sr.business.stfs.R;
import sr.business.stfs.adapter.AdapterProducts;

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

        //This is the adapter
        AdapterProducts adapterProducts = new AdapterProducts(getContext());
        adapterProducts.createArrayList();

        //This is the list
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterProducts);

        return view;
    }

}
