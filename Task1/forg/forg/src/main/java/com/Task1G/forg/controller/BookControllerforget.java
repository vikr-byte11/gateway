package com.Task1G.forg.controller;

import com.Task1G.forg.model.Book;
import com.Task1G.forg.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookControllerforget {
    @Autowired
    BookRepository br;
    @GetMapping("/allbooks")
    public List<Book> getbook(@RequestParam String region, @RequestParam String rights,
                              @RequestParam String country,@RequestParam String serverid)
    {
        String Region=region;
        String Rights=rights;
        String Country=country;
        String Sid=serverid;
        //can use up these variables to authenticate
        System.out.println("Region: "+region);
        System.out.println("rights: "+Rights);

        System.out.println("country: "+Country);

        System.out.println("serverid: "+Sid);

        return (List<Book>) br.findAll();
    }

}
