package Kz.bitlab.SpringApplication.controlers;
import org.springframework.ui.Model;
import Kz.bitlab.SpringApplication.db.DBManager;
import Kz.bitlab.SpringApplication.models.Item;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import Kz.bitlab.SpringApplication.db.DBManager;
import java.util.List;
import java.util.ArrayList;
@Controller
public class HomeController {
    @GetMapping("/")  // @WebServlet(vallue='/') + do get()
public String homePage(Model model){

        List <Item> items = DBManager.getItems();
        model.addAttribute("items", items);   //req.setAtribute("items")
    return "home";      //req.getRequestDispatcher("home")
    }

    @GetMapping("/second")  // @WebServlet(vallue='/') + do get()
    public String secondPage(){
        return "second";
    }
}
