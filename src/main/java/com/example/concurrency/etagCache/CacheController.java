package com.example.concurrency.etagCache;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author gaobin
 * @Date 2022-05-01 10:52
 */
@RestController
@RequestMapping("/cache/")
public class CacheController {

    @RequestMapping("test")
    public ResponseEntity<String> last(@RequestHeader(value = "If-None-Match",required = false) String ifNoneMatch){

        System.out.println("If-None-Match:"+ifNoneMatch);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy HH:mm:ss");

        HttpHeaders httpHeaders = new HttpHeaders();
        String body = "tesdfsdfsdfsdfsdfsaaaa";
        if(!StringUtils.isEmpty(ifNoneMatch) && body.equals(ifNoneMatch.replace("\"",""))){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }else{
            httpHeaders.add("Date",simpleDateFormat.format(new Date()));
            httpHeaders.add("ETag",body);
            return new ResponseEntity<>(body,httpHeaders, HttpStatus.OK);
        }






    }



}
