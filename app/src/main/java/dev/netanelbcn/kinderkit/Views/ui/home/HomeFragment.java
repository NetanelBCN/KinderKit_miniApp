package dev.netanelbcn.kinderkit.Views.ui.home;

import android.content.Intent;
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

import java.util.ArrayList;

import dev.netanelbcn.kinderkit.Adapters.MenuCardsAdapter;
import dev.netanelbcn.kinderkit.Interfaces.KidCallback;
import dev.netanelbcn.kinderkit.Models.Kid;
import dev.netanelbcn.kinderkit.R;
import dev.netanelbcn.kinderkit.Uilities.DataManager;
import dev.netanelbcn.kinderkit.Views.KidInfoActivity;
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
        adapter.setPlayerCallback(new KidCallback() {
            @Override
            public void CardClicked(Kid kid, int position) {
                Intent intent = new Intent(getContext(), KidInfoActivity.class);
                intent.putExtra("kidfname", kid.getfName());
                intent.putExtra("kidlname", kid.getlName());
                intent.putExtra("kidage", kid.getAge() + " Years Old");
                intent.putExtra("kidDate", "B-Date: " + kid.getBirthDate().toString());
                intent.putExtra("uri", kid.getProfilePhotoUri().toString());
                intent.putExtra("kidPosition", position);
                startActivity(intent);
            }
        });
        return root;
    }

    private void initViews(View v) {
        FH_RV_kids = v.findViewById(R.id.FH_RV_kids);
        FH_RV_kids.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        FH_RV_kids.setLayoutManager(layoutManager);
    }

    private void refreshKidsList() {
//        ArrayList<Kid> updatedKids = DataManager.getInstance().getKids();
//        adapter.setKids(updatedKids);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshKidsList();
    }
}