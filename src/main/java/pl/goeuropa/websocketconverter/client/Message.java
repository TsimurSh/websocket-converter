package pl.goeuropa.websocketconverter.client;


import lombok.Data;

import java.util.HashMap;

@Data
public class Message {

    private HashMap <String, Object> ext_columns;
    private HashMap <String, Object> vehicles;

}
