package Kz.bitlab.SpringApplication.controlers;
import org.springframework.ui.Model;
import Kz.bitlab.SpringApplication.db.DBManager;
import Kz.bitlab.SpringApplication.models.Item;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")  // @WebServlet(vallue='/') + do get()
    public String homePage(Model model) {
        List<Item> items = DBManager.getItems();
        model.addAttribute("items", items);   //req.setAtribute("items")
        return "home";      //req.getRequestDispatcher("home")
    }

    @GetMapping("/second")  // @WebServlet(vallue='/') + do get()
    public String secondPage() {
        return "second";
    }

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
        return "home";
    }

    @GetMapping("/details/{id}")

    public String detailsItem(@PathVariable Long id, Model model) {
        Item item = DBManager.getItemById(id);
        if(item==null) {
            return "/";
        }model.addAttribute("item", item);
        return "details";
        }
    }


