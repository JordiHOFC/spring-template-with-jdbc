package br.com.learning.datajdbctemplate.config;

import br.com.learning.datajdbctemplate.repository.MappingSqlQueryPerson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class JdbcConfiguration {
    @Bean
    public DataSource h2DataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("CriarTabelaPessoa.sql")
                .build();
    }
}
