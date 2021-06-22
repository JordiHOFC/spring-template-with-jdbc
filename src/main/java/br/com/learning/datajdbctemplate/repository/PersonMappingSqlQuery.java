package br.com.learning.datajdbctemplate.repository;

import br.com.learning.datajdbctemplate.model.Pessoa;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMappingSqlQuery extends MappingSqlQuery<Pessoa> {
    public PersonMappingSqlQuery(DataSource ds, String sql) {
        super(ds, sql);
    }

    @Override
    protected Pessoa mapRow(ResultSet resultSet, int i) throws SQLException {
       return new Pessoa(resultSet.getLong("ID"),
               resultSet.getString("NOME"),
               resultSet.getInt("IDADE"),
               resultSet.getString("CPF")
       );
    }
}
