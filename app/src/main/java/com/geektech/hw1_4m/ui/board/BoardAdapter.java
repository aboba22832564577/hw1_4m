package com.geektech.hw1_4m.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.hw1_4m.R;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private final int[] img = {
            R.drawable.img,
            R.drawable.img_1,
            R.drawable.img_4,
            R.drawable.img_5
    };
    private final String[] title = {
            "Добро пожаловать!",
            "Дальнейшее использование",
            "Удачи вам во всем!",
            "Никогда не здавайтесь!"
    };
    private final String[] desc = {
            "Добро пожаловать наш дорогой гость! Мы очень рады вас приветствовать на нашем" +
                    " приложении.",
            "Мы уверены что вы будете использовать это приложение для дальнейшего вашего развития",
            "Мы всегда желаем вам наилучшего кодирования и чтоб у вас не было никаких крашей",
            "Уверенность в себе это наилучший энергетик или стимул для программиста!"
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vp,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(img[position], title[position], desc[position]);
    }

    @Override
    public int getItemCount() {
        return img.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title, description;
        private final ImageView image;
        public ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.vp_title);
            description = view.findViewById(R.id.vp_description);
            image = view.findViewById(R.id.vp_image);
        }

        public void onBind(int img, String title, String desc) {
            this.title.setText(title);
            this.description.setText(desc);
            this.image.setImageResource(img);
        }
    }
}
