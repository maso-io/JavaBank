package com.masonwabe;

import java.sql.*;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;

public class Backend
{
    private DataSource ds;
    private final Connection connection;

    public Backend() {
        setDs(createDataSource());
        try {
            //connection = DriverManager.getConnection("jdbc:h2:mem:;INIT=RUNSCRIPT FROM 'classpath:users.sql';");
            connection = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResultSet getRow(int acc) {
        ResultSet rs;
        try {
            PreparedStatement ps = connection.prepareStatement("select *  FROM Users where (account_number) = (?)");
            ps.setInt(1, acc);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    
    public void setAmount(int acc, int amount)
    {
        int count; 
        try {
            PreparedStatement ps = connection.prepareStatement("update Users set (amount) = (?) where account_number = (?)");
            ps.setInt(1, amount);
            ps.setInt(2, acc);
            count = ps.executeUpdate();
            if (count <1) throw new SQLException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAccount(String pin)
    {
        if (pin.length() != 5) {
            System.out.println("Pin must be 5 digit");
            return;
        }
        int count;
        try {
            PreparedStatement ps = connection.prepareStatement("insert into Users (pin) values = (?)");
            ps.setString(1, pin);
            count = ps.executeUpdate();
            if (count <1) throw new SQLException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validate(int acc, String pin) throws SQLException
    {
        PreparedStatement ps = connection.prepareStatement("select * from Users where (account_number) = (?)");
        ps.setInt(1, acc);
        ResultSet rs = ps.executeQuery();
        if (!rs.next())
            return false;
        boolean pb = pin.equals(rs.getString("pin_number"));
        boolean ab = acc == rs.getInt("account_number");
        return (ab && pb);
    }

    public DataSource getDs() {
        return ds;
    }

    public Connection getDBConnection() {
        return connection;
    }
    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    public static DataSource createDataSource()
    {
        HikariDataSource ds = new HikariDataSource();
        String jdbcUrlEmbed = " jdbc:h2:./bank";
        //String jdbcUrl = "jdbc:h2:./bank.mv.db:;INIT=RUNSCRIPT FROM 'classpath:users.sql';";
        ds.setJdbcUrl(jdbcUrlEmbed);
        ds.setUsername("sa");
        ds.setPassword("1234");
        return ds;
    }
}
