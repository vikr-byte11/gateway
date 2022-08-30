package com.Task1P.forpost.controller;


import com.Task1P.forpost.model.Book;
import com.Task1P.forpost.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class BookController {

    @Autowired
    BookRepository br;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveb(@RequestParam String bookName,@RequestParam String bookAuthor,
                        @RequestParam String region, @RequestParam String rights,
                        @RequestParam String country,@RequestParam String serverid){
        String Region=region;
        String Rights=rights;
        String Country=country;
        String Sid=serverid;
        System.out.println("Region: "+region);
        System.out.println("rights: "+Rights);

        System.out.println("country: "+Country);

        System.out.println("serverid: "+Sid);
        //can use up these variables to authenticate
        Book book=new Book();
        book.setBookAuthor(bookAuthor);
        book.setBookName(bookName);
        br.save(book);
        return "Your record is saved :)";
    }

}
