package part3.v5.extractmethod;

import part3.v5.StatementStrategy;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = this.dataSource.getConnection();

            ps = stmt.makePreparedStatement(conn);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            if( ps != null){ try { ps.close();} catch (SQLException e){}}
            if(conn != null){ try { conn.close();} catch (SQLException e){}}
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
