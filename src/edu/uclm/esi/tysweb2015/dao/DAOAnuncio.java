package edu.uclm.esi.tysweb2015.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import edu.uclm.esi.tysweb2015.dominio.Anuncio;

public class DAOAnuncio {
	
	public static void insert(Anuncio anuncio) throws SQLException, Exception {
		Connection bd = Broker.get().getConnectionInsercion();
		try{
			String sql="{call insertarAnuncio (?, ?, ?, ?)}";
			CallableStatement cs=bd.prepareCall(sql);
			cs.setInt(1, anuncio.getIdAnunciante());
			cs.setString(2, anuncio.getDescripcion());
			cs.setInt(3, anuncio.getIdCategoria());
			cs.registerOutParameter(4, java.sql.Types.INTEGER);
			cs.executeUpdate();
			int exito=cs.getInt(4);
			if (exito<=0)
				throw new SQLException("Error al insertar anuncio. Código: "+exito);
		}
		catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
	}

}
