package android.study.roomtest1;

import android.os.Bundle;
import android.study.roomtest1.databinding.ActivityMainBinding;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyViewModel myViewModel;
    private ActivityMainBinding binding;
    private WordAdapter wordBasicAdapter;
    private WordAdapter wordCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        myViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MyViewModel.class);

        wordBasicAdapter = new WordAdapter(false);
        wordCardAdapter = new WordAdapter(true);


        myViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                wordBasicAdapter.setAllWords(words);
                wordBasicAdapter.notifyDataSetChanged();
                wordCardAdapter.setAllWords(words);
                wordCardAdapter.notifyDataSetChanged();
            }
        });


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setLifecycleOwner(this);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.recyclerView.setAdapter(wordBasicAdapter);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Word word1 = new Word("word", "单词");
                Word word2 = new Word("English", "英语");
                Word word3 = new Word("Hello", "你好");

                myViewModel.insertWords(word1, word2, word3);
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.deleteAllWords();
            }
        });

        binding.switchPattern.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.recyclerView.setAdapter(wordCardAdapter);
                }else{
                    binding.recyclerView.setAdapter(wordBasicAdapter);
                }
            }
        });
    }


}