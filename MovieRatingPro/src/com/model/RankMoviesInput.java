package com.model;

import java.io.Serializable;

public class RankMoviesInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int type;


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}
	
	
	public boolean isDirection() {
		return direction;
	}


	public void setDirection(boolean direction) {
		this.direction = direction;
	}


	public boolean isActor() {
		return actor;
	}


	public void setActor(boolean actor) {
		this.actor = actor;
	}


	public boolean isActress() {
		return actress;
	}


	public void setActress(boolean actress) {
		this.actress = actress;
	}


	public boolean isSongs() {
		return songs;
	}


	public void setSongs(boolean songs) {
		this.songs = songs;
	}


	public boolean isProduction() {
		return production;
	}


	public void setProduction(boolean production) {
		this.production = production;
	}


	public boolean isDialogues() {
		return dialogues;
	}


	public void setDialogues(boolean dialogues) {
		this.dialogues = dialogues;
	}


	private boolean direction;
	
	private boolean actor;
	
	private boolean actress;
	
	private boolean songs;
	
	private boolean production;
	
	private boolean dialogues;
	

}
