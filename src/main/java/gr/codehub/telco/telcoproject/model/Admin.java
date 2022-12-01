package gr.codehub.telco.telcoproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@EqualsAndHashCode (callSuper = true)
@SuperBuilder
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="admin")
public class Admin extends User{

    @Column(name="admin_id")
    private int adminId; //right now the toString method produces only the employeeId


    //this can be changed

}
