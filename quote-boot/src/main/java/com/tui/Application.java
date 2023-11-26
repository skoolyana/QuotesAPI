package com.tui;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The SpringBoot Main Application.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.tui")
public class Application {

  /**
   * Main.
   * 
   * @param args the args
   */
  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
