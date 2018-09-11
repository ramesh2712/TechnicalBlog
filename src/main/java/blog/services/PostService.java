package blog.services;

import blog.model.Post;
import javafx.geometry.Pos;

import java.util.List;

public interface PostService {

    Post create(Post post);
    List<Post> findAll();
    List<Post> firstThreePosts();
    Post findByTitle(String title);
    Post editPost(Post post);
    void deleteById(Post post);
}
