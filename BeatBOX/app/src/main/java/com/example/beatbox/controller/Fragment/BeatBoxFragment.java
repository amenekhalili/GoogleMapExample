package com.example.beatbox.controller.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.beatbox.Model.Sound;
import com.example.beatbox.R;
import com.example.beatbox.Repository.BeatBoxRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class BeatBoxFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private BeatBoxRepository mBeatBoxRepository;

    public BeatBoxFragment() {
    }

    public static BeatBoxFragment newInstance() {
        BeatBoxFragment fragment = new BeatBoxFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBeatBoxRepository = BeatBoxRepository.getInstance(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_beat_box, container, false);

        findViews(view);
        initViews();
         setupAdapter();
         
        return  view;
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext() , 3));
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_beatBox);
    }

    private void setupAdapter(){
        List<Sound> sounds = mBeatBoxRepository.getSounds();
        SoundAdapter adapter = new SoundAdapter(sounds);
        mRecyclerView.setAdapter(adapter);


    }


    private class SoundHolder extends RecyclerView.ViewHolder{

        private Button mButton;
        private Sound mSound;


        public SoundHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            mButton = itemView.findViewById(R.id.button_beat_box);
        }

        public void bindSound(Sound sound){
            mSound = sound;
            mButton.setText(sound.getName());
        }

    }


    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{

        private List<Sound> mSounds;

        public List<Sound> getSounds() {
            return mSounds;
        }

        public void setSounds(List<Sound> sounds) {
            mSounds = sounds;
        }

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @NonNull
        @org.jetbrains.annotations.NotNull
        @Override
        public SoundHolder onCreateViewHolder(
                @NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {

             View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_sound
             ,parent , false);



            return new SoundHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull
                                                     BeatBoxFragment.SoundHolder holder,
                                     int position) {

            Sound sound = mSounds.get(position);
            holder.bindSound(sound);


        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}