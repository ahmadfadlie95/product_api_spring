package com.example.hellosprint.repository;

import com.example.hellosprint.models.Review;
import com.example.hellosprint.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ReviewRepository extends JpaRepository <Review, Long> {
    Optional<Review> findByReview(Integer rating);

    User findByReview(User user);
}
