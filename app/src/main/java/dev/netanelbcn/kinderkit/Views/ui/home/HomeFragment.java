package dev.netanelbcn.kinderkit.Views.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dev.netanelbcn.kinderkit.Adapters.MenuCardsAdapter;
import dev.netanelbcn.kinderkit.Interfaces.KidCallback;
import dev.netanelbcn.kinderkit.R;
import dev.netanelbcn.kinderkit.Uilities.DataManager;
import dev.netanelbcn.kinderkit.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private RecyclerView FRAGSCORE_RV_recycler;
    private MenuCardsAdapter adapter;
//    private DataManager dataManager;
    private KidCallback callbackCardClicked;
    private RecyclerView FH_RV_kids;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.FHMTVTitle;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        adapter = new MenuCardsAdapter(getContext());
        initViews(root);
        return root;
    }

//    public HomeFragment() {
//        adapter = new MenuCardsAdapter(getContext());
//    }

    private void initViews(View v) {
        FH_RV_kids = v.findViewById(R.id.FH_RV_kids);
        FH_RV_kids.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        FH_RV_kids.setLayoutManager(layoutManager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}