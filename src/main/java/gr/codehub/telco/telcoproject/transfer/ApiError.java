package gr.codehub.telco.telcoproject.transfer;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
@AllArgsConstructor
public class ApiError {
    Integer status;
    String message;
}
