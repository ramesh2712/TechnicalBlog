package blog.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")

public class User implements Serializable {

    @Id
    @Column
    private String userName;

    @Column
    private String passwordHash;

    @Column
    private String fullName;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER )
    private  ProfilePhoto profilePhoto;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY,mappedBy = "user")
    private List<Post> posts = new ArrayList<Post>();

    public User(String userName, String fullName) {
        this.userName = userName;
        this.fullName = fullName;
    }

    public User() {
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ProfilePhoto getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(ProfilePhoto profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
