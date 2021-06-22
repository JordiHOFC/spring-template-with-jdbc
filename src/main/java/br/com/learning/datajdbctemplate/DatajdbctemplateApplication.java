package br.com.learning.datajdbctemplate;

import br.com.learning.datajdbctemplate.model.Pessoa;
import br.com.learning.datajdbctemplate.repository.MappingSqlQueryPerson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DatajdbctemplateApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext run = SpringApplication.run(DatajdbctemplateApplication.class, args);
		final MappingSqlQueryPerson repository = run.getBean(MappingSqlQueryPerson.class);

		Pessoa jordi= new Pessoa("Jordi Henrique Marques Silva",24,"973.146.700-99");
		Pessoa jordi2= new Pessoa("Jordi Henrique Marques Silva",24,"973.146.700-99");
		repository.saveAll(List.of(jordi,jordi2));

		final Optional<Pessoa> byId = repository.findById(1L);
		repository.findAll().stream()
				.map(Pessoa::getId)
				.map(repository::findById)
				.map(Optional::get)
				.map(r->"Nome: "+r.getNome()+"\nIdade: "+r.getIdade()+"\nCPF:"+r.getCpf())
				.forEach(System.out::println);


	}

}
