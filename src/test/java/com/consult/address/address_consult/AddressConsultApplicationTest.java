package com.consult.address.address_consult;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

class AddressConsultApplicationTest {

    @Test
    void shouldInstantiateApplicationClass() {
        // arrange + act
        AddressConsultApplication app = new AddressConsultApplication();

        // assert
        assertNotNull(app);
    }

    @Test
    void shouldExposeSpringBootApplicationAnnotationAndMainMethod() throws NoSuchMethodException {
        // arrange + act
        SpringBootApplication annotation = AddressConsultApplication.class.getAnnotation(SpringBootApplication.class);
        var mainMethod = AddressConsultApplication.class.getMethod("main", String[].class);

        // assert
        assertNotNull(annotation);
        assertEquals(void.class, mainMethod.getReturnType());
    }

    @Test
    void shouldCallSpringApplicationRunFromMain() {
        // arrange
        String[] args = new String[]{"--spring.main.web-application-type=none"};
        ConfigurableApplicationContext context = mock(ConfigurableApplicationContext.class);

        // act + assert
        try (MockedStatic<SpringApplication> springApp = mockStatic(SpringApplication.class)) {
            springApp.when(() -> SpringApplication.run(AddressConsultApplication.class, args)).thenReturn(context);
            AddressConsultApplication.main(args);
            springApp.verify(() -> SpringApplication.run(AddressConsultApplication.class, args));
        }
    }
}
