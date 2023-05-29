package com.drones.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }
}
