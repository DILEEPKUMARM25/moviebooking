package com.example.moviebooking.common.resposeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonRespose {
    private Boolean error;
   private String message;
   private Object data;
}
