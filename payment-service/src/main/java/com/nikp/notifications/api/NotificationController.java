package com.nikp.notifications.api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.nikp.notifications.domain.HelloMessage;
import com.nikp.notifications.domain.PaymentAddedNotification;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;

@Controller
public class NotificationController {

    @Autowired
    private MeterRegistry registry;

    @MessageMapping("/payment-add")
    @SendTo("/topic/greetings")
    @Timed
    public HelloMessage welcomeUser(PaymentAddedNotification paymentAddedNotification) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new HelloMessage("Hello, " + paymentAddedNotification.getName() + "!");
    }

}