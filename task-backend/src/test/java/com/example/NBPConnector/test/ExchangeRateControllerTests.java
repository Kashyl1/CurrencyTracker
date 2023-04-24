package com.example.NBPConnector.test;

import com.example.NBPConnector.controller.ExchangeRateController;
import com.example.NBPConnector.responsebuilder.ResponseBuilders;
import com.example.NBPConnector.service.ExchangeRateService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ExchangeRateControllerTests {

    @InjectMocks
    private ExchangeRateController exchangeRateController;

    @Mock
    private ExchangeRateService exchangeRateService;

    @Mock
    private ResponseBuilders responseBuilders;
    // :(

}
