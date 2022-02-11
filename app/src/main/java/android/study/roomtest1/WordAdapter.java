package android.study.roomtest1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyy
 * @date 2022年02月10日 下午4:08
 */
public class WordAdapter extends RecyclerView.Adapter<WordAdapter.MyViewHolder> {

    //    private MyViewModel myViewModel;
    private List<Word> allWords = new ArrayList<>();

    private boolean isCardPattern = false;

    public WordAdapter(boolean isCardPattern) {
        this.isCardPattern = isCardPattern;
    }

    public void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (isCardPattern)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_holder, parent, false);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basic_view_holder, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Word item = allWords.get(position);
        holder.tvId.setText(String.valueOf(item.getId()));
        holder.tvEnglish.setText(item.getWord());
        holder.tvChinese.setText(item.getChineseMeaning());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvId, tvEnglish, tvChinese;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.textViewId);
            tvEnglish = itemView.findViewById(R.id.textViewEnglish);
            tvChinese = itemView.findViewById(R.id.textViewChinese);
        }
    }

}
