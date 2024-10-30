package ufu.piloto.model.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ufu.piloto.model.series.Series;
import ufu.piloto.model.user.User;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
public class Review {
    private final String id;
    private double rating;
    private String comment;
    private final Date createdReviewDate;
    private Date updatedReviewDate;
    private final User user;
    private final Series series;
}









