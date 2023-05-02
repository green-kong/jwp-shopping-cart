package cart.dao.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import cart.dao.entity.Product;
import cart.dao.entity.User;

@Repository
public class JdbcCartDao implements CartDao {

    private final NamedParameterJdbcOperations namedParameterJdbc;

    public JdbcCartDao(NamedParameterJdbcOperations namedParameterJdbc) {
        this.namedParameterJdbc = namedParameterJdbc;
    }

    @Override
    public Long addProduct(User user, Long productId) {
        final Long userId = getUserId(user);
        final String sql = "INSERT INTO cart (user_id, product_id) VALUES (:userId, :productId)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("productId", productId);
        final SqlParameterSource params = new MapSqlParameterSource(paramMap);
        namedParameterJdbc.update(sql, params, keyHolder);
        return (long) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Product> findAllProductInCart(User user) {
        final Long userId = getUserId(user);
        final String sql = "SELECT p.id, p.name, p.price, p.img_url FROM cart AS c "
                + "JOIN product AS p "
                + "ON p.id = c.product_id "
                + "WHERE c.user_id = :userId";
        final SqlParameterSource params = new MapSqlParameterSource("userId", userId);
        final RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
        return namedParameterJdbc.query(sql, params, rowMapper);
    }

    @Override
    public void removeProductInCart(User user, Long productId) {
        final Long userId = getUserId(user);
        final String sql = "DELETE FROM cart "
                + "WHERE user_id = :userId "
                + "AND product_id = :productId "
                + "LIMIT 1";
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("productId", productId);
        final SqlParameterSource params = new MapSqlParameterSource(paramMap);
        namedParameterJdbc.update(sql, params);
    }

    private Long getUserId(User user) {
        try {
            final String sql = "SELECT id FROM users WHERE email = :email AND password = :password";
            final SqlParameterSource params = new BeanPropertySqlParameterSource(user);
            return namedParameterJdbc.queryForObject(sql, params, Long.class);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "회원정보를 다시 확인해주세요.");
        }
    }
}