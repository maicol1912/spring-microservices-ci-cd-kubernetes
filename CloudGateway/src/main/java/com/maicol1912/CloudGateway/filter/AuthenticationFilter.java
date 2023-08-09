package com.maicol1912.CloudGateway.filter;

import com.maicol1912.CloudGateway.exception.CustomException;
import com.maicol1912.CloudGateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JwtUtil jwtUtil;
    public AuthenticationFilter(){
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if(validator.isSecured.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new CustomException("Unauthorized access to the application","NOT AUTHORIZED",401);
                }
                String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeaders!=null && authHeaders.startsWith("Bearer ")){
                    authHeaders = authHeaders.substring(7);
                }
                try{
                    //restTemplate.getForObject("http://IDENTITY-SERVICE//validate?token="+authHeaders,String.class);
                    jwtUtil.validateToken(authHeaders);
                }catch (Exception e){
                    System.out.println("invalid access...");
                    throw new CustomException("Unauthorized access to the application","NOT AUTHORIZED",401);
                }
            }
           return chain.filter(exchange);
        });
    }

    public static class Config{

    }
}
