package com.example.middleware;

import com.example.authentication.ClaimsResponse;
import com.example.authentication.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class SecurityConfig {

    private final String PROTOCOL;
    private final String HOST;
    private final String PORT;

    private List<String> combinedRoutes;

    private JwtUtil jwtUtil;

    private RouteResolveService routeResolveService;
    private Routes routes;
    @Enumerated(EnumType.STRING)
    private Prefixes ROUTE_PREFIX;

    public SecurityConfig()
    {
        this.HOST = "localhost";
        this.PORT = ":8080";
        this.PROTOCOL = "http://";

        //Routes
        List<String> adminRoutes =
                Arrays.asList(
                        this.PROTOCOL+this.HOST+this.PORT+Prefixes.admin.toString()+"/dashboard",
                        this.PROTOCOL+this.HOST+this.PORT+Prefixes.admin.toString()+"/users",
                        this.PROTOCOL+this.HOST+this.PORT+Prefixes.admin.toString()+"/products");


        List<String> clientRoutes =
                Arrays.asList(
                        this.PROTOCOL+this.HOST+this.PORT+Prefixes.client.toString()+"/dashboard",
                        this.PROTOCOL+this.HOST+this.PORT+Prefixes.client.toString()+"/profile",
                        this.PROTOCOL+this.HOST+this.PORT+Prefixes.client.toString()+"/orders");
        List<String> supplierRoutes =
                Arrays.asList(
                        this.PROTOCOL+this.HOST+this.PORT+Prefixes.supplier.toString()+"/dashboard",
                        this.PROTOCOL+this.HOST+this.PORT+Prefixes.supplier.toString()+"/products",
                        this.PROTOCOL+this.HOST+this.PORT+Prefixes.supplier.toString()+"/inventory");

        List<String> publicRoutes =
                Arrays.asList(
                        this.PROTOCOL+this.HOST+this.PORT+Prefixes.publics.toString()+"/",
                        this.PROTOCOL+this.HOST+this.PORT+Prefixes.publics.toString()+"/login",
                        this.PROTOCOL+this.HOST+this.PORT+Prefixes.publics.toString()+"/logout",
                        this.PROTOCOL+this.HOST+this.PORT+Prefixes.publics.toString()+"/register");


        this.combinedRoutes = new ArrayList<>();

        this.combinedRoutes.addAll(adminRoutes);
        this.combinedRoutes.addAll(clientRoutes);
        this.combinedRoutes.addAll(supplierRoutes);
        this.combinedRoutes.addAll(publicRoutes);

        this.routes = new Routes(adminRoutes,clientRoutes,supplierRoutes,publicRoutes);
    }

    public SecurityConfig(String PROTOCOL, String HOST, String PORT) {
        this.PROTOCOL = PROTOCOL;
        this.HOST = HOST;
        this.PORT = PORT;
    }

    URLTester isURLValid(String url)
    {
        int count = 0;
        URLTester output = null;
        for(String route : this.combinedRoutes)
        {
            if(route.equals(combinedRoutes.get(count)))
            {
                output.setValidUrl(true);
                output.setRouteType(route);
                return output;
            }
                count++;
        }
        return output;
    }

    Boolean isUserAuthenticated(String token)
    {
        Claims claim_email = this.jwtUtil.extractAllClaims(token);
//        String email = claim_email.getSubject();
//        User user = this.routeResolveService.getTokenUser(email);
        Boolean isTokenValid_ = this.jwtUtil.isTokenExpired(token);
        if(!isTokenValid_)
        {
            return true;
        }
        return false;
    }
    Boolean isUserAdmin(String token)
    {
        ClaimsResponse claimsResponse = this.jwtUtil.extractClaimsAsClaimResponse(token);
        return false;
    }
}

