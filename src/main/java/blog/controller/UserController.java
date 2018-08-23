package blog.controller;
import blog.forms.RegisterNewUser;
import blog.model.Post;
import blog.model.User;
import blog.services.PostService;
import blog.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserServiceImp userServiceImp;

    @RequestMapping("/users/login")
    private String loginPage(User user){

        return "users/login";
    }

    @RequestMapping(value="/users/login" , method= RequestMethod.POST)
    private String login(RegisterNewUser user, Model model) {

        System.out.print(user.getUsername());
        System.out.print(user.getPassword());

        if(userServiceImp.authenticate(user.getUsername(),user.getPassword())) {
            return "redirect:/posts";
        }
        return "redirect:/";
    }

    @RequestMapping("/users/logout")
    private String logOut(Model model) {

        List<Post> list = postService.firstThreePosts();
        model.addAttribute("posts",list);
        return "index";
    }

    @RequestMapping("/users/register")
    private String register(RegisterNewUser registerNewUser) {
        return "users/register";
    }

    @RequestMapping(value = "/users/register",method = RequestMethod.POST)
    public String registerUser(RegisterNewUser registerNewUser){
        User user = new User(registerNewUser.getUsername(),
                registerNewUser.getFullName());
        String sha256hex = "kjn krjgnv r";
       /* sha256hex = Hashing.sha256()
                .hashString(registerNewUser.getPassword(), StandardCharsets.UTF_8)
                .toString();
        user.setPasswordHash(sha256hex);*/
        userServiceImp.registerNewUser(user);
        return "redirect:/";
    }
}
