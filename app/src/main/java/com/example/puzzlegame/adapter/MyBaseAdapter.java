package com.example.puzzlegame.adapter;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.databinding.ItemRowLayoutButtonBinding;
import com.example.puzzlegame.listener.CommonActionListener;

import java.util.ArrayList;
import java.util.List;

public class MyBaseAdapter extends RecyclerView.Adapter<MyBaseAdapter.MyViewHolder> {

    private final Context mContext;
    private final int rawLayoutId;
    // private final AppUtil appUtil;
    private List<?> itemsArrayList;
    private CommonActionListener commonActionListener;


    public MyBaseAdapter(Context mContext, List<?> itemsArrayList, int rawLayoutId) {
        this.mContext = mContext;
        this.itemsArrayList = itemsArrayList;
        this.rawLayoutId = rawLayoutId;
        //  appUtil = new AppUtil();
    }

    public MyBaseAdapter(Context mContext, List<?> itemsArrayList, int rawLayoutId, CommonActionListener commonActionListener) {
        this.mContext = mContext;
        this.itemsArrayList = itemsArrayList;
        this.rawLayoutId = rawLayoutId;
        this.commonActionListener = commonActionListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), rawLayoutId, parent, false);
        return new MyViewHolder(binding);

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Object object = itemsArrayList.get(position);
        holder.setBinding(object);
    }

    @Override
    public int getItemCount() {
        return itemsArrayList != null ? itemsArrayList.size() : 0;
    }

    public void filterList(ArrayList<?> itemsArrayList) {
        this.itemsArrayList = itemsArrayList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ViewDataBinding binding;

        public MyViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            if (binding instanceof ItemRowLayoutButtonBinding) {

                commonActionListener = (CommonActionListener) mContext;
                // ((ItemRowLayoutButtonBinding) binding).btnLatter.setOnClickListener(this);
            }
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.btnLatter:
                    //commonActionListener.onClick(getLayoutPosition(), String.valueOf(v.getTag()));
                    break;
            }
        }


        @SuppressLint("SetTextI18n")
        public void setBinding(Object object) {
            if (binding instanceof ItemRowLayoutButtonBinding) {

                String latter = (String) object;
                // ((ItemRowLayoutButtonBinding) binding).btnFrontLatter.setText(latter);
                // ((ItemRowLayoutButtonBinding) binding).btnBackLatter.setText(latter);
                ((ItemRowLayoutButtonBinding) binding).btnLatter.setTag(latter);


                ((ItemRowLayoutButtonBinding) binding).btnLatter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((ItemRowLayoutButtonBinding) binding).btnLatter.setText(latter);
                        flipIt(((ItemRowLayoutButtonBinding) binding).btnLatter);
                        commonActionListener.onClick(getLayoutPosition(), String.valueOf(v.getTag()));
                    }
                });
            }
        }
    }

    private void flipIt(final View viewToFlip) {
        ObjectAnimator flip = ObjectAnimator.ofFloat(viewToFlip, "rotationX", 0f, 360f);
        flip.setDuration(1500);
        flip.start();

    }
}
