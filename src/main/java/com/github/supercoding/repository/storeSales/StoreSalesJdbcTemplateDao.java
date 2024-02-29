package com.github.supercoding.repository.storeSales;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StoreSalesJdbcTemplateDao implements StoreSalesRepository {

    private JdbcTemplate jdbcTemplate;

    public StoreSalesJdbcTemplateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //NOTE: find를 하기위해서는 RowMapper가 필요
    static RowMapper<StoreSales> storeSalesRowMapper = ((rs, rowNum) ->
            new StoreSales(
                    rs.getInt("id"),
                    rs.getString("store_name"),
                    rs.getInt("amount")
            )
    );

    @Override
    public StoreSales findStoreSalesById(Integer storeId) {
        return jdbcTemplate.queryForObject("SELECT * from store_sales WHERE  id = ?", storeSalesRowMapper, storeId);
    }

    @Override
    public void updateSalesAmount(Integer storeId, Integer amount) {
        jdbcTemplate.update("UPDATE store_sales" +
                            "SET amount = ?" +
                            "WHERE id = ?", amount, storeId);

    }
}
