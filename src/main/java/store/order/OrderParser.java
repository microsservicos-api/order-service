package store.order;

import java.util.List;

import store.order.OrderIn;
import store.order.OrderOut;

public class OrderParser {

    public static Order to(OrderIn in) {
        return in == null ? null :
            Order.builder()
                .name(in.name())
                .email(in.email())
                .password(in.password())
                .build();
    }

    public static OrderOut to(Order a) {
        return a == null ? null :
            OrderOut.builder()
                .id(a.id())
                .name(a.name())
                .email(a.email())
                .build();
    }

    public static List<OrderOut> to(List<Order> as) {
        return as == null ? null :
            as.stream().map(OrderParser::to).toList();
    }
    
}
