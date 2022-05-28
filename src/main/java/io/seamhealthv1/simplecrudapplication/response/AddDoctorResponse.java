package io.seamhealthv1.simplecrudapplication.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDoctorResponse {

    private String message;
    private String doctorId;
    private String headerLocation;
    private String statusCode;

}
