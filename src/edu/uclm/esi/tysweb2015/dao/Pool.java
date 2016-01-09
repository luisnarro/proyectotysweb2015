package edu.uclm.esi.tysweb2015.dao;

import java.sql.SQLException;
import java.util.Vector;

public class Pool {
	private Vector<Conexion> seleccionLibres;
	private Vector<Conexion> seleccionOcupadas;
	private Vector<Conexion> insercionLibres;
	private Vector<Conexion> insercionOcupadas;
	private Vector<Conexion> eliminarLibres;
	private Vector<Conexion> eliminarOcupadas;
	
	public Pool(int nS, int nI, int nD) throws SQLException {
		seleccionLibres=new Vector<>();
		seleccionOcupadas=new Vector<>();
		insercionLibres=new Vector<>();
		insercionOcupadas=new Vector<>();
		eliminarLibres=new Vector<>();
		eliminarOcupadas=new Vector<>();
		
		for(int i=0; i<nS; i++){
			Conexion conexion = new Conexion("selectorTSW2015", "selectorTSW2015", this);
			seleccionLibres.add(conexion);
		}
		
		for(int i=0; i<nI; i++){
			Conexion conexion = new Conexion("inserterTyS2015", "inserterTyS2015", this);
			insercionLibres.add(conexion);
		}
		
		for(int i=0; i<nD; i++){
			Conexion conexion = new Conexion("deleteTyS2015", "deleteTyS2015", this);
			eliminarLibres.add(conexion);
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
	
	public Conexion getConexionDelete() throws SQLException {
		if(eliminarLibres.size()==0)
			throw new SQLException("No hay conexiones libres");
		
		Conexion result = eliminarLibres.remove(0);
		eliminarOcupadas.add(result);
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
	
	public void moverAEliminarLibres(Conexion conexion) {
		this.eliminarOcupadas.remove(conexion);
		this.eliminarLibres.add(conexion);		
	}
}
