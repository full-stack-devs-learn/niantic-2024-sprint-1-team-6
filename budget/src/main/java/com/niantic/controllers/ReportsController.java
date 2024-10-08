package com.niantic.controllers;

import com.niantic.models.Category;
import com.niantic.models.Transaction;
import com.niantic.models.User;
import com.niantic.models.Vendor;
import com.niantic.services.CategoryDao;
import com.niantic.services.TransactionDao;
import com.niantic.services.UserDao;
import com.niantic.services.VendorDao;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ReportsController {

    private TransactionDao transactionDao = new TransactionDao();
    private CategoryDao categoryDao = new CategoryDao();
    private UserDao userDao = new UserDao();
    private VendorDao vendorDao = new VendorDao();

    @GetMapping("/report/category")
    public String getAllCategories(Model model)
    {
        ArrayList<Category> categories = categoryDao.getAllCategories();


        model.addAttribute("categories", categories);
        model.addAttribute("pageTitle", "Report - Category Menu");


        return "reports/category_menu";
    }



    @GetMapping("/report/category/{id}")
    public String reportByCategory(Model model, @PathVariable int id)
    {
        ArrayList<Transaction> transactions = transactionDao.getTransactionByCategory(id);
        Category category = categoryDao.getCategoryById(id);
        ArrayList<Vendor> vendors = vendorDao.getAllVendors();
        ArrayList<User> users  = userDao.getAllUsers();



        // Making Header with String Format
        String catName = category.getCategoryName();
        String message = String.format("Report by Category: %s", catName);

        model.addAttribute("transactions", transactions);
        model.addAttribute("category", category);
        model.addAttribute("message", message);
        model.addAttribute("vendors", vendors);
        model.addAttribute("users", users);
        model.addAttribute("action", "category");
        model.addAttribute("categoryName", catName);
        model.addAttribute("pageTitle", String.format("Report by Category - %s", catName));


        return "reports/reports";
    }

    @GetMapping("/report/user")
    public String getAllUsers(Model model)
    {
        ArrayList<User> users = userDao.getAllUsers();

        model.addAttribute("users", users);
        model.addAttribute("pageTitle", "Report - User Menu");

        return "reports/user_menu";
    }

    @GetMapping("/report/user/{id}")
    public String reportByUser(Model model, @PathVariable int id)
    {
        ArrayList<Transaction> transactions = transactionDao.getTransactionByUser(id);
        ArrayList<Vendor> vendors = vendorDao.getAllVendors();
        ArrayList<Category> categories = categoryDao.getAllCategories();
        User user = userDao.getUserById(id);

        // Making Header with String Format
        String useName = user.getUserName();
        String message = String.format("Report by User: %s", useName);

        model.addAttribute("transactions", transactions);
        model.addAttribute("user", user);
        model.addAttribute("message", message);
        model.addAttribute("vendors", vendors);
        model.addAttribute("categories", categories);
        model.addAttribute("action", "user");
        model.addAttribute("userName", useName);
        model.addAttribute("pageTitle", String.format("Report by User - %s", useName));

        return "reports/reports";
    }

    @GetMapping("/report/month")
    public String getAllMonths(Model model)
    {

        model.addAttribute("pageTitle", "Report - Month Menu");
        return "reports/month_menu";
    }

    @GetMapping("/report/month/{monthNum}")
    public String reportByMonth(Model model, @PathVariable int monthNum)
    {
        ArrayList<Transaction> transactions = transactionDao.getTransactionByMonth(monthNum);
        ArrayList<Vendor> vendors = vendorDao.getAllVendors();
        ArrayList<User> users = userDao.getAllUsers();
        ArrayList<Category> categories = categoryDao.getAllCategories();
        String monthName = "";

        switch (monthNum)
        {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }

        // Making Header with String Format
        String message = String.format("Report by Month: %s", monthName);

        model.addAttribute("transactions", transactions);
        model.addAttribute("message", message);
        model.addAttribute("vendors", vendors);
        model.addAttribute("users", users);
        model.addAttribute("categories", categories);
        model.addAttribute("action", "month");
        model.addAttribute("pageTitle", String.format("Report by Month - %s", monthName));

        return "reports/reports";
    }

    @GetMapping("/report/year")
    public String reportAllYears(Model model)
    {
        ArrayList<Integer> year = transactionDao.getYearlyTransaction();

        model.addAttribute("years", year);
        model.addAttribute("pageTitle", "Report - Year Menu");


        return "/reports/year_menu";
    }

    @GetMapping("/report/year/{year}")
    public String reportByYear(Model model, @PathVariable int year)
    {
        ArrayList<Transaction> transactions = transactionDao.getTransactionByYear(year);
        ArrayList<Vendor> vendors = vendorDao.getAllVendors();
        ArrayList<User> users = userDao.getAllUsers();
        ArrayList<Category> categories = categoryDao.getAllCategories();

        //add header stuff
        String message = String.format(" Reports by Year %d", year);

        model.addAttribute("transactions", transactions);
        model.addAttribute("message", message);
        model.addAttribute("vendors", vendors);
        model.addAttribute("users", users);
        model.addAttribute("categories", categories);
        model.addAttribute("action", "year");
        model.addAttribute("pageTitle", String.format("Report by Year - %d", year));

        return "reports/reports";

    }



}
