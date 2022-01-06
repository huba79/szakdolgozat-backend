/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.domain;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author huba
 */
public class Configuration {

    public static final Map<Integer, String> RESERVATION_STATUSES;

    public static final Map<Integer, String> STAGE_STATUSES;
    
    public static final Map<Integer, String> STAGE_TYPES;   

    static {
        
        RESERVATION_STATUSES = new HashMap<>();
        RESERVATION_STATUSES.put(0, "Requests");
        RESERVATION_STATUSES.put(1, "Reserved");
        RESERVATION_STATUSES.put(2, "Paid");
        RESERVATION_STATUSES.put(3, "InUse");
        RESERVATION_STATUSES.put(4, "Canceled");
        RESERVATION_STATUSES.put(5, "Expired");
        
        STAGE_STATUSES = new HashMap<>();
        STAGE_STATUSES.put(0, "Available");
        STAGE_STATUSES.put(1, "Reserved");
        STAGE_STATUSES.put(2, "Paid");
        STAGE_STATUSES.put(3, "InUse");
        STAGE_STATUSES.put(4, "Inactive");
        
        STAGE_TYPES = new HashMap<>();
        STAGE_TYPES.put(0, "Shore");
        STAGE_TYPES.put(1, "Stage");
        STAGE_TYPES.put(2, "Boat");
        
        

    }
    
}
