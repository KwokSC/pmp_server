package com.chunkie.pmp_server.handler;

import com.chunkie.pmp_server.common.Species;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Species.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class SpeciesEnumTypeHandler extends BaseTypeHandler<Species> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Species species, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, species.name());
    }

    @Override
    public Species getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String value = resultSet.getString(s);
        return (value == null)? null:Species.valueOf(value);
    }

    @Override
    public Species getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String value = resultSet.getString(i);
        return (value == null)? null:Species.valueOf(value);
    }

    @Override
    public Species getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String value = callableStatement.getString(i);
        return (value == null)? null: Species.valueOf(value);
    }
}
