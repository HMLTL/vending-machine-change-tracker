package com.mpikuza.vendingmachine.configuration;

import org.jline.utils.AttributedString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

import static org.jline.utils.AttributedStyle.DEFAULT;
import static org.jline.utils.AttributedStyle.YELLOW;

@Configuration
public class ShellConfiguration {

    @Bean
    public PromptProvider promptProvider() {
        return () -> new AttributedString("VendingMachine > ", DEFAULT.foreground(YELLOW));
    }

}
