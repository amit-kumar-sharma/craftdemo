/**
 * 
 */
package org.craft.demo.dataimport.dbclients;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.craft.demo.analyticalstore.GraphDB;
import org.craft.demo.dataimport.model.GraphDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author asharma
 *
 */
@Component
public class GraphDBClient implements DBClient<GraphDataModel>{
	
	@Autowired
	private GraphDB graphDB; 

	/*
	 * (non-Javadoc)
	 * @see org.craft.demo.dataimport.dbclients.DBClient#readData(java.nio.file.Path)
	 */
	@Override
	public List<GraphDataModel> readData(final Path databasePath) {
		return Arrays.asList(new GraphDataModel(graphDB.getProbableDuplicates()));
	}
	
	@Override
	public void writeData(GraphDataModel data) {
		graphDB.addData(data);
	}

	@SuppressWarnings("unused")
	private BigInteger generateId(Serializable obj) throws IOException, NoSuchAlgorithmException {
	    if (obj == null) {
	      return BigInteger.ZERO;
	    }
	    try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    	ObjectOutputStream oos = new ObjectOutputStream(baos);){
		    oos.writeObject(obj);
		    MessageDigest m = MessageDigest.getInstance("SHA1");
		    m.update(baos.toByteArray());
		    return new BigInteger(1, m.digest());
	    }
	}

}
