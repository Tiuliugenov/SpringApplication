package Kz.bitlab.SpringApplication.controlers;
import Kz.bitlab.SpringApplication.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import Kz.bitlab.SpringApplication.db.DBManager;
import Kz.bitlab.SpringApplication.models.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping(value = "/")
class HomeController {
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/home")  // @WebServlet(vallue='/') + do get()
    public String homePage(Model model) {
        List<Item> items = DBManager.getItems();
        model.addAttribute("items", items);   //req.setAtribute("items")
        return "home";      //req.getRequestDispatcher("home")
    }
    @Autowired
    private MyUserService myUserService;

@PreAuthorize("isAuthenticated()")
    @GetMapping("/second")  // @WebServlet(vallue='/') + do get()
    public String secondPage() {
        return "second";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/additem")  //adding item
    public String showAddForm(Model model) {
        model.addAttribute("item", new Item());
        return "additem";
    }

    @PostMapping("/additem")
    public String addItem(String name,
                          String description,
                          double price) {
        Item item = new Item(null, name, description,price);
        DBManager.addItem(item);
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/details/{id}")

    public String detailsItem(@PathVariable Long id, Model model) {
        Item item = DBManager.getItemById(id);
        if (item == null) {
            return "/";
        }
        model.addAttribute("item", item);

        if (item != null) {
            return "details";

        }return null;
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/login")
    public String openLogin() {
return "login";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String openProfile () {
        return "profile";
    }
   @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin-panel")
    public String openAdminPage (){
        return "admin-panel";
    }
    @GetMapping(value = "/403")
    public String OpenPage403 (){
        return "403";
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/registration")
    public String OpenRegistration () {
        return "registration";
    }
    @PostMapping(value = "sign-up")
    public String signUpPost (@RequestParam(name="user-email") String email,
    @RequestParam(name="user-fullname") String fullName,
    @RequestParam(name="user-password") String password,
                              @RequestParam(name="user-repassword") String repassword) {
    String check = myUserService.registerUser(email, fullName, password, repassword);
        if (check.equals("userExist")) {
            return "redirect:registration?userExist";
        } else if (check.equals("passwordNotMatch")) {
            return "redirect:registration?passwordNotMatch";
        } else {
            return "redirect:login";
        }
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping ("/change-password")
    public String openChangePassword (){
    return "change-password";
    }

@PostMapping ("/change-password")
    public String changePasswordPost(@RequestParam(name = "user-id") Long userId,
                                     @RequestParam(name = "old-password") String oldPassword,
                                     @RequestParam(name = "new-password") String newPassword,
                                     @RequestParam(name = "renew-password") String renewPassword
                                     ){
    String check = myUserService.changePassword(userId, oldPassword, newPassword, renewPassword);
    if (check.equals("oldPasswordIncorrect")){
        return "redirect:change-password?oldPasswordIncorrect";
    }  else if (check.equals("newPasswordNotMatch")) {
    return "redirect:change-password?newPasswordNotMatch";
    }else {
        return "redirect:change-password?passwordChangedSuccess";
    }
}
}
