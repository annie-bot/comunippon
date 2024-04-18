package com.comunippon.ComuNippon.service;

import com.comunippon.ComuNippon.model.Review;
import com.comunippon.ComuNippon.model.Title;
import com.comunippon.ComuNippon.repository.ReviewRepository;
import com.comunippon.ComuNippon.repository.TitleListRepository;
import com.comunippon.ComuNippon.repository.TitleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class TitleService {
    private final ReviewService reviewService;
    private final TitleListRepository titleListRepository;

    private final TitleRepository repository;

    public void createTitle(Title titleDTO) {
        Title title = new Title();
        title.setName(titleDTO.getName());
        title.setSynopsis(titleDTO.getSynopsis());
        title.setAverageRating(titleDTO.getAverageRating());
        repository.save(title);
    }

    public List<Title> listTitle() {
        return repository.findAll();
    }

    public Title getTitleById(Long id) {
        return repository.findById(id).get();
    }

    public Title deleteTitle(Long id) {
        Title title = getTitleById(id);
        repository.deleteById(id);
        return title;
    }

    public Title updateTitle(Long id, Title titleDTO) {
        Title title = getTitleById(id);
        title.setName(titleDTO.getName());
        title.setSynopsis(titleDTO.getSynopsis());
        title.setAverageRating(titleDTO.getAverageRating());

        return repository.save(title);
    }

    public List<Review> getReviewByTitle(Long id) {
        Title title = getTitleById(id);
        return title.getReviews();
    }

    public void postReview(Long titleId, Review review) {
        Title title = getTitleById(titleId);
        title.getReviews().add(review);
        repository.save(title);
    }

    public Review updateReview(Long titleId, Review reviewDTO) throws Exception {
        Title title = getTitleById(titleId);
        Review reviewAux = null;
        for (int i = 0; i < title.getReviews().size(); i++) {
            if (title.getReviews().get(i).getId() == reviewDTO.getId()) {
                reviewAux = title.getReviews().get(i);
            }
        }
        if (Objects.isNull(reviewAux)) {
            throw new Exception("Resenha não encontrada");
        }
        repository.save(title);
        return reviewService.updateReview(reviewAux.getId(), reviewDTO);
    }

    public void deleteReview(Long titleId, Long reviewId) {
        Title title = getTitleById(titleId);
        for (int i = 0; i < title.getReviews().size(); i++) {
            if (title.getReviews().get(i).getId() == reviewId) {
                title.getReviews().remove(i);
            }
        }
        repository.save(title);
    }


}
