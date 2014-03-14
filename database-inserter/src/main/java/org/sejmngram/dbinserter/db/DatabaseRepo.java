package org.sejmngram.dbinserter.db;

import org.sejmngram.dbinserter.model.RowData;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created by michalsiemionczyk on 05/03/14.
 */
public class DatabaseRepo {

    private  static String db_host = "localhost";
    private static String db_base = "sejmngram";
    private static String db_user = "db-inserter";
    private static String db_pass = "sejmngram";

    Connection c;

    public DatabaseRepo(){
        initDB();
        this.c = getConnection();

    }

    public  void insertToDb( HashMap<String, RowData> blobMap) {
        initDB();
        Connection c = getConnection();

        PreparedStatement stmt;

        //Display URL and connection information
        System.out.println("Connection: " + c);

        //Get a Statement object
        try {
            int nrInsertedRows = 0;
            for ( String ngram :  blobMap.keySet()){
                RowData rowData = blobMap.get( ngram );
                for ( RowData.Row row :  rowData.getAllRows()){
                    stmt = c.prepareStatement("INSERT INTO sejmngram.ngrams values ( default, ?, ?, ?, ?, ?)");

                    stmt.setDate( 1, new java.sql.Date( rowData.getDateFrom().getTime() ));
                    stmt.setDate( 2, new java.sql.Date( rowData.getDateTo().getTime() ));
                    stmt.setString( 3, ngram );
                    stmt.setInt( 4, row.getNrEntries());
                    stmt.setBytes( 5, row.getBlob() );

                    //create table
                    int result =  stmt.executeUpdate();
                    nrInsertedRows++;
                    if ( nrInsertedRows % 500 == 0){
                        Logger.getAnonymousLogger().info( "inserted rows :" + nrInsertedRows);
                    }

                }

            }

            Logger.getAnonymousLogger().info( "nrAllInsertedRows: " + nrInsertedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void initDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection(){
        String url = "jdbc:mysql://" + db_host  + "/" + db_base;
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, db_user, db_pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
