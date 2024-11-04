package ufu.piloto.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ufu.piloto.model.review.Review;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private final String email;
    private String password;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private List<Review> reviews;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String id, String name, String email, String password, LocalDate createdDate, LocalDate updatedDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.reviews = new ArrayList<>();
    }
}
