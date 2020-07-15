package com.example.workout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WorkoutDetailFragment extends Fragment {
    private int workoutId=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view=getView();
        if(view!=null){
            Workout workout=Workout.workouts[workoutId];

            TextView title=(TextView) view.findViewById(R.id.textTitle);
            TextView description=(TextView) view.findViewById(R.id.textDescription);

            title.setText(workout.getName());
            description.setText(workout.getDescription());
        }
    }

    public void setWorkout(int id){
        this.workoutId=id;
    }
}