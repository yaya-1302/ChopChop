package com.enigma.ChopChop.repository;

import com.enigma.ChopChop.entity.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {

    @Query(value = "SELECT * FROM meal_plans WHERE user_id = :userId", nativeQuery = true)
    List<MealPlan> findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM meal_plans WHERE date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<MealPlan> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Modifying
    @Query(value = "DELETE FROM meal_plans WHERE user_id = :userId", nativeQuery = true)
    void deleteByUserId(@Param("userId") Long userId);
}
