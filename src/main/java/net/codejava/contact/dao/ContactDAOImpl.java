package net.codejava.contact.dao;

import net.codejava.contact.model.Contact;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContactDAOImpl implements ContactDAO {

    /* JdbcTemplate Это базовый класс, который управляет обработкой всех событий и связями с БД.
    Класс JdbcTemplate выполняет SQL-запросы, выполняет итерации по ResultSet
    и извлекает вызываемые значения, обновляет инструкции и вызовы процедур,
    “ловит” исключения и транслирует их в исключения, определённые в пакете org.*/

    private JdbcTemplate jdbcTemplate;

    public ContactDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public int save(Contact c) {
        String sql = "INSERT INTO Contact(name, email, address, phone) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, c.getName(), c.getEmail(), c.getAddress(), c.getPhone());
    }

    public int update(Contact c) {
        String sql = "UPDATE Contact SET name=?, email=?, address=?, phone=? WHERE contact_id=?";
        return jdbcTemplate.update(sql, c.getName(), c.getEmail(), c.getAddress(), c.getPhone(), c.getId());
    }

    /*org.springframework.jdbc.core.ResultSetExtractor – это интерфейс обратного вызова,
    используемый методами запросов JdbcTemplate. Реализации этого интерфейса выполняют
    фактическую работу по извлечению результатов из ResultSet,
    но не нужно беспокоиться об обработке исключений.*/

    public Contact get(final Integer id) {
        String sql = "SELECT * FROM contact WHERE contact_id=" + id;

        ResultSetExtractor<Contact> extractor = new ResultSetExtractor<Contact>() {
            public Contact extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
                    String phone = resultSet.getString("phone");

                    return new Contact(id, name, email, address, phone);
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }

    public int delete(Integer id) {
        String sql = "DELETE FROM contact WHERE contact_id=" + id;
        return jdbcTemplate.update(sql);
    }

    public List<Contact> list() {
        String sql = "SELECT * FROM contact";

        RowMapper<Contact> rowMapper = new RowMapper<Contact>() {
            public Contact mapRow(ResultSet rs, int rowNumber) throws SQLException {
                int id = rs.getInt("contact_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");

                return new Contact(id, name, email, address, phone);
            }
        };

        return jdbcTemplate.query(sql, rowMapper);
    }
}
