package ufu.piloto.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ufu.piloto.model.review.Review;

import java.util.Date;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class User {
    private final String id;
    private String name;
    private final String email;
    private String password;
    private final Date createdDate;
    private Date updatedDate;
    private List<Review> reviews;
}
