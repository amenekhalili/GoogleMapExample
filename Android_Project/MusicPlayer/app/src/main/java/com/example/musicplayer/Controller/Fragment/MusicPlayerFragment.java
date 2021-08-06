package com.example.musicplayer.Controller.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.musicplayer.Model.Music;
import com.example.musicplayer.R;
import com.example.musicplayer.Repository.MusicPlayerRepository;

import java.io.IOException;
import java.util.List;


public class MusicPlayerFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MusicPlayerRepository mMusicPlayerRepository;





    public MusicPlayerFragment() {
        // Required empty public constructor
    }


    public static MusicPlayerFragment newInstance() {
        MusicPlayerFragment fragment = new MusicPlayerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mMusicPlayerRepository = MusicPlayerRepository.getInstance(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_music_player, container, false);
          findViews(view);
          initViews();
          setAdapter();
        return view;
    }



    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
    }

    private void initViews() {
     mRecyclerView.setLayoutManager(new GridLayoutManager(getContext() , 3));
    }

    private void setAdapter(){
        List<Music> music =  mMusicPlayerRepository.getMusic();
        MusicAdapter adapter = new MusicAdapter(music);
        mRecyclerView.setAdapter(adapter);

    }

    private class MusicHolder extends RecyclerView.ViewHolder{

        private ImageView mImageView;
        private Button mButton;
        private Music mMusic;


        public MusicHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.img_view_pic);
            mButton = itemView.findViewById(R.id.btn_play_music);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        mMusicPlayerRepository.playMusic(mMusic);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        public void bindMusic(Music music){
            mMusic = music;
            mButton.setText(mMusic.getTitle());
        }
    }

    private class MusicAdapter extends RecyclerView.Adapter<MusicHolder>{

        private List<Music> mMusic;

        public List<Music> getMusic() {
            return mMusic;
        }

        public void setMusic(List<Music> music) {
            mMusic = music;
        }

        public MusicAdapter(List<Music> music){
            mMusic = music;
        }

        @NonNull
        @Override
        public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).
                    inflate(R.layout.item_list_music,
                    parent ,
                            false);
            return new MusicHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MusicHolder holder, int position) {
            Music music  = mMusic.get(position);
            holder.bindMusic(music);
        }

        @Override
        public int getItemCount() {
            return mMusic.size();
        }
    }

}