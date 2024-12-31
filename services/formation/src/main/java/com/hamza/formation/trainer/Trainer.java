package com.hamza.formation.trainer;

import com.hamza.formation.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Builder
@Table(name = "trainers")
public class Trainer extends User {
    private String phoneNumber;
    private String cvUrl;
    private List<String> specialities;
}
