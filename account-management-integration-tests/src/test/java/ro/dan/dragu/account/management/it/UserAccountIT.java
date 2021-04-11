package ro.dan.dragu.account.management.it;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponentsBuilder;
import ro.dan.dragu.account.management.web.Application;


import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAccountIT {

    @LocalServerPort
    private int port;

    @Test
    public void testCreateAccountAndListTransactions(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity("{\"name\":  \"user3\", \"fullName\" : \"User 3\", \"email\" : \"user3@testmail.com\", \"password\" : \"password3\" }", headers);
        ResponseEntity<String> response  =   testRestTemplate.postForEntity("http://localhost:"+ port +"/account/management/user/create", request, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        UriComponentsBuilder   builder = UriComponentsBuilder.fromHttpUrl("http://localhost:"+ port +"/account/management/user/listTransactions?")
                .queryParam("timeFrame", 1)
                .queryParam("timeUnit", "HOURS");
        testRestTemplate = new TestRestTemplate("user3", "password3");
        response = testRestTemplate.getForEntity(builder.build().encode().toUri(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreateAccountAndDeleteAccountAndListTransactions(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity("{\"name\":  \"user4\", \"fullName\" : \"User 4\", \"email\" : \"user3@testmail.com\", \"password\" : \"password4\" }", headers);
        ResponseEntity<String> response  = testRestTemplate.postForEntity("http://localhost:"+ port +"/account/management/user/create", request, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        testRestTemplate = new TestRestTemplate("user4", "password4");
        testRestTemplate.delete("http://localhost:"+ port +"/account/management/user/delete");

        UriComponentsBuilder   builder = UriComponentsBuilder.fromHttpUrl("http://localhost:"+ port +"/account/management/user/listTransactions?")
                .queryParam("timeFrame", 1)
                .queryParam("timeUnit", "HOURS");
        response = testRestTemplate.getForEntity(builder.build().encode().toUri(), String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}
