package com.ojt.project.service;

import java.util.List;

import com.ojt.project.entity.WorkoutVideo;

public interface WorkoutVideoService {
    // Fetch all workouts
    List<WorkoutVideo> getAllWorkouts();

    // Fetch workouts by category and type
    List<WorkoutVideo> getWorkoutsByCategoryAndType(String workoutCategory, String workoutType);

    // Fetch a workout by ID
    WorkoutVideo getWorkoutById(Long id);

    // Save (insert or update) a workout
    WorkoutVideo saveWorkout(WorkoutVideo workoutVideo);


    // Delete a workout
    void deleteWorkout(Long id);
    
    long countAllWorkouts();
}
