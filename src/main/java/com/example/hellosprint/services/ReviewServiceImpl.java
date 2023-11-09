package com.example.hellosprint.services;

import com.example.hellosprint.data.ReviewRequest;
import com.example.hellosprint.models.Product;
import com.example.hellosprint.models.Review;
import com.example.hellosprint.models.User;
import com.example.hellosprint.repository.ProductRepository;
import com.example.hellosprint.repository.ReviewRepository;
import com.example.hellosprint.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    ReviewRepository reviewRepository;
    UserRepository userRepository;
    ProductRepository productRepository;
    @Override
    public Review addReview(ReviewRequest request, Long productId, Long userId) throws Exception {
        Review review = new Review();
        review.setRating(request.getRating());
        review.setReview(request.getComment());
        User user = userRepository.findById(userId).orElseThrow(Exception::new);
        review.setUser(user);
        Product product = productRepository.findById(productId).orElseThrow(Exception::new);
        review.setProduct(product);

        return reviewRepository.save(review);
    }
}
