package com.ojt.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ojt.project.entity.WorkoutVideo;

import java.util.List;

@Repository
public interface WorkoutVideoRepository extends JpaRepository<WorkoutVideo, Long> {
	@Query("SELECT COUNT(w) FROM WorkoutVideo w")
	long countAllWorkouts();
    @Query("SELECT w FROM WorkoutVideo w WHERE w.workoutCategory = :workoutCategory AND w.workoutType = :workoutType")
    List<WorkoutVideo> findByWorkoutCategoryAndWorkoutType(@Param("workoutCategory") String workoutCategory, @Param("workoutType") String workoutType);
}