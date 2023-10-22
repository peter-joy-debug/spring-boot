package com.example.authentication;

import com.example.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;

    public JwtUtil(JwtConfig jwtConfig)
    {
        this.jwtConfig = jwtConfig;
    }
    public String generateToken(User user) {
        Date now = new Date();
        jwtConfig.setSessionDuration(3600000);
        Date expirationDate = new Date(now.getTime() + jwtConfig.getSessionDuration()); // Token expires in 1 hour
        String base64Key = jwtConfig.generateRandomKey();
//        Device device = DeviceUtils.getCurrentDevice(request);

        //Setting Key

        jwtConfig.setSecretKey(base64Key);
        System.out.println("Generated Secret Key: " + jwtConfig.getSecretKey());

        JwtBuilder builder = Jwts.builder()
                .setSubject(user.getEmail()) // Set the user's email as the subject
                .claim("userId", user.getUserID()) // Convert user ID to a string
                .claim("userRole", user.getUserType()); // Convert user role to a string
        System.out.println("Toke Issue: "+now);
        System.out.println("Expiration Date: "+expirationDate);
        builder.setIssuedAt(now);
        builder.setExpiration(expirationDate);
        builder.signWith(SignatureAlgorithm.HS256, jwtConfig.getSecretKey());
        String token = builder.compact();
        System.out.println("Generated Token: " + token);
        return token;
    }




    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, String claimName, Class<T> type) {
            Claims claims = extractAllClaims(token);
            Object claimValue = claims.get(claimName);

            if (claimValue == null) {
                // Handle the case when the claim is not present
                return null;
            }

            // Cast the claim value to the expected type
            if (type.isInstance(claimValue)) {
                return type.cast(claimValue);
            } else {
                // Handle the case when the claim is of an unexpected type
                System.out.println("Claim is not of the expected type");
                return null;
            }
    }

    public Date getExpirationDate(String token)
    {
        Claims claims = this.extractAllClaims(token);
        return claims.getExpiration();
    }

    public String extractClaimsAsJsonString(String token) {
        // Parse the token to extract claims

        Claims claims = this.extractAllClaims(token);

        // Create a JSON object to hold the claims
        JSONObject jsonObject = new JSONObject();
        String jsonString ="";
        // Extract individual claims and add them to the JSON object
        try {
            jsonObject.put("subject", claims.getSubject());
            jsonObject.put("userId", claims.get("userId"));
            jsonObject.put("userRole", claims.get("userRole"));
            jsonObject.put("Expiration", claims.getExpiration());

            // Convert the JSON object to a JSON string
            jsonString = jsonObject.toString();

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonString;
    }

    public ClaimsResponse extractClaimsAsClaimResponse(String token) {
        // Parse the token to extract claims
        Claims claims = this.extractAllClaims(token);
        // Create a JSON object to hold the claims
        JSONObject jsonObject = new JSONObject();
        String jsonString ="";
        // Extract individual claims and add them to the JSON object
        ClaimsResponse claimsResponse =null;
            claimsResponse = new ClaimsResponse(claims.getSubject(),claims.get("userId").toString(),claims.get("userRole").toString());
//            jsonObject.put("subject", claims.getSubject());
//            jsonObject.put("userId", claims.get("userId"));
//            jsonObject.put("userRole", claims.get("userRole"));
//            jsonObject.put("Expiration", claims.getExpiration());
        return claimsResponse;
    }




    public Boolean isTokenExpired(String token) {
        Date expirationDate = this.getExpirationDate(token);
        if (expirationDate == null) {
            throw new NullPointerException("Token expiration date is null");
        }
        return expirationDate.before(new Date());
    }

    public void logout(String token)
    {
        if(!this.isTokenExpired(token))
        {
            Claims claims = this.extractAllClaims(token);
            claims.setExpiration(claims.getIssuedAt());
            System.out.println("You are logged out!");
        }
    }



    public String extractSubject(String token) {
        return extractClaim(token, Claims.SUBJECT, String.class);
    }
}
