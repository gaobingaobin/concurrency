package com.example.concurrency;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Description
 * @Author gaobin
 * @Date 2022-02-24 10:35
 */
public class Test {
    public static void main(String[] args) {
        String  str = "[{\"widget_content_id\":\"97703\",\"id\":403387,\"weight\":21,\"engine\":\"4\",\"model\":\"5\",\"name\":\"The Law of Revenge EP04 Part1\",\"type\":1,\"description\":\"Marissa is pregnant with Gabriel's child. Marissa plans to announce the news in public\",\"poster\":\"http://static.startimestv.com/static/files/production/poster/2022/2/918516.jpg\",\"episode\":4,\"paragraph\":1,\"duration_second\":1330,\"program_detail_id\":13176,\"billing_type\":2,\"support_download\":true,\"source_type\":\"FILE\",\"program_form\":\"TV_PLAY\",\"vv_count\":62132},{\"widget_content_id\":\"151589\",\"id\":402999,\"weight\":22,\"engine\":\"4\",\"model\":\"5\",\"name\":\"The Legal Wife EP24 Part1\",\"type\":1,\"description\":\"Monica reconciling with Adrian\",\"poster\":\"http://static.startimestv.com/static/files/production/poster/2022/2/915536.jpg\",\"episode\":24,\"paragraph\":1,\"duration_second\":1370,\"program_detail_id\":12928,\"billing_type\":2,\"support_download\":true,\"source_type\":\"FILE\",\"program_form\":\"TV_PLAY\",\"vv_count\":56156},{\"widget_content_id\":\"108672\",\"id\":404115,\"weight\":23,\"engine\":\"4\",\"model\":\"5\",\"name\":\"New Beginnings EP28 Part1\",\"type\":1,\"description\":\"Tisay and Ely desperately look for Cai\",\"poster\":\"http://static.startimestv.com/static/files/production/poster/2022/2/918570.jpg\",\"episode\":28,\"paragraph\":1,\"duration_second\":1321,\"program_detail_id\":12826,\"operation_label\":\"Full Episode\",\"billing_type\":2,\"support_download\":true,\"source_type\":\"FILE\",\"program_form\":\"TV_PLAY\",\"vv_count\":70262},{\"widget_content_id\":\"86487\",\"id\":402743,\"weight\":24,\"engine\":\"4\",\"model\":\"5\",\"name\":\"Swaragini EP482\",\"type\":1,\"description\":\"Mansi and Nikhil enter maheshwari house with goons\",\"poster\":\"http://static.startimestv.com/static/files/production/poster/2022/2/918287.jpg\",\"episode\":482,\"duration_second\":852,\"program_detail_id\":11463,\"operation_label\":\"Full Episode\",\"billing_type\":2,\"support_download\":true,\"source_type\":\"FILE\",\"program_form\":\"TV_PLAY\",\"vv_count\":10176},{\"widget_content_id\":\"149847\",\"id\":380758,\"weight\":25,\"name\":\"Love Thy Woman EP23 Part2\",\"type\":1,\"description\":\"Dana questions Lucy about Michael\",\"poster\":\"http://static.startimestv.com/static/files/production/poster/2021/12/868829.jpg\",\"episode\":23,\"paragraph\":2,\"duration_second\":1415,\"program_detail_id\":12521,\"operation_label\":\"Full Episode\",\"billing_type\":2,\"support_download\":true,\"source_type\":\"FILE\",\"program_form\":\"TV_PLAY\",\"vv_count\":226081}]";
        JSONArray object = JSON.parseArray(str);
        System.out.println(object);

    }
}
