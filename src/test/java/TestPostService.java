import blog.model.Post;
import blog.services.PostService;
import blog.services.PostServiceImp;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

public class TestPostService {

    @Test
    public void testCreatePost(){

        PostService postService = new PostServiceImp();
        Post post = new Post();
        post.setTitle("test1");
        post.setBody("This is part of unit test case");
        postService.create(post);
        Post postUsingGetOperation = postService.findByTitle("test1");
        Assert.assertEquals(postUsingGetOperation.getTitle(),post.getTitle());
        Assert.assertEquals(postUsingGetOperation.getBody(),post.getBody());
    }
}
