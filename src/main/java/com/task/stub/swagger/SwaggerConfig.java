package com.task.stub.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Эмулятор системы процессинга",
                description = "Эмулируется работа внешней системы для проведения НТ системы выдачи микрокредитов",
                contact = @Contact(
                        name = "name",
                        email = "email"
                )
        )
)

public class SwaggerConfig {
}
