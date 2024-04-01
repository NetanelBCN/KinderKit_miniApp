//package dev.netanelbcn.kinderkit.Adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.card.MaterialCardView;
//import com.google.android.material.imageview.ShapeableImageView;
//import com.google.android.material.textview.MaterialTextView;
//
//import java.util.ArrayList;
//
//import dev.netanelbcn.kinderkit.R;
//
//public class MenuCardsAdapter extends RecyclerView.Adapter<MenuCardsAdapter.KidAdapter.KidViewHolder>{
//
//    private ArrayList<String> kids;
//    private Context context;
//    private OnKidClickListener onKidClickListener;
//
//    public MenuCardsAdapter(ArrayList<String> kids, Context context, OnKidClickListener onKidClickListener) {
//        this.kids = kids;
//        this.context = context;
//        this.onKidClickListener = onKidClickListener;
//    }
//
//    @NonNull
//    @Override
//    public KidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kid_card, parent, false);
//        return new KidViewHolder(view, onKidClickListener);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MenuCardsAdapter.KidAdapter.KidViewHolder holder, int position) {
//        holder.KC_MTV_name.setText(kids.get(position));
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return kids.size();
//    }
//
//    public static class KidViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        ShapeableImageView KC_SIV_photo;
//        MaterialCardView KC_SIV_card;
//        MaterialTextView KC_MTV_name;
//        OnKidClickListener onKidClickListener;
//
//        public KidViewHolder(@NonNull View itemView, OnKidClickListener onKidClickListener) {
//            super(itemView);
//            KC_SIV_photo = itemView.findViewById(R.id.KC_SIV_photo);
//            KC_MTV_name = itemView.findViewById(R.id.KC_MTV_name);
//            KC_SIV_card = itemView.findViewById(R.id.KC_MCV_card);
//            this.onKidClickListener = onKidClickListener;
//            KC_SIV_card.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            onKidClickListener.onKidClick(getAdapterPosition());
//        }
//    }
//
//    public interface OnKidClickListener {
//        void onKidClick(int position);
//    }
//
//}
