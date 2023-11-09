package com.example.hellosprint.services;

import com.example.hellosprint.data.ReviewRequest;
import com.example.hellosprint.models.Review;

public interface ReviewService {
    Review addReview(ReviewRequest request, Long productId, Long userId) throws Exception;
}
