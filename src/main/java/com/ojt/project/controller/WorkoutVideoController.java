package com.ojt.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ojt.project.entity.WorkoutVideo;
import com.ojt.project.service.WorkoutVideoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workouts")
public class WorkoutVideoController {

    private final WorkoutVideoService workoutService;

    @Autowired
    public WorkoutVideoController(WorkoutVideoService workoutService) {
        this.workoutService = workoutService;
    }

    // Fetch all workouts (GET request)
    @GetMapping
    public ResponseEntity<List<WorkoutVideo>> getAllWorkouts() {
        List<WorkoutVideo> workouts = workoutService.getAllWorkouts();
        return new ResponseEntity<>(workouts, HttpStatus.OK);
    }

    // Fetch workout by category and type (GET request)
    @GetMapping("/workoutdetails")
    public ResponseEntity<List<WorkoutVideo>> getWorkoutsByCategoryAndType(
            @RequestParam String workoutCategory,
            @RequestParam String workoutType) {
        List<WorkoutVideo> workouts = workoutService.getWorkoutsByCategoryAndType(workoutCategory, workoutType);
        if (workouts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(workouts, HttpStatus.OK);
    }

    // Add new workout (POST request)
    @PostMapping("/add")
    public ResponseEntity<WorkoutVideo> addWorkout(@RequestBody WorkoutVideo workoutVideo) {
        try {
            WorkoutVideo savedWorkout = workoutService.saveWorkout(workoutVideo);
            return new ResponseEntity<>(savedWorkout, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

 
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWorkout(@PathVariable Long id) {
        WorkoutVideo workoutVideo = workoutService.getWorkoutById(id);
        if (workoutVideo != null) {
            workoutService.deleteWorkout(id);
            return ResponseEntity.ok("Workout deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout not found.");
        }
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> getWorkoutCount() {
        long workoutCount = workoutService.countAllWorkouts();
        return new ResponseEntity<>(workoutCount, HttpStatus.OK);
    }
}
