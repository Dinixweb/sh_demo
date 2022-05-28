package io.seamhealthv1.simplecrudapplication.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class errorMessage {
    private int statusCode;
    private String message;
}
