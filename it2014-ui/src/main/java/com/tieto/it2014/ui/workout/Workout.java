/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tieto.it2014.ui.workout;

import java.util.Date;

/**
 *
 * @author pc3
 */


public class Workout {
            
            // EMEI
            // Start
            // Finish
            // Duration
            // Distance
            
            String imei = null;
            Date start = null;
            Date finish = null;
            double duration = 0;
            double distance = 0;
            
            public Workout(String imei,
                    Date start,
                    Date finish,
                    double duration,
                    double distance) {
                this.imei = imei;
                this.start = start;
                this.finish = finish;
                this.duration = duration;
                this.distance = distance;
            }
            
        }

