package com.example.retrofit;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.txt_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apex.fararayanehco.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    mTextViewResult.setText("code:" + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID:" + post.getId() + "\n";
                    content += "User ID:" + post.getUserId() + "\n";
                    content += "Title:" + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n";
                    mTextViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                mTextViewResult.setText(t.getMessage());
            }
        });

    }
}