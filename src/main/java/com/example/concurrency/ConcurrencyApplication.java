package com.example.concurrency;


import com.example.concurrency.model.LibraryProperties;
import com.example.concurrency.model.ProfileProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConcurrencyApplication implements InitializingBean {
    private final LibraryProperties library;

    private final ProfileProperties profileProperties;

    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyApplication.class, args);
    }



    public ConcurrencyApplication(LibraryProperties library, ProfileProperties profileProperties) {
        this.library = library;
        this.profileProperties = profileProperties;
    }



    @Override
    public void afterPropertiesSet() {
        System.out.println(library.getLocation());
        System.out.println(library.getBooks());
        System.out.println(profileProperties.getEmail());
    }

}
