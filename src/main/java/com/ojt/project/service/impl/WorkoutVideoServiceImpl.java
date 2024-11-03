package com.ojt.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojt.project.entity.WorkoutVideo;
import com.ojt.project.repository.WorkoutVideoRepository;
import com.ojt.project.service.WorkoutVideoService;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutVideoServiceImpl implements WorkoutVideoService {

    private final WorkoutVideoRepository workoutRepository;

    @Autowired
    public WorkoutVideoServiceImpl(WorkoutVideoRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    // Get all workouts
    @Override
    public List<WorkoutVideo> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    // Get workouts by category and type
    @Override
    public List<WorkoutVideo> getWorkoutsByCategoryAndType(String workoutCategory, String workoutType) {
        return workoutRepository.findByWorkoutCategoryAndWorkoutType(workoutCategory, workoutType);
    }

    // Get workout by ID
    @Override
    public WorkoutVideo getWorkoutById(Long id) {
        Optional<WorkoutVideo> workoutVideo = workoutRepository.findById(id);
        return workoutVideo.orElse(null);
    }

    // Save or update workout
    @Override
    public WorkoutVideo saveWorkout(WorkoutVideo workoutVideo) {
        return workoutRepository.save(workoutVideo);
    }

    // Delete workout by IDs
    @Override
    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }
    
    @Override
    public long countAllWorkouts() {
        return workoutRepository.countAllWorkouts();
    }
}