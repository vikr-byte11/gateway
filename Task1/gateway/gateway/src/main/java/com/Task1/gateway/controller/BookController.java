package com.Task1.gateway.controller;


import com.Task1.gateway.YAMLConfig;
import com.Task1.gateway.model.Book;
import com.Task1.gateway.repo.BookRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@Service
@ConfigurationProperties(prefix = "available")
public class BookController {

    @Autowired
    private YAMLConfig myconfig;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/save")
    public String insertBook(@RequestParam @NotNull Map<String, String> reqParam){
        String ans;
        String check="post";
        String callingUrl="";
        String checkStatus="active";
        String type=reqParam.get("Type");
        String Postparam="";
        String body = "";
        String serverid="";
        for(YAMLConfig.Service obj:myconfig.getServices()){
            String currMethod= obj.getMethod();
            String currStatus=obj.getStatus();
            String currType=obj.getType();
            //check if current server of the list is the required server
            //by checking its method status and type
         if(currMethod.equals(check) && currStatus.equals(checkStatus)&& currType.equals(type))
            {
                callingUrl=obj.getUrl();
                body= obj.getBody();
                serverid= obj.getServerId();
                Postparam= obj.getPostParam();
                break;
            }
        }
        if(callingUrl.isEmpty())
        {
            return "Not able to serve the request currently";
        }
        String region="",country="",rights="";
        int idx=0;
        Character sep=':';
        for(int i=0;i<body.length();i++)
        {
            String res="";
            if(body.charAt(i)==sep)
            {
                 //create rest
                i++;
                while(i<body.length() && body.charAt(i)!=',')
                {
                    res+=body.charAt(i);
                    i++;
                }
                if(idx==0)
                {
                    region=res;
                    idx=1;
                }
                else if(idx==1) {
                    country = res;
                    idx=2;
                }
                else{
                    rights=res;
                    idx=0;
                }
                i--;
            }
        }
        List<String>param=new ArrayList<>();
        //separate the post params;
        for(int i=0;i<Postparam.length();i++)
        {
            String tmp="";
            while(i<Postparam.length() &&Postparam.charAt(i)!=',')
            {
                tmp+=Postparam.charAt(i);
                i++;
            }
            param.add(tmp);
        }
        String Region=reqParam.get("Region");
        region=Region;
        //now for every param generate its key
        String restofUrl="";
        //getting the values of required post param from the map
        for(int i=0;i<param.size();i++)
        {
            //bookname=abc,bookauthor=a12
            //param bookname,bookauthor
            String x=param.get(i);
            String pp=reqParam.get(x);
            //it should have been part of paylod
            restofUrl=restofUrl.concat(x+"="+pp+"&");
        }
        //making and calling the url
        int n=restofUrl.length();
        String rou=restofUrl.substring(0,n-1);
        String newUrl=callingUrl+"?"+rou+"&region="+region+"&country="+country+"&rights="+rights+"&serverid="+serverid;
        Book book =new Book();
        book.setBookName("dummy book");
        book.setBookAuthor("dummy author");
        Object obj=new Object();
        ans = restTemplate.postForObject(newUrl,book,String.class);
        return ans;
    }
    class server
    {
        String url;
        String body;
        String serverid;

        public String getServerid() {
            return serverid;
        }

        public void setServerid(String serverid) {
            this.serverid = serverid;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
    @GetMapping("/get")
    public List<Object> getBook(@RequestParam String Region)
    {
           String check="get";
           String callingUrl="";
           String tbody = "";
           String serverid="";
        String checkStatus="active";
        List<server>serversOfGet = new ArrayList<server>();//to store all servers of get method
            for(YAMLConfig.Service obj:myconfig.getServices()){
                String currMethod= obj.getMethod();
                String currStatus=obj.getStatus();
                if(currMethod.equals(check) && currStatus.equals(checkStatus))
                {
                    
                    callingUrl=obj.getUrl();
                    tbody= obj.getBody();
                    serverid= obj.getServerId();
                    server s =new server();
                     s.setServerid(serverid);
                     s.setUrl(callingUrl);
                     s.setBody(tbody);
                     serversOfGet.add(s);
                }
            }

            if(serversOfGet.size()==0)
            {
                List<Object>tans=new ArrayList<>();
                Book book= new Book();
                book.setBookAuthor("Not able to serve the request currently");
                book.setBookName("Not able to serve the request currently");
                tans.add(book);
                return tans;
            }
            //now we have all the servers with get request
        //now make url and get the lists of the response by each server
        List<Object>ans=new ArrayList<>();
            for(int ti=0;ti<serversOfGet.size();ti++) {
                //looping
                //obj1->{url,body,serverid}
                String body= serversOfGet.get(ti).getBody();//get body part
                String rcal=serversOfGet.get(ti).getUrl();//get url

                String region = "", country = "", rights = "";
                int idx = 0;
                Character sep = ':';
                //extracting the parameters from body part
                for (int i = 0; i < body.length(); i++) {
                    String res = "";
                    if (body.charAt(i) == sep) {
                        //create rest
                        i++;
                        while (i < body.length() && body.charAt(i) != ',') {
                            res += body.charAt(i);
                            i++;
                        }
                        if (idx == 0) {
                            region = res;
                            idx = 1;
                        } else if (idx == 1) {
                            country = res;
                            idx = 2;
                        } else {
                            rights = res;
                            idx = 0;
                        }
                        i--;
                    }
                }
                region = Region;


                String newUrl = rcal + "?region=" + Region + "&rights=" + rights + "&country=" + country + "&serverid=" + serverid;
                System.out.println("new url : "+rcal);
                Object[] acall = restTemplate.getForObject(newUrl, Object[].class);
                //got the response from the server
                //now save the in the answer list
                for(Object o:acall)
                    ans.add(o);
            }
        return ans;
    }
}
