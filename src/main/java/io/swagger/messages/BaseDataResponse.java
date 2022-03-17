/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.messages;

import io.swagger.domain.*;
import java.util.ArrayList;

/**
 *
 * @author huba
 */
public class BaseDataResponse {
    Company company;
    ArrayList<Lake> lakes;
    ArrayList<ServicePrice> services;
    ArrayList<Stage> stages;
    User CurrentUser;

    public BaseDataResponse(Company company, ArrayList<Lake> lakes, ArrayList<ServicePrice> services, ArrayList<Stage> stages, User CurrentUser) {
        this.company = company;
        this.lakes = lakes;
        this.services = services;
        this.stages = stages;
        this.CurrentUser = CurrentUser;
    }
    
}
