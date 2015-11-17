package edu.uclm.esi.tysweb2015.dao;

import java.sql.SQLException;
import java.util.Vector;

public class Pool {
	private Vector<Conexion> seleccionLibres;
	private Vector<Conexion> seleccionOcupadas;
	private Vector<Conexion> insercionLibres;
	private Vector<Conexion> insercionOcupadas;
	
	public Pool(int nS, int nI) throws SQLException {
		seleccionLibres=new Vector<>();
		seleccionOcupadas=new Vector<>();
		insercionLibres=new Vector<>();
		seleccionOcupadas=new Vector<>();
		
		for(int i=0; i<nS; i++){
			Conexion conexion = new Conexion("selectorTSW2015", "selectorTSW2015", this);
			seleccionLibres.add(conexion);
		}
		
		for(int i=0; i<nI; i++){
			Conexion conexion = new Conexion("inserterTSW2015", "inserterTSW2015", this);
			insercionLibres.add(conexion);
		}
	}

	public Conexion getConexionSelection() throws SQLException {
		if(seleccionLibres.size()==0)
			throw new SQLException("No hay conexiones libres");
		
		Conexion result = seleccionLibres.remove(0);
		seleccionOcupadas.add(result);
		return result;
	}
	
	public Conexion getConexionInsercion() throws SQLException {
		if(insercionLibres.size()==0)
			throw new SQLException("No hay conexiones libres");
		
		Conexion result = insercionLibres.remove(0);
		insercionOcupadas.add(result);
		return result;
	}

	public void moverASeleccionLibres(Conexion conexion) {
		this.seleccionOcupadas.remove(conexion);
		this.seleccionLibres.add(conexion);
		
	}

	public void moverAInsercionLibres(Conexion conexion) {
		this.insercionOcupadas.remove(conexion);
		this.insercionLibres.add(conexion);		
	}
}
