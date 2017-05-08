package com.wiley.umltoolkit.casestudy.dao.common;
import com.wiley.umltoolkit.casestudy.common.DatabaseException;
import java.util.Collection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/** Data Access Object interface used by most DAOs to represent CRUD (Create,
 * Read,  Update,  Delete) processing
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public interface BaseDao  {
    
    /** Finds a collection of business objects (i.e. Value Objects)
     * @return Collection containing collection of business objects (i.e. Value Objects)
     * @throws DatabaseException thrown when unable to load all objects
     */
    public Collection loadAll() throws DatabaseException;
    
    /** Creates a record in the database given the values from a business object
     * @param object Object containing populated data (typically entered by a
     * user on a data entry form)
     * @throws DatabaseException thrown when unable to create a record
     */
    public void create(Object object) throws DatabaseException;
    
    /** Finds a business object for this Manager given a unique id
     * @param key id representing the unique key representing a record in Database
     * @return Object business object representing data for this Manager
     * @throws DatabaseException general db error
     */
    public Object load(String key) throws DatabaseException;
    
    /** Finds a business object for this Manager given a unique id
     * @param object Object containing populated data (from an existing record
     * in the database)
     * @throws DatabaseException thrown when unable to store this object
     */
    public void store(Object object) throws DatabaseException;
    
    /** Removes a record from the database given a unique id
     * @param key id representing the unique key representing a record in Database
     * @throws DatabaseException thrown when unable to remove a record with this key
     */
    public void remove(String key) throws DatabaseException;
    
    /** Force the implementation of this method in concrete class
     * @return Connection containing a connection to Database
     * @throws DatabaseException if unable to get Connection
     */
    public abstract Connection getConnection() throws DatabaseException;
    
    /** Force the implementation of this method in concrete class
     * @param rs ResultSet A table of data representing a database result set
     * @param stmt Statement The object used for executing a static SQL statement
     * and obtaining the results produced by it
     * @param conn Connection A connection (session) with a specific database
     * @throws DatabaseException if unable to close Connection
     */
    public abstract void closeDbConnection(ResultSet rs,  Statement stmt,  Connection conn) throws DatabaseException;
    
}