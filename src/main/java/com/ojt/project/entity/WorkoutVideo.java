package com.ojt.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "workout")
public class WorkoutVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private Long workoutId;

    @Column(name = "workout_category", nullable = false)
    private String workoutCategory;

    @Column(name = "workout_type", nullable = false)
    private String workoutType;

    @Column(name = "workout_name", nullable = false)
    private String workoutName;

    @Column(name = "instructions", columnDefinition = "TEXT")
    private String instructions;

    @Column(name = "focus_area")
    private String focusArea;

    @Column(name = "common_mistakes")
    private String commonMistakes;

    @Column(name = "breathing_tips")
    private String breathingTips;

    @Column(name = "animation_url")
    private String animationUrl;

    @Column(name = "muscles_url")
    private String musclesUrl;

    @Column(name = "how_to_do_url")
    private String howToDoUrl;

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public String getWorkoutCategory() {
        return workoutCategory;
    }

    public void setWorkoutCategory(String workoutCategory) {
        this.workoutCategory = workoutCategory;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getFocusArea() {
        return focusArea;
    }

    public void setFocusArea(String focusArea) {
        this.focusArea = focusArea;
    }

    public String getCommonMistakes() {
        return commonMistakes;
    }

    public void setCommonMistakes(String commonMistakes) {
        this.commonMistakes = commonMistakes;
    }

    public String getBreathingTips() {
        return breathingTips;
    }

    public void setBreathingTips(String breathingTips) {
        this.breathingTips = breathingTips;
    }

    public String getAnimationUrl() {
        return animationUrl;
    }

    public void setAnimationUrl(String animationUrl) {
        this.animationUrl = animationUrl;
    }

    public String getMusclesUrl() {
        return musclesUrl;
    }

    public void setMusclesUrl(String musclesUrl) {
        this.musclesUrl = musclesUrl;
    }

    public String getHowToDoUrl() {
        return howToDoUrl;
    }

    public void setHowToDoUrl(String howToDoUrl) {
        this.howToDoUrl = howToDoUrl;
    }

}
