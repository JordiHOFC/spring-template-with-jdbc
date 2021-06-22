package br.com.learning.datajdbctemplate.repository;

import br.com.learning.datajdbctemplate.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Component;
import org.w3c.dom.ls.LSOutput;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Component
public class MappingSqlQueryPerson {
    @Autowired
    private DataSource dataSource;
    private SqlQuery<Pessoa> personSqlQuery;
    private SqlQuery<Pessoa> personAllQuery;


    @PostConstruct
    private void postConstruct() {
        personSqlQuery = new PersonMappingSqlQuery(dataSource, "select * from Pessoa where ID=?");
        personSqlQuery.declareParameter(new SqlParameterValue(Types.BIGINT, "ID"));
        personAllQuery=new PersonMappingSqlQuery(dataSource, "select * from Pessoa");
    }

    public Optional<Pessoa> findById(Long id) {

        final List<Pessoa> execute = personSqlQuery.execute(id);

        if (execute.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(execute.get(0));
    }
    public List<Pessoa> findAll(){
        return personAllQuery.execute();
    }

    public void save(Pessoa pessoa) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("Pessoa")
                .usingGeneratedKeyColumns("id");
        final SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(pessoa);
        insert.executeBatch(batch);
    }

    public void saveAll(List<Pessoa> pessoas) {
        pessoas.forEach(this::save);
    }


}
