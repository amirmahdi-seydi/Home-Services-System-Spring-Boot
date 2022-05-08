package ir.maktab.homeservice.service.dto;
/*
 * created by Amir mahdi seydi 5/8/2022 2:08 AM
 */


import ir.maktab.homeservice.model.Service;
import ir.maktab.homeservice.model.Specialist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpecialistServiceDTO {

    private Long id;
    private Specialist specialist;
    private Service services;
}
