package store.order;

import java.lang.reflect.Array;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Builder @Data @Accessors(fluent = true, chain = true)
public class Order {

    private String id;
    
    private float total;
    
}
