package blog.common;

import blog.model.Post;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class PostsManager {

    private FileOperations<Post> fileOperations;

    public PostsManager() {
        fileOperations = FileOperations.getInstance();
    }

    public List<Post> readAllPosts() {

        return (ArrayList<Post>) fileOperations.readAllFiles(Constants.POST_DIR_NAME);
    }

    public List<Post> getThreePosts() {

        return (ArrayList<Post>) fileOperations.readRecentFiles(3, Constants.POST_DIR_NAME);
    }

    public static int numberOfPosts() {

        File file = new File(Constants.POST_DIR_NAME);
        File[] files = file.listFiles();
        return files.length;
    }

    public boolean deletePost(final String postTitle) {

        return (boolean) fileOperations.deleteFile(Constants.POST_FILE_PREFIX, postTitle);
    }

    public static void main(String[] args) {
        PostsManager postsManager= new PostsManager();
        Post post = new Post();
        post.setBody(" JDBC Description");
        post.setTitle("Test JDBC");
        post.setDate(new Date());

        postsManager.writeToFile(post);

    }
    public Post writeToFile(final Post post) {

        JDBCConnector jdbcConnector = JDBCConnector.getInstance();

        // "create table Posts (id SERIAL PRIMARY KEY, title varchar, body varchar, body timestamp)";

        String query = "insert into Posts(title,body,date) values( \'" + post.getTitle() + "\' , \'" + post.getBody() + "\' , \'03-03-2017\')";

        jdbcConnector.execute(query);
        return null;
    }

    public Post getPost(final String prefix) {
        return (Post) fileOperations.readFile(Constants.POST_FILE_PREFIX, prefix);
    }
}
