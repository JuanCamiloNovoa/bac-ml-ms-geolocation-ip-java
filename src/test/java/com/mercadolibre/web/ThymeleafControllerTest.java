package com.mercadolibre.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ThymeleafControllerTest {
    @InjectMocks
    private ThymeleafController thymeleafController;

    @Test
    void index() {
        Model model = mock(Model.class);
        String viewName = thymeleafController.index(model);
        assertEquals("index", viewName);
    }

    @Test
    void results() {
        Model model = mock(Model.class);
        String ip = "127.0.0.1";
        String viewName = thymeleafController.results(ip, model);
        assertEquals("results", viewName);
        verify(model).addAttribute("ip", ip);
    }
}