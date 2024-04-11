package dev.netanelbcn.kinderkit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import dev.netanelbcn.kinderkit.Interfaces.KidCallback;
import dev.netanelbcn.kinderkit.Models.Kid;
import dev.netanelbcn.kinderkit.R;
import dev.netanelbcn.kinderkit.Uilities.DataManager;

public class MenuCardsAdapter extends RecyclerView.Adapter<MenuCardsAdapter.KidViewHolder> {
    private Context context;
    private ArrayList<Kid> kids;
    private KidCallback kidCallback;
    private DataManager dataManager = DataManager.getInstance();

    public MenuCardsAdapter(Context context) {
        this.context = context;
        this.kids = dataManager.getKids();
    }

    public MenuCardsAdapter setPlayerCallback(KidCallback playerCallback) {
        this.kidCallback = playerCallback;
        return this;
    }

    public void setKids(ArrayList<Kid> kids) {
        this.kids = kids;
    }

    @NonNull
    @Override
    public MenuCardsAdapter.KidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kid_card, parent, false);
        return new KidViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuCardsAdapter.KidViewHolder holder, int position) {
        Kid kid = getItem(position);
        holder.KC_MTV_name.setText(kid.getfName() + " " + kid.getlName());
        Glide.with(this.context)
                .load(kid.getProfilePhotoUri())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.KC_SIV_photo);
        holder.KC_SIV_photo.setImageURI(kid.getProfilePhotoUri());
        //  String ur="https://blog.hubspot.com/hs-fs/hubfs/parts-url_0.webp?width=650&height=396&name=parts-url_0.webp";
        Glide.with(context)
                .load(kid.getProfilePhotoUri())
                .placeholder(R.drawable.ic_launcher_background) // Placeholder image while loading
                .into(holder.KC_SIV_photo);
    }

    @Override
    public int getItemCount() {
        return kids.size();
    }

    private Kid getItem(int position) {
        return kids.get(position);
    }

    public class KidViewHolder extends RecyclerView.ViewHolder {
        private ShapeableImageView KC_SIV_photo;
        private MaterialTextView KC_MTV_name;
        private MaterialCardView KC_MCV_card;

        public KidViewHolder(@NonNull View itemView) {
            super(itemView);
            KC_SIV_photo = itemView.findViewById(R.id.KC_SIV_photo);
            KC_MTV_name = itemView.findViewById(R.id.KC_MTV_name);
            KC_MCV_card = itemView.findViewById(R.id.KC_MCV_card);
            KC_MCV_card.setOnClickListener(v -> {
                if (kidCallback != null) {
                    kidCallback.CardClicked(getItem(getAdapterPosition()), getAdapterPosition());
                }
            });

        }
    }
}
