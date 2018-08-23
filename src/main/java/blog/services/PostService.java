package blog.services;

import blog.model.Post;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.List;

public interface PostService {

    Post create(Post post);
    List<Post> findAll();
    List<Post> firstThreePosts();
    Post findByTitle(String title);
    Post editPost(Post post);
    void deleteById(Post post);

}
